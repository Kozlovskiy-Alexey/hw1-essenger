package by.it.academy.hw1_messenger.messenger.controller.web.servlets;

import by.it.academy.hw1_messenger.messenger.model.Message;
import by.it.academy.hw1_messenger.messenger.model.User;
import by.it.academy.hw1_messenger.messenger.view.MessengerService;
import by.it.academy.hw1_messenger.messenger.view.MessengerServiceDAO;
import by.it.academy.hw1_messenger.messenger.view.api.IMessengerService;
import by.it.academy.hw1_messenger.messenger.storage.api.IStorageService;
import by.it.academy.hw1_messenger.messenger.storage.SessionStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ChatServlet", urlPatterns = "/messenger/chats")
public class ChatServlet extends HttpServlet {

    private final IMessengerService service;
    private final IStorageService storageService;

    public ChatServlet() {
        this.service = new MessengerServiceDAO();
        this.storageService = new SessionStorage();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        User user = storageService.getFromStorage(req);
        String loginTo = user.getLogin();
        List<Message> messageList = service.get(loginTo);
        if (messageList != null) {
            req.setAttribute("messages", messageList);
            req.getRequestDispatcher("/views/chats-page.jsp").forward(req, resp);
        } else {
            req.setAttribute("messageIsEmpty", "У вас нет сообщений.");
            req.getRequestDispatcher("/views/chats-page.jsp").forward(req, resp);
        }
    }
}
