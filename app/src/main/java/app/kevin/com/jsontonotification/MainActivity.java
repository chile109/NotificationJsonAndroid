package app.kevin.com.jsontonotification;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import junit.framework.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
	private AlarmManager alarmManager;
	private String TestData2 = "[{\"title\":\"中午注射\",\"content\":\"胰島素30cc\",\"date\":\"2018-10-17 11:57:30\"}," +
			"{\"title\":\"晚間服藥\",\"content\":\"紅包配白開水\",\"date\":\"2018-10-17 11:58:00\"}]";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Gson gson = new GsonBuilder()
				.setPrettyPrinting()//格式化输出
				.setDateFormat("yyyy-MM-dd HH:mm:ss:SSS")//格式化时间
				.create();
		initView();
	}

	// 初始化控件
	private void initView() {

		Button alarmBtn = (Button) findViewById(R.id.button);
		alarmBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				for (int i = 0; i < JsonToMessage(TestData2).length; i++) {
					Calendar calendar = Calendar.getInstance();		//每次getInstance都是返回一個新的Calendar物件
					calendar.setTime(JsonToMessage(TestData2)[i].date);    //Date 轉換為 calendar

					initAlarm(JsonToMessage(TestData2)[i], calendar, i);
				}

			}
		});
	}

	// 初始化闹钟
	private void initAlarm(Message _message, Calendar calendar, int i) {

		// 设置闹钟触发动作
		Intent intent = new Intent(this, AlarmBroadcast.class);
		intent.setAction("startAlarm");

		//intent傳送自定義object, object需要序列化至bundle中
		Bundle bundle = new Bundle();
		bundle.putSerializable("message", _message);
		intent.putExtra("bundle", bundle);

		//ID需不同才會被當作獨立的pendingintent, Flags宣告為0表示不對其做覆蓋或保留處理
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, i, intent, 0);
		setAlarm(calendar, pendingIntent);
	}

	// 设置闹钟
	private void setAlarm(Calendar calendar, PendingIntent pendingIntent) {
		// 实例化AlarmManager
		alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

		Toast.makeText(this, "设置成功", Toast.LENGTH_SHORT).show();
	}

	private Message[] JsonToMessage(String data) {
		Gson gson = new GsonBuilder()
				.setPrettyPrinting()//格式化输出
				.setDateFormat("yyyy-MM-dd HH:mm:ss")//格式化时间
				.create();

		Message[] messageArray = gson.fromJson(TestData2, Message[].class);

		return messageArray;
	}
}
