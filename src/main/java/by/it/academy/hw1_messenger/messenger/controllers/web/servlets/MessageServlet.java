package by.it.academy.hw1_messenger.messenger.controllers.web.servlets;

import by.it.academy.hw1_messenger.messenger.dto.Message;
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
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@WebServlet(name = "MessageServlet", urlPatterns = "/messenger/message")
public class MessageServlet extends HttpServlet {

    private final IMessengerService service;
    private final IStorageService storageService;

    public MessageServlet() {
        this.service = MessengerService.getInstance();
        this.storageService = new SessionStorage();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        req.getRequestDispatcher("/views/message-page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String loginTo = req.getParameter("login");
        String message = req.getParameter("message");

        if (loginTo.isEmpty()) {
            req.setAttribute("sendConfirmation", "Введите логин");
            req.getRequestDispatcher("/views/message-page.jsp").forward(req, resp);
        } else if (message.isEmpty()) {
            req.setAttribute("sendConfirmation", "Введите сообщение");
            req.getRequestDispatcher("/views/message-page.jsp").forward(req, resp);
        } else if (service.checkLoginIsFree(loginTo)) {
            req.setAttribute("sendConfirmation", ("Пользователь с логином " + loginTo + " не найден!"));
            req.getRequestDispatcher("/views/message-page.jsp").forward(req, resp);
        } else {
            String loginFrom = storageService.getFromStorage(req).getLogin();
            service.addMessage(loginTo, new Message(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), loginFrom, message));
            req.setAttribute("sendConfirmation", ("Сообщение успешно отправлено пользователю " + loginTo));
            req.getRequestDispatcher("/views/message-page.jsp").forward(req, resp);
        }
    }
}
