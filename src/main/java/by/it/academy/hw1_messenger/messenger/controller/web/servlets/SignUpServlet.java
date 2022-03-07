package by.it.academy.hw1_messenger.messenger.controller.web.servlets;

import by.it.academy.hw1_messenger.messenger.controller.dao.UserDAO;
import by.it.academy.hw1_messenger.messenger.controller.dao.api.IUserDAO;
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
import java.time.LocalDate;

@WebServlet(name = "SignUpServlet", urlPatterns = "/messenger/signUp")
public class SignUpServlet extends HttpServlet {

    private final IMessengerService service;
    private final IStorageService sessionStorage;
    private final static String LOGIN_PARAMETER = "login";
    private final static String PASSWORD_PARAMETER = "password";
    private final static String FIRST_NAME_PARAMETER = "firstName";
    private final static String LAST_NAME_PARAMETER = "lastName";
    private final static String BIRTHDAY_PARAMETER = "birthday";

    public SignUpServlet() {
        this.service = new MessengerServiceDAO();
        this.sessionStorage = new SessionStorage();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        req.getRequestDispatcher("/views/signUp-page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String login = req.getParameter(LOGIN_PARAMETER);
        String password = req.getParameter(PASSWORD_PARAMETER);
        String firstName = req.getParameter(FIRST_NAME_PARAMETER);
        String lastName = req.getParameter(LAST_NAME_PARAMETER);
        String birthday = req.getParameter(BIRTHDAY_PARAMETER);

        if (login.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || birthday.isEmpty()) {
            req.setAttribute("requiredParameter", "Все поля должны быть заполнены!");
            req.getRequestDispatcher("/views/signUp-page.jsp").forward(req, resp);
        } else if (!service.checkLoginIsFree(login)) {
            req.setAttribute("requiredParameter", "Пользователь с логином " + login + " уже существует!");
            req.getRequestDispatcher("/views/signUp-page.jsp").forward(req, resp);
        } else {
            User user = new User(login, password, firstName, lastName, LocalDate.parse(birthday));
            service.addUser(user);
            sessionStorage.addToStorage(user, req);
            resp.sendRedirect(req.getContextPath() + "/messenger/userPage");
        }
    }
}
