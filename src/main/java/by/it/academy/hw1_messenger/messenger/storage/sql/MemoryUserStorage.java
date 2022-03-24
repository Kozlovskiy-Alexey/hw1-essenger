package by.it.academy.hw1_messenger.messenger.storage.sql;

import by.it.academy.hw1_messenger.messenger.model.User;
import by.it.academy.hw1_messenger.messenger.storage.api.IUserStorage;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MemoryUserStorage implements IUserStorage {

    private static volatile MemoryUserStorage instance;
    private final Map<String, User> users = new HashMap<>();

    private MemoryUserStorage() {
    }

    public static MemoryUserStorage getInstance() {
        MemoryUserStorage result = instance;
        if (result != null) {
            return instance;
        }
        synchronized (MemoryUserStorage.class) {
            if (instance == null) {
                instance = new MemoryUserStorage();
            }
        }
        return instance;
    }

    @Override
    public User get(String login) {
        return this.users.get(login);
    }

    @Override
    public void add(User user) {
        if (this.users.containsKey(user.getLogin())) {
            throw new IllegalArgumentException("Пользователь с логином " + user.getLogin() + " уже существует");
        }
        this.users.put(user.getLogin(), user);
    }

    @Override
    public Collection<User> getAll() {
        return this.users.values();
    }

    @Override
    public long getCount() {
        return users.size();
    }
}
