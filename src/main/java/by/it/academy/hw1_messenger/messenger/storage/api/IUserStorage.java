package by.it.academy.hw1_messenger.messenger.storage.api;

import by.it.academy.hw1_messenger.messenger.model.User;

import java.util.Collection;

public interface IUserStorage {
    User get(String login);

    void add(User user);

    Collection<User> getAll();

    long getCount();
}
