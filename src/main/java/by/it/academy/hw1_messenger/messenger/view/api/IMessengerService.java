package by.it.academy.hw1_messenger.messenger.view.api;

import by.it.academy.hw1_messenger.messenger.model.Message;
import by.it.academy.hw1_messenger.messenger.model.User;

import java.util.List;
import java.util.Map;

public interface IMessengerService {

    void addUser(User user);

    User signIn(String login, String password);

    boolean checkLoginIsFree(String login);

    void addMessage(String login, Message message);

    List<Message> get(String loginTo);

    List<User> getUserList();

    Map<String, List<Message>> getMessagesMap();

}
