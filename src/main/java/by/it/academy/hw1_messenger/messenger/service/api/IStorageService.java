package by.it.academy.hw1_messenger.messenger.service.api;

import by.it.academy.hw1_messenger.messenger.dto.Person;

import javax.servlet.http.HttpServletRequest;

public interface IStorageService {

    void addToStorage(Person person, HttpServletRequest request);

    Person getFromStorage(HttpServletRequest request);

}
