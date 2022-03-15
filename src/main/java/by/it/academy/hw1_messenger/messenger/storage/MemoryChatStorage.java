package by.it.academy.hw1_messenger.messenger.storage;

import by.it.academy.hw1_messenger.messenger.model.Message;
import by.it.academy.hw1_messenger.messenger.storage.api.IChatStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryChatStorage implements IChatStorage {
    private static volatile MemoryChatStorage instance;
    private final Map<String, List<Message>> chats = new HashMap<>();

    private MemoryChatStorage() {

    }

    public MemoryChatStorage getInstance() {
        MemoryChatStorage result = instance;
        if (result != null) {
            return result;
        }
        synchronized (MemoryChatStorage.class) {
            if (instance == null) {
                instance = new MemoryChatStorage();
            }
        }
        return instance;
    }


    @Override
    public List<Message> get(String login) {
        return this.chats.get(login);
    }

    @Override
    public void addMessage(String login, Message message) {
        List<Message> chat;
        if (this.chats.containsKey(login)) {
            chat = chats.get(login);
        } else {
            chat = new ArrayList<>();
            this.chats.put(login, chat);
        }
        chat.add(message);
    }

    @Override
    public long getCount() {
        return this.chats.values().stream()
                .mapToInt(List::size)
                .sum();
    }
}
