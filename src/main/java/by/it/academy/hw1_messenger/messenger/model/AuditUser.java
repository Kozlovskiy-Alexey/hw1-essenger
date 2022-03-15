package by.it.academy.hw1_messenger.messenger.model;

import java.time.LocalDateTime;

public class AuditUser {
    private long id;
    private LocalDateTime dtCreate;
    private String text;
    private User user;
    private User author;

    public AuditUser(long id, LocalDateTime dtCreate, String text, User user, User author) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.text = text;
        this.user = user;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public String getText() {
        return text;
    }

    public User getUser() {
        return user;
    }

    public User getAuthor() {
        return author;
    }
}
