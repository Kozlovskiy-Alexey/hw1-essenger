package by.it.academy.hw1_messenger.messenger.view;

import by.it.academy.hw1_messenger.messenger.model.User;
import by.it.academy.hw1_messenger.messenger.storage.api.ChoiceFactoryStorage;
import by.it.academy.hw1_messenger.messenger.storage.sql.DBUserStorage;
import by.it.academy.hw1_messenger.messenger.storage.api.IUserStorage;
import by.it.academy.hw1_messenger.messenger.view.api.IAuditService;
import by.it.academy.hw1_messenger.messenger.view.api.IMessageService;
import by.it.academy.hw1_messenger.messenger.view.api.IUserService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

public class UserService implements IUserService {
    private static volatile UserService instance;
    private final IUserStorage userStorage;
    private final IMessageService messageService;
    private final IAuditService auditService;

    private UserService() {
        this.userStorage = ChoiceFactoryStorage.getInstance().getIUserStorage();
        this.messageService = MessageService.getInstance();
        this.auditService = AuditService.getInstance();
    }

    public static UserService getInstance() {
        UserService result = instance;
        if (result != null) {
            return result;
        }
        synchronized (UserService.class) {
            if (instance == null) {
                instance = new UserService();
            }
        }
        return instance;
    }

    @Override
    public User get(String login) {
        return this.userStorage.get(login);
    }

    @Override
    public void signUp(User user) {
        this.validationForSignUp(user);
        user.setRegistration(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        userStorage.add(user);
        auditService.regAudit(user);
    }

    private void validationForSignUp(User user) {
        String errorMessage = "";
        if (this.nullOrEmpty(user.getLogin())) {
            errorMessage += "Логин обязателен для заполеннения";
        }
        if (this.nullOrEmpty(user.getPassword())) {
            if (!errorMessage.isEmpty()) {
                errorMessage += "; ";
            }
            errorMessage += "Пароль обязателен для заполенния";
        }
        if (this.nullOrEmpty(user.getFirstName())) {
            if (!errorMessage.isEmpty()) {
                errorMessage += "; ";
            }
            errorMessage += "Имя обязательно для заполнения";
        }
        if (this.nullOrEmpty(user.getLastName())) {
            if (!errorMessage.isEmpty()) {
                errorMessage += "; ";
            }
            errorMessage += "Фамилия обязательна для заполнения";
        }
        if (!errorMessage.isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private boolean nullOrEmpty(String val) {
        return val == null || val.isEmpty();
    }

    @Override
    public Collection<User> getAll() {
        return this.userStorage.getAll();
    }

    @Override
    public long getCount() {
        return this.userStorage.getCount();
    }
}
