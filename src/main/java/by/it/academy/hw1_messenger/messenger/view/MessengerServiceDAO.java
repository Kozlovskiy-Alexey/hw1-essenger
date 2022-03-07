package by.it.academy.hw1_messenger.messenger.view;

import by.it.academy.hw1_messenger.messenger.controller.dao.MessageDAO;
import by.it.academy.hw1_messenger.messenger.controller.dao.UserDAO;
import by.it.academy.hw1_messenger.messenger.controller.dao.api.IMessageDAO;
import by.it.academy.hw1_messenger.messenger.controller.dao.api.IUserDAO;
import by.it.academy.hw1_messenger.messenger.model.Message;
import by.it.academy.hw1_messenger.messenger.model.User;
import by.it.academy.hw1_messenger.messenger.view.api.IMessengerService;

import java.util.*;

public class MessengerServiceDAO implements IMessengerService {


    private List<User> userList;
    private Map<String, List<Message>> messages;
    private IUserDAO userDAO;
    private IMessageDAO messageDAO;

    public MessengerServiceDAO() {
        this.userList = new ArrayList<>();
        this.messages = new HashMap<>();
        this.userDAO = new UserDAO();
        this.messageDAO = new MessageDAO();
    }

       @Override
    public void addUser(User user) {
        userDAO.createUser(user);
    }

    @Override
    public User signIn(String login, String password) {
        return userDAO.getUser(login, password);
    }

    @Override
    public boolean checkLoginIsFree(String login) {
        return !userDAO.checkUser(login);
    }

    @Override
    public void addMessage(String loginTo, Message message) {
       messageDAO.insertMessage(loginTo, message);
    }

    @Override
    public List<Message> get(String loginTo) {
        return messageDAO.getMessage(loginTo);
    }

    @Override
    public List<User> getUserList() {
        return Collections.unmodifiableList(this.userList);
    }

    @Override
    public Map<String, List<Message>> getMessagesMap() {
        return Collections.unmodifiableMap(this.messages);
    }
}
