package by.it.academy.hw1_messenger.messenger.model;

import java.time.LocalDateTime;

public class AuditUser {
    private long id;
    private LocalDateTime dtCreate;
    private String text;
    private User author;
    private User user;

    public AuditUser(LocalDateTime dtCreate, String text, User author, User user) {
        this.dtCreate = dtCreate;
        this.text = text;
        this.author = author;
        this.user = user;
    }

    public AuditUser(long id, LocalDateTime dtCreate, String text, User author, User user) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.text = text;
        this.author = author;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
        return "AuditUser{" +
                "id=" + id +
                ", dtCreate=" + dtCreate +
                ", text='" + text + '\'' +
                ", author=" + author +
                ", user=" + user +
                '}';
    }
}
