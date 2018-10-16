package app.kevin.com.jsontonotification;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Message implements Serializable{
        public String title;
        public String content;
        public Date date;

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
