package by.it.academy.hw1_messenger.messenger.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Message implements Serializable {

    private LocalDateTime dateTime;
    private String fromLogin;
    private String textMessage;

    public Message(LocalDateTime dateTime, String fromLogin, String text) {
        this.dateTime = dateTime;
        this.fromLogin = fromLogin;
        this.textMessage = text;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getFromLogin() {
        return fromLogin;
    }

    public String getTextMessage() {
        return textMessage;
    }

    @Override
    public String toString() {
        return "Message{" +
                "dateTime=" + dateTime +
                ", fromLogin='" + fromLogin + '\'' +
                ", message='" + textMessage + '\'' +
                '}';
    }

    public static class Builder {
        private LocalDateTime dateTime;
        private String fromLogin;
        private String textMessage;

        private Builder() {
        }

        public Builder setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public Builder setFromLogin(String fromLogin) {
            this.fromLogin = fromLogin;
            return this;
        }

        public Builder setTextMessage(String textMessage) {
            this.textMessage = textMessage;
            return this;
        }

        public static Builder createBuilder() {
            return new Builder();
        }

        public  Message build() {
            return new Message(dateTime, fromLogin, textMessage);
        }
    }
}
