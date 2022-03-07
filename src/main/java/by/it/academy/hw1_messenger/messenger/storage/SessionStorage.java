package by.it.academy.hw1_messenger.messenger.storage;

import by.it.academy.hw1_messenger.messenger.model.User;
import by.it.academy.hw1_messenger.messenger.storage.api.IStorageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionStorage implements IStorageService {

    private final static String USER_ATTRIBUTE_NAME = "user";
    private final static int SESSION_MAX_INACTIVE_INTERVAL = 3000;

    @Override
    public void addToStorage(User user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(SESSION_MAX_INACTIVE_INTERVAL);
        session.setAttribute(USER_ATTRIBUTE_NAME, user);
    }

    @Override
    public User getFromStorage(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (User) session.getAttribute(USER_ATTRIBUTE_NAME);
    }
}
