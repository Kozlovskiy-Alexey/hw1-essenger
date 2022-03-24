package by.it.academy.hw1_messenger.messenger.controller.web.servlets;

import by.it.academy.hw1_messenger.messenger.model.Message;
import by.it.academy.hw1_messenger.messenger.model.User;
import by.it.academy.hw1_messenger.messenger.view.MessageService;
import by.it.academy.hw1_messenger.messenger.view.api.IMessageService;

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

    private final IMessageService messageService;

    public MessageServlet() {
    this.messageService = MessageService.getInstance();
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
        String messageText = req.getParameter("message");
        User user = (User) req.getSession().getAttribute("user");
        Message message = new Message(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), user.getLogin(), messageText);

        if (loginTo.isEmpty()) {
            req.setAttribute("sendConfirmation", "Введите логин");
            req.getRequestDispatcher("/views/message-page.jsp").forward(req, resp);
        } else if (messageText.isEmpty()) {
            req.setAttribute("sendConfirmation", "Введите сообщение");
            req.getRequestDispatcher("/views/message-page.jsp").forward(req, resp);
        }
        try {
            messageService.addMessage(loginTo, message);
            req.setAttribute("sendConfirmation", ("Сообщение успешно отправлено пользователю " + loginTo));
            req.getRequestDispatcher("/views/message-page.jsp").forward(req, resp);
        } catch (IllegalArgumentException e) {
            req.setAttribute("sendConfirmation", e.getMessage());
            req.getRequestDispatcher("/views/message-page.jsp").forward(req, resp);
        }
    }
}
