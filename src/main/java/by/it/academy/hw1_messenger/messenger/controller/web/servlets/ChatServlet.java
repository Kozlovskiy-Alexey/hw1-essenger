package by.it.academy.hw1_messenger.messenger.controller.web.servlets;

import by.it.academy.hw1_messenger.messenger.model.Message;
import by.it.academy.hw1_messenger.messenger.model.User;
import by.it.academy.hw1_messenger.messenger.storage.DBChatStorage;
import by.it.academy.hw1_messenger.messenger.storage.api.IChatStorage;
import by.it.academy.hw1_messenger.messenger.view.MessageService;
import by.it.academy.hw1_messenger.messenger.view.api.IMessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ChatServlet", urlPatterns = "/messenger/chats")
public class ChatServlet extends HttpServlet {

    private final IMessageService messageService;

    public ChatServlet() {
        this.messageService = MessageService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        User user = (User) req.getSession().getAttribute("user");
        String loginTo = user.getLogin();
        List<Message> messageList = messageService.get(user);
        if (messageList != null) {
            req.setAttribute("messages", messageList);
            req.getRequestDispatcher("/views/chats-page.jsp").forward(req, resp);
        } else {
            req.setAttribute("messageIsEmpty", "У вас нет сообщений.");
            req.getRequestDispatcher("/views/chats-page.jsp").forward(req, resp);
        }
    }
}
