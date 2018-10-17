package app.kevin.com.jsontonotification;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Message implements Serializable{

    @SerializedName("title")
    public String title;

    @SerializedName("content")
    public String content;

    @SerializedName("date")
    public Date date;

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
