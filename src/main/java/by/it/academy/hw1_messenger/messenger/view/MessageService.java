package by.it.academy.hw1_messenger.messenger.view;

import by.it.academy.hw1_messenger.messenger.model.Message;
import by.it.academy.hw1_messenger.messenger.model.User;
import by.it.academy.hw1_messenger.messenger.storage.DBChatStorage;
import by.it.academy.hw1_messenger.messenger.storage.api.IChatStorage;
import by.it.academy.hw1_messenger.messenger.view.api.IMessageService;

import java.util.List;

public class MessageService implements IMessageService {
    private static volatile MessageService instance;
    private final IChatStorage chatStorage;

    private MessageService() {
        this.chatStorage = DBChatStorage.getInstance();
    }

    public static MessageService getInstance() {
        MessageService result = instance;
        if (result != null) {
            return result;
        }
        synchronized (MessageService.class) {
            if (instance == null) {
                instance = new MessageService();
            }
        }
        return instance;
    }

    @Override
    public List<Message> get(User currentUser) {
        return this.chatStorage.get(currentUser.getLogin());
    }

    @Override
    public void addMessage(String loginRecipient, Message message) {
        this.chatStorage.addMessage(loginRecipient, message);
    }

    @Override
    public void addMessage(User recipient, Message message) {
        this.addMessage(recipient.getLogin(), message);
    }

    @Override
    public long getCount() {
        return this.chatStorage.getCount();
    }
}
