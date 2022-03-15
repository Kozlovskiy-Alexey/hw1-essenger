package by.it.academy.hw1_messenger.messenger.view;

import by.it.academy.hw1_messenger.messenger.model.User;
import by.it.academy.hw1_messenger.messenger.view.api.IAuthService;
import by.it.academy.hw1_messenger.messenger.view.api.IUserService;

import java.util.Objects;

public class AuthService implements IAuthService {
    private static volatile AuthService instance;
    private final IUserService userService;

    private AuthService() {
        this.userService = UserService.getInstance();
    }

    public static AuthService getInstance() {
        AuthService result = instance;
        if (result != null) {
            return result;
        }
        synchronized (AuthService.class) {
            if (instance == null) {
                instance = new AuthService();
            }
        }
        return instance;
    }

    @Override
    public User authentication(String login, String password) {
        User user = this.userService.get(login);
        if (user == null) {
            return null;
        }
        if (!Objects.equals(user.getPassword(), password)) {
            return null;
        }
        return user;
    }
}
