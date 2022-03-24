package by.it.academy.hw1_messenger.messenger.controller.mains;

import by.it.academy.hw1_messenger.messenger.storage.api.ChoiceFactoryStorage;
import by.it.academy.hw1_messenger.messenger.storage.api.IChatStorage;
import by.it.academy.hw1_messenger.messenger.storage.api.IFactoryStorage;

public class FactoryStorageMain1 {
    public static void main(String[] args) {

        IFactoryStorage factoryStorage = ChoiceFactoryStorage.getInstance();
        IChatStorage chatStorage = factoryStorage.getIChatStorage();
    }
}
