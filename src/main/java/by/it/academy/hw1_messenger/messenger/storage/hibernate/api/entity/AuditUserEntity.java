package by.it.academy.hw1_messenger.messenger.storage.hibernate.api.entity;

import by.it.academy.hw1_messenger.messenger.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(schema = "messenger", name = "audit_user")
public class AuditUserEntity {
    @Id
    private long id;
    private LocalDateTime dt_create;
    private String textMessage;

    private User author;

    private User user;

    public AuditUserEntity() {

    }

    public AuditUserEntity(long id, LocalDateTime dt_create, String textMessage, User author, User user) {
        this.id = id;
        this.dt_create = dt_create;
        this.textMessage = textMessage;
        this.author = author;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDt_create() {
        return dt_create;
    }

    public void setDt_create(LocalDateTime dt_create) {
        this.dt_create = dt_create;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "AuditUserEntity{" +
                "id=" + id +
                ", dt_create=" + dt_create +
                ", textMessage='" + textMessage + '\'' +
                ", author=" + author +
                ", user=" + user +
                '}';
    }
}
