package by.it.academy.hw1_messenger.messenger.storage.hibernate.api;

import by.it.academy.hw1_messenger.messenger.storage.api.IChatStorage;
import by.it.academy.hw1_messenger.messenger.storage.api.IFactoryStorage;
import by.it.academy.hw1_messenger.messenger.storage.api.IUserStorage;
import by.it.academy.hw1_messenger.messenger.storage.hibernate.HibernateChatStorage;
import by.it.academy.hw1_messenger.messenger.storage.hibernate.HibernateUserStorage;

public class HibernateFactoryStorage implements IFactoryStorage {

    @Override
    public IChatStorage getIChatStorage() {
        return HibernateChatStorage.getInstance();
    }

    @Override
    public IUserStorage getIUserStorage() {
        return HibernateUserStorage.getInstance();
    }
}
