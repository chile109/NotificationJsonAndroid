package app.kevin.com.jsontonotification;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import junit.framework.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
	private AlarmManager alarmManager;
	private PendingIntent pendingIntent;
	public static Message _message;
	private String Testdata = "{\"title\":\"晚餐服藥\",\"content\":\"紅包配溫開水\", \"date\":\"2018-10-15 18:00:10:033\"}";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		_message = JsonToMessage(Testdata);
		initView();
		initAlarm();
	}

	// 初始化控件
	private void initView() {

		Button alarmBtn = (Button) findViewById(R.id.button);
		alarmBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(_message.date);	//Date 轉換為 calendar

				setAlarm(calendar);
			}
		});
	}

	// 初始化闹钟
	private void initAlarm() {
		// 实例化AlarmManager
		alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		// 设置闹钟触发动作
		Intent intent = new Intent(this, AlarmBroadcast.class);
		intent.setAction("startAlarm");

		//intent傳送自定義object, object需要序列化至bundle中
		Bundle bundle = new Bundle();
		bundle.putSerializable("message", _message);
		intent.putExtra("bundle", bundle);

		pendingIntent = PendingIntent.getBroadcast(this, 110, intent, PendingIntent.FLAG_UPDATE_CURRENT);

		// 取消闹钟
//        alarmManager.cancel(pendingIntent);
	}

	// 设置闹钟
	private void setAlarm(Calendar calendar) {
//        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (1000 * 5), pendingIntent);
		alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

		Toast.makeText(this, "设置成功", Toast.LENGTH_SHORT).show();

	}

	private Message JsonToMessage(String data) {
		Gson gson = new GsonBuilder()
				.setPrettyPrinting()//格式化输出
				.setDateFormat("yyyy-MM-dd HH:mm:ss:SSS")//格式化时间
				.create();

		Message m = gson.fromJson(data, Message.class);
		System.out.println();
		System.out.println(m);

		return m;
	}
}
