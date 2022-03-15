package by.it.academy.hw1_messenger.messenger.view.api;

import by.it.academy.hw1_messenger.messenger.model.User;

public interface IAuthService {
    User authentication(String login, String password);
}
