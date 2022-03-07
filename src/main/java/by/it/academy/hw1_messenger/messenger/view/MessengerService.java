package by.it.academy.hw1_messenger.messenger.view;

import by.it.academy.hw1_messenger.messenger.controller.dao.UserDAO;
import by.it.academy.hw1_messenger.messenger.controller.dao.api.IUserDAO;
import by.it.academy.hw1_messenger.messenger.model.Message;
import by.it.academy.hw1_messenger.messenger.model.User;
import by.it.academy.hw1_messenger.messenger.view.api.IMessengerService;

import java.util.*;

public class MessengerService implements IMessengerService {

    private final static MessengerService instance = new MessengerService();
    private List<User> userList;
    private Map<String, List<Message>> messages;
    private IUserDAO userDAO;

    private MessengerService() {
        this.userList = new ArrayList<>();
        this.messages = new HashMap<>();
        this.userDAO = new UserDAO();
    }

    public static MessengerService getInstance() {
        return instance;
    }

    @Override
    public void addUser(User user) {
        userList.add(user);
    }

    @Override
    public User signIn(String login, String password) {
        return userList.stream()
                .filter((s1) -> (s1.getLogin().equals(login) && s1.getPassword().equals(password)))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean checkLoginIsFree(String login) {

        return !userList.stream()
                .filter(s -> s.getLogin().equals(login))
                .findFirst()
                .isEmpty();
    }

    @Override
    public void addMessage(String fromLogin, Message message) {
        if (messages.containsKey(fromLogin)) {
            List<Message> messagesList = this.messages.get(fromLogin);
            messagesList.add(message);
            messages.put(fromLogin, messagesList);
        } else {
            List<Message> messageList = new ArrayList<>();
            messageList.add(message);
            messages.put(fromLogin, messageList);
        }
    }

    @Override
    public List<Message> get(String loginTo) {
        Map.Entry<String, List<Message>> stringListEntry = this.messages.entrySet()
                .stream()
                .filter(s -> s.getKey().equals(loginTo))
                .findFirst()
                .orElse(null);
        if (stringListEntry != null) {
            List<Message> value = stringListEntry.getValue();
            Collections.reverse(value);
            return value;
        } else return null;
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
