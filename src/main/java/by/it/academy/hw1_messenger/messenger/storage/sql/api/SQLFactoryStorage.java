package by.it.academy.hw1_messenger.messenger.storage.sql.api;

import by.it.academy.hw1_messenger.messenger.storage.api.IChatStorage;
import by.it.academy.hw1_messenger.messenger.storage.api.IFactoryStorage;
import by.it.academy.hw1_messenger.messenger.storage.api.IUserStorage;
import by.it.academy.hw1_messenger.messenger.storage.sql.DBChatStorage;
import by.it.academy.hw1_messenger.messenger.storage.sql.DBUserStorage;

public class SQLFactoryStorage implements IFactoryStorage {

    @Override
    public IChatStorage getIChatStorage() {
        return DBChatStorage.getInstance();
    }

    @Override
    public IUserStorage getIUserStorage() {
        return DBUserStorage.getInstance();
    }
}
