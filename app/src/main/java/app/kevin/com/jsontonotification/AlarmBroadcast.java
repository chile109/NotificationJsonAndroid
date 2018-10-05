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
    private String Testdata = "{\"title\":\"晚餐服藥\",\"content\":\"紅包配溫開水\", \"date\":\"2018-03-17 19:55:30:033\"}";

    @Override
    public void onReceive(Context context, Intent intent) {
        if ("startAlarm".equals(intent.getAction())) {
            Toast.makeText(context, "闹钟提醒", Toast.LENGTH_LONG).show();
            // 处理闹钟事件
            // 振动、响铃、或者跳转页面等
            NotificationChannel channelLove = new NotificationChannel(
                    idLove,
                    "Channel Love",
                    NotificationManager.IMPORTANCE_HIGH);
            channelLove.setDescription("最重要的人");
            channelLove.enableLights(true);
            channelLove.enableVibration(true);

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channelLove);

            Notification.Builder builder =
                    new Notification.Builder(context)
                            .setSmallIcon(R.drawable.ic_launcher_background)
                            .setContentTitle(JsonToObject(Testdata).title)
                            .setContentText(JsonToObject(Testdata).content)
                            .setChannelId(idLove);
            notificationManager.notify(1, builder.build());
        }
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
