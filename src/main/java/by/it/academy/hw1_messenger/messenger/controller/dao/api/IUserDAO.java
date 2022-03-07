package by.it.academy.hw1_messenger.messenger.controller.dao.api;

import by.it.academy.hw1_messenger.messenger.model.User;

public interface IUserDAO {

    int createUser(User user);

    User getUser(String login, String password);

    boolean checkUser(String login);

}
