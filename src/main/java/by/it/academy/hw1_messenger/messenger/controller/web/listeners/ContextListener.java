package by.it.academy.hw1_messenger.messenger.controller.web.listeners;

import by.it.academy.hw1_messenger.messenger.view.MessagesLoader;
import by.it.academy.hw1_messenger.messenger.view.UserLoader;
import by.it.academy.hw1_messenger.messenger.view.MessengerService;
import by.it.academy.hw1_messenger.messenger.view.api.IMessengerService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

    private final IMessengerService service;
    private final static String FILE_PATH_INIT_USER_PARAM = "fileForSaveUsers";
    private final static String FILE_PATH_INIT_MESSAGES_PARAM = "fileForSaveMessages";

    public ContextListener() {
        this.service = MessengerService.getInstance();
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String fileForSavePersons = sce.getServletContext().getInitParameter(FILE_PATH_INIT_USER_PARAM);
        String fileForSaveMessages = sce.getServletContext().getInitParameter(FILE_PATH_INIT_MESSAGES_PARAM);
        new UserLoader(service).load(fileForSavePersons);
        new MessagesLoader(service).load(fileForSaveMessages);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        String fileForSavePersons = sce.getServletContext().getInitParameter(FILE_PATH_INIT_USER_PARAM);
        String fileForSaveMessages = sce.getServletContext().getInitParameter(FILE_PATH_INIT_MESSAGES_PARAM);
        new UserLoader(service).unLoad(fileForSavePersons);
        new MessagesLoader(service).unLoad(fileForSaveMessages);
    }
}
