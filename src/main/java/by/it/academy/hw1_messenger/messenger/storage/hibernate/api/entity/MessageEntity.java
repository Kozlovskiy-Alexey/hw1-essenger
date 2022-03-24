package by.it.academy.hw1_messenger.messenger.storage.hibernate.api.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(schema = "messenger", name = "message")
public class MessageEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String toLogin;
    private String fromLogin;
    private LocalDateTime dateTime;
    private String textMessage;

    public MessageEntity() {
    }

    public MessageEntity(String toLogin, String fromLogin, LocalDateTime dateTime, String textMessage) {
        this.toLogin = toLogin;
        this.fromLogin = fromLogin;
        this.dateTime = dateTime;
        this.textMessage = textMessage;
    }


    public String getToLogin() {
        return toLogin;
    }

    public void setToLogin(String toLogin) {
        this.toLogin = toLogin;
    }

    public String getFromLogin() {
        return fromLogin;
    }

    public void setFromLogin(String fromLogin) {
        this.fromLogin = fromLogin;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String message) {
        this.textMessage = message;
    }

    @Override
    public String toString() {
        return "MessageEntity{" +
                "id=" + id +
                ", toLogin='" + toLogin + '\'' +
                ", fromLogin='" + fromLogin + '\'' +
                ", dateTime=" + dateTime +
                ", message='" + textMessage + '\'' +
                '}';
    }

    public static class Builder {
        private String toLogin;
        private String fromLogin;
        private LocalDateTime dateTime;
        private String textMessage;

        private Builder() {

        }

        public Builder setToLogin(String toLogin) {
            this.toLogin = toLogin;
            return this;
        }

        public Builder setFromLogin(String fromLogin) {
            this.fromLogin = fromLogin;
            return this;
        }

        public Builder setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public Builder setTextMessage(String textMessage) {
            this.textMessage = textMessage;
            return this;
        }

        public static Builder createBuilder() {
            return new Builder();
        }

        public MessageEntity build() {
            return new MessageEntity(toLogin, fromLogin, dateTime, textMessage);
        }
    }
}
