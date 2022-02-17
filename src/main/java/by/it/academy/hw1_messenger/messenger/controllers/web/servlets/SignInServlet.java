package by.it.academy.hw1_messenger.messenger.controllers.web.servlets;

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

@WebServlet(name = "SignInServlet", urlPatterns = "/messenger/signIn")
public class SignInServlet extends HttpServlet {

    private final IMessengerService service;
    private final IStorageService storageService;

    public SignInServlet() {
        this.service = MessengerService.getInstance();
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

        Person person = service.signIn(login, password);

        if (person != null) {
            storageService.addToStorage(person, req);
            resp.sendRedirect(req.getContextPath() + "/messenger/userPage");
        } else {
            req.setAttribute("wrongValidation", "Неправильный логин или пароль!");
            req.getRequestDispatcher("/views/signIn-page.jsp").forward(req, resp);
        }
    }
}
