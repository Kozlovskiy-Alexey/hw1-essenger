package by.it.academy.hw1_messenger.messenger.service;

import by.it.academy.hw1_messenger.messenger.dto.Message;
import by.it.academy.hw1_messenger.messenger.dto.Person;
import by.it.academy.hw1_messenger.messenger.service.api.IMessengerService;

import java.util.*;

public class MessengerService implements IMessengerService {

    private final static MessengerService instance = new MessengerService();
    private List<Person> personList;
    private Map<String, List<Message>> messages;

    private MessengerService() {
        this.personList = new ArrayList<>();
        this.messages = new HashMap<>();
    }

    public static MessengerService getInstance() {
        return instance;
    }

    @Override
    public void addPerson(Person person) {
        personList.add(person);
    }

    @Override
    public Person signIn(String login, String password) {
        return personList.stream()
                .filter((s1) -> (s1.getLogin().equals(login) && s1.getPassword().equals(password)))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean checkLoginIsFree(String login) {
        return personList.stream()
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
    public List<Message> getMessagesToPerson(String loginTo) {
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
    public List<Person> getPersonList() {
        return Collections.unmodifiableList(this.personList);
    }

    @Override
    public Map<String, List<Message>> getMessagesMap() {
        return Collections.unmodifiableMap(this.messages);
    }
}
