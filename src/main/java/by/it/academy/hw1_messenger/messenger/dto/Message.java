package by.it.academy.hw1_messenger.messenger.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Message implements Serializable {

    private LocalDateTime dateTime;
    private String fromLogin;
    private String message;

    public Message(LocalDateTime dateTime, String fromLogin, String message) {
        this.dateTime = dateTime;
        this.fromLogin = fromLogin;
        this.message = message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getFromLogin() {
        return fromLogin;
    }

    public String getMessage() {
        return message;
    }

}
