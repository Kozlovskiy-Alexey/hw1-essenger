package by.it.academy.hw1_messenger.messenger.storage.api;

public interface IFactoryStorage {

    IChatStorage getIChatStorage();

    IUserStorage getIUserStorage();

}
