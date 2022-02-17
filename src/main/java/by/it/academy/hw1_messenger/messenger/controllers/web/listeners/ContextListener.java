package by.it.academy.hw1_messenger.messenger.controllers.web.listeners;

import by.it.academy.hw1_messenger.messenger.service.MessagesLoader;
import by.it.academy.hw1_messenger.messenger.service.PersonLoader;
import by.it.academy.hw1_messenger.messenger.service.MessengerService;
import by.it.academy.hw1_messenger.messenger.service.api.IMessengerService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

    private final IMessengerService service;
    private final static String FILE_PATH_INIT_PERSON_PARAM = "fileForSavePersons";
    private final static String FILE_PATH_INIT_MESSAGES_PARAM = "fileForSaveMessages";

    public ContextListener() {
        this.service = MessengerService.getInstance();
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String fileForSavePersons = sce.getServletContext().getInitParameter(FILE_PATH_INIT_PERSON_PARAM);
        String fileForSaveMessages = sce.getServletContext().getInitParameter(FILE_PATH_INIT_MESSAGES_PARAM);
        new PersonLoader(service).load(fileForSavePersons);
        new MessagesLoader(service).load(fileForSaveMessages);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        String fileForSavePersons = sce.getServletContext().getInitParameter(FILE_PATH_INIT_PERSON_PARAM);
        String fileForSaveMessages = sce.getServletContext().getInitParameter(FILE_PATH_INIT_MESSAGES_PARAM);
        new PersonLoader(service).unLoad(fileForSavePersons);
        new MessagesLoader(service).unLoad(fileForSaveMessages);
    }
}
