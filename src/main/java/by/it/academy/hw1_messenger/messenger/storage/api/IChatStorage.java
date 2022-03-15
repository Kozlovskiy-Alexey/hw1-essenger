package by.it.academy.hw1_messenger.messenger.storage.api;

import by.it.academy.hw1_messenger.messenger.model.Message;

import java.util.List;

public interface IChatStorage {
    List<Message> get(String login);

    void addMessage(String login, Message message);

    long getCount();
}
