package by.it.academy.hw1_messenger.messenger.controllers.web.servlets;

import by.it.academy.hw1_messenger.messenger.dto.Message;
import by.it.academy.hw1_messenger.messenger.dto.Person;
import by.it.academy.hw1_messenger.messenger.service.MessengerService;
import by.it.academy.hw1_messenger.messenger.service.api.IMessengerService;
import by.it.academy.hw1_messenger.messenger.service.api.IStorageService;
import by.it.academy.hw1_messenger.messenger.service.SessionStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "ChatServlet", urlPatterns = "/messenger/chats")
public class ChatServlet extends HttpServlet {

    private final IMessengerService service;
    private final IStorageService storageService;

    public ChatServlet() {
        this.service = MessengerService.getInstance();
        this.storageService = new SessionStorage();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        Person person = storageService.getFromStorage(req);
        String loginTo = person.getLogin();
        List<Message> messageList = service.getMessagesToPerson(loginTo);
        if (messageList != null) {
            req.setAttribute("messages", messageList);
            req.getRequestDispatcher("/views/chats-page.jsp").forward(req, resp);
        } else {
            req.setAttribute("messageIsEmpty", "У вас нет сообщений.");
            req.getRequestDispatcher("/views/chats-page.jsp").forward(req, resp);
        }
    }
}
