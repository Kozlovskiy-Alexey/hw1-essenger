package by.it.academy.hw1_messenger.messenger.controller.mains;

import by.it.academy.hw1_messenger.messenger.model.Message;
import by.it.academy.hw1_messenger.messenger.model.User;
import by.it.academy.hw1_messenger.messenger.storage.DBChatStorage;
import by.it.academy.hw1_messenger.messenger.storage.api.IChatStorage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MessengerMain2 {
    public static void main(String[] args) {

        User user = new User("Vov1", "123", "Vladimir", "Tarasov", LocalDate
                .of(1984, 01, 21), LocalDateTime.now());


        Message message = new Message(LocalDateTime.now(), "Alex", "Hello !!!");
        Message message1 = new Message(LocalDateTime.now(), "Vic", "Hello !!!");

        IChatStorage chatStorage = DBChatStorage.getInstance();
        List<Message> messages = chatStorage.get("Alex");
        messages.forEach(System.out::println);
//        long count = chatStorage.getCount();
//        System.out.println(count);
//        chatStorage.addMessage("Vov1", message);

    }
}
