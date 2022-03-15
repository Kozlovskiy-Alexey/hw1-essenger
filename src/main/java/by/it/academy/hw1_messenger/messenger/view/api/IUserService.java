package by.it.academy.hw1_messenger.messenger.view.api;

import by.it.academy.hw1_messenger.messenger.model.User;

import java.util.Collection;

public interface IUserService {

    User get(String login);
    void signUp(User user);
    Collection<User> getAll();
    long getCount();
}
