package by.it.academy.hw1_messenger.messenger.storage.api;

import by.it.academy.hw1_messenger.messenger.model.User;

import javax.servlet.http.HttpServletRequest;

public interface IStorageService {

    void addToStorage(User user, HttpServletRequest request);

    User getFromStorage(HttpServletRequest request);

}
