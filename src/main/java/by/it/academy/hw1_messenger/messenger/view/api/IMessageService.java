package by.it.academy.hw1_messenger.messenger.view.api;

import by.it.academy.hw1_messenger.messenger.model.Message;
import by.it.academy.hw1_messenger.messenger.model.User;

import java.util.List;

public interface IMessageService {
    List<Message> get(User currentUser);

    void addMessage(String loginRecipient, Message message);

    void addMessage(User recipient, Message message);

    long getCount();
}
