package by.it.academy.hw1_messenger.messenger.controller.web.servlets;

import by.it.academy.hw1_messenger.messenger.model.User;
import by.it.academy.hw1_messenger.messenger.storage.api.IStorageService;
import by.it.academy.hw1_messenger.messenger.storage.SessionStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SignInSuccessServlet", urlPatterns = "/messenger/userPage")
public class SignInSuccessServlet extends HttpServlet {

    private final IStorageService storageService;
    private final static String USER_ATTRIBUTE_NAME = "user";

    public SignInSuccessServlet() {
        this.storageService = new SessionStorage();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        User user = storageService.getFromStorage(req);
        req.setAttribute(USER_ATTRIBUTE_NAME, (user.getFirstName() + " " + user.getLastName()));
        req.getRequestDispatcher("/views/signIn-success-page.jsp").forward(req, resp);
    }
}
