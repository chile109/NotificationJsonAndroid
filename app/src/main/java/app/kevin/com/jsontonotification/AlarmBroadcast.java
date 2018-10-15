package app.kevin.com.jsontonotification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AlarmBroadcast extends BroadcastReceiver {
    public static final String idLove = "default";


    @Override
    public void onReceive(Context context, Intent intent) {
        if ("startAlarm".equals(intent.getAction())) {
            Toast.makeText(context, "闹钟提醒", Toast.LENGTH_LONG).show();
            // 处理闹钟事件
            // 振动、响铃、或者跳转页面等
            NotificationChannel channelLove = new NotificationChannel(
                    idLove,
                    "Channel Dinner",
                    NotificationManager.IMPORTANCE_HIGH);
            channelLove.setDescription("晚間");
            channelLove.enableLights(true);
            channelLove.enableVibration(true);

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channelLove);

            Notification.Builder builder =
                    new Notification.Builder(context)
                            .setSmallIcon(R.drawable.ic_launcher_background)
                            .setContentTitle("wfwefw")
                            .setContentText("fwefwefwefw")
                            .setChannelId(idLove);
            notificationManager.notify(1, builder.build());
        }
    }


}
