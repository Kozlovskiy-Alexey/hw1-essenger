package by.it.academy.hw1_messenger.messenger.service;

import by.it.academy.hw1_messenger.messenger.dto.Person;
import by.it.academy.hw1_messenger.messenger.service.api.IStorageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionStorage implements IStorageService {

    private final static String PERSON_ATTRIBUTE_NAME = "person";
    private final static int SESSION_MAX_INACTIVE_INTERVAL = 3000;

    @Override
    public void addToStorage(Person person, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(SESSION_MAX_INACTIVE_INTERVAL);
        session.setAttribute(PERSON_ATTRIBUTE_NAME, person);
    }

    @Override
    public Person getFromStorage(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (Person) session.getAttribute(PERSON_ATTRIBUTE_NAME);
    }
}
