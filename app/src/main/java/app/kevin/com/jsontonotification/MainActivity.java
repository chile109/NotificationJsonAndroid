package app.kevin.com.jsontonotification;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

	public static final String idLove = "default";
	private String Testdata = "{\"title\":\"晚餐服藥\",\"content\":\"紅包配溫開水\", \"date\":\"2018-03-17 19:55:30:033\"}";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		NotificationChannel channelLove = new NotificationChannel(
				idLove,
				"Channel Love",
				NotificationManager.IMPORTANCE_HIGH);
		channelLove.setDescription("最重要的人");
		channelLove.enableLights(true);
		channelLove.enableVibration(true);

		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notificationManager.createNotificationChannel(channelLove);

		Notification.Builder builder =
				new Notification.Builder(this)
						.setSmallIcon(R.drawable.ic_launcher_background)
						.setContentTitle(JsonToObject(Testdata).title)
						.setContentText(JsonToObject(Testdata).content)
						.setChannelId(idLove);
		notificationManager.notify(1, builder.build());


	}

	private Message JsonToObject(String data) {
		Gson gson = new GsonBuilder()
				.setPrettyPrinting()//格式化输出
				.setDateFormat("yyyy-MM-dd HH:mm:ss:SSS")//格式化时间
				.create();

		Message m = gson.fromJson(data, Message.class);
		System.out.println();
		System.out.println(m);

		return m;
	}

	class Message {
		private String title;
		private String content;
		private Date date;

		public Message(String title, String contenxt, Date date) {
			this.title = title;
			this.content = contenxt;
			this.date = date;
		}

		@Override
		public String toString() {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS", Locale.CHINESE);
			return "Data{" +
					"title=" + title +
					", content=" + content +
					", date=" + simpleDateFormat.format(date) +
					'}';
		}

	}
}
