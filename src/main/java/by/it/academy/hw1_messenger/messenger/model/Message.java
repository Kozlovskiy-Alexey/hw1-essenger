package by.it.academy.hw1_messenger.messenger.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Message implements Serializable {

    private LocalDateTime dateTime;
    private String fromLogin;
    private String text;

    public Message(LocalDateTime dateTime, String fromLogin, String text) {
        this.dateTime = dateTime;
        this.fromLogin = fromLogin;
        this.text = text;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getFromLogin() {
        return fromLogin;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Message{" +
                "dateTime=" + dateTime +
                ", fromLogin='" + fromLogin + '\'' +
                ", message='" + text + '\'' +
                '}';
    }
}
