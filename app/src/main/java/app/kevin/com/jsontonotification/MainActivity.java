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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
	private AlarmManager alarmManager;
	private PendingIntent pendingIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();
		initAlarm();
	}

	// 初始化控件
	private void initView() {

		Button alarmBtn = (Button) findViewById(R.id.button);
		alarmBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 设置闹钟时间
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(System.currentTimeMillis());
				calendar.set(2012, 9, 15);        //在Calendar類別中月份的編號是由0~11
				calendar.set(Calendar.HOUR_OF_DAY, 17);
				calendar.set(Calendar.MINUTE, 1);
				calendar.set(Calendar.SECOND, 30);

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
		pendingIntent = PendingIntent.getBroadcast(this, 110, intent, PendingIntent.FLAG_CANCEL_CURRENT);

		// 取消闹钟
//        alarmManager.cancel(pendingIntent);
	}

	// 设置闹钟
	private void setAlarm(Calendar calendar) {
//        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (1000 * 5), pendingIntent);
		alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

		Toast.makeText(this, "设置成功", Toast.LENGTH_SHORT).show();

	}
}
