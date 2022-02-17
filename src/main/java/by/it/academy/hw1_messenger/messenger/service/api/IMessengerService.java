package by.it.academy.hw1_messenger.messenger.service.api;

import by.it.academy.hw1_messenger.messenger.dto.Message;
import by.it.academy.hw1_messenger.messenger.dto.Person;

import java.util.List;
import java.util.Map;

public interface IMessengerService {

    void addPerson(Person person);

    Person signIn(String login, String password);

    boolean checkLoginIsFree(String login);

    void addMessage(String fromLogin, Message message);

    List<Message> getMessagesToPerson(String loginTo);

    List<Person> getPersonList();

    Map<String, List<Message>> getMessagesMap();

}
