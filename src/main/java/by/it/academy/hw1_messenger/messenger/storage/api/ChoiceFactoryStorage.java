package by.it.academy.hw1_messenger.messenger.storage.api;

import by.it.academy.hw1_messenger.messenger.storage.hibernate.api.HibernateFactoryStorage;

public class ChoiceFactoryStorage implements IFactoryStorage {

    private static volatile ChoiceFactoryStorage instance;
    private final IFactoryStorage factoryStorage;

    private ChoiceFactoryStorage() {
        factoryStorage = new HibernateFactoryStorage();
    }

    @Override
    public IChatStorage getIChatStorage() {
        return factoryStorage.getIChatStorage();
    }

    @Override
    public IUserStorage getIUserStorage() {
        return factoryStorage.getIUserStorage();
    }

    public static ChoiceFactoryStorage getInstance() {
        ChoiceFactoryStorage result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ChoiceFactoryStorage.class) {
            if (instance == null) {
                instance = new ChoiceFactoryStorage();
            }
        }
        return instance;
    }
}
