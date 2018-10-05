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

public class MainActivity extends AppCompatActivity {

	public static final String idLove = "default";

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


		NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		notificationManager.createNotificationChannel(channelLove);

		Notification.Builder builder =
				new Notification.Builder(this)
						.setSmallIcon(R.drawable.ic_launcher_background)
						.setContentTitle("My Love")
						.setContentText("Hi, my love!")
						.setChannelId(idLove);
		notificationManager.notify(1, builder.build());

		JsonToTiltle();
	}

	private String JsonToTiltle()
	{
		String data = "{\"title\":\"晚餐服藥\",\"content\":\"紅包配溫開水\"}";
		Gson gson = new Gson();
		Message m = gson.fromJson(data, Message.class);
		System.out.println();
		System.out.println(m);

		return m.toString();
	}

	class Message {
		private String title;
		private String content;

		public Message(String title, String contenxt) {
			this.title = title;
			this.content = contenxt;
		}

		@Override
		public String toString() {
			return "Data{" +
					"title='" + title + '\'' +
					", content=" + content +
					'}';
		}

	}
}
