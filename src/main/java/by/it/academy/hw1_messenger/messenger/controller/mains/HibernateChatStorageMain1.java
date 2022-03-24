package by.it.academy.hw1_messenger.messenger.controller.mains;

import by.it.academy.hw1_messenger.messenger.model.Message;
import by.it.academy.hw1_messenger.messenger.storage.hibernate.HibernateChatStorage;
import java.util.List;

public class HibernateChatStorageMain1 {
    public static void main(String[] args) {

        HibernateChatStorage chatStorage = HibernateChatStorage.getInstance();
        List<Message> messages = chatStorage.get("Alex");
        messages.forEach(System.out::println);
    }
}
