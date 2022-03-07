package by.it.academy.hw1_messenger.messenger.controller.web.servlets;

import by.it.academy.hw1_messenger.messenger.model.User;
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

@WebServlet(name = "SignInServlet", urlPatterns = "/messenger/signIn")
public class SignInServlet extends HttpServlet {

    private final IMessengerService service;
    private final IStorageService storageService;

    public SignInServlet() {
        this.service = new MessengerServiceDAO();
        this.storageService = new SessionStorage();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        req.getRequestDispatcher("/views/signIn-page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login.isEmpty() || password.isEmpty()) {
            req.setAttribute("wrongValidation", "Не введен логин или пароль!");
            req.getRequestDispatcher("/views/signIn-page.jsp").forward(req, resp);
        }

        User user = service.signIn(login, password);

        if (user != null) {
            storageService.addToStorage(user, req);
            resp.sendRedirect(req.getContextPath() + "/messenger/userPage");
        } else {
            req.setAttribute("wrongValidation", "Неправильный логин или пароль!");
            req.getRequestDispatcher("/views/signIn-page.jsp").forward(req, resp);
        }
    }
}
