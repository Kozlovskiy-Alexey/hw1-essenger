package by.it.academy.hw1_messenger.messenger.controller.mains;

import by.it.academy.hw1_messenger.messenger.model.Message;
import by.it.academy.hw1_messenger.messenger.storage.hibernate.HibernateChatStorage;
import by.it.academy.hw1_messenger.messenger.storage.hibernate.api.entity.MessageEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class HibernateChatStorageMain2 {
    public static void main(String[] args) {

        HibernateChatStorage chatStorage = HibernateChatStorage.getInstance();
        Message message = Message.Builder.createBuilder()
                .setFromLogin("Alex")
                .setDateTime(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .setTextMessage("Hello from Alex")
                .build();

        chatStorage.addMessage("Vic", message);
        System.out.println(chatStorage.getCount());

    }
}
