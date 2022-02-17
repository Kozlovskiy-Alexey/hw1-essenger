package by.it.academy.hw1_messenger.messenger.controllers.web.servlets;

import by.it.academy.hw1_messenger.messenger.dto.Person;
import by.it.academy.hw1_messenger.messenger.service.api.IStorageService;
import by.it.academy.hw1_messenger.messenger.service.SessionStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SignInSuccessServlet", urlPatterns = "/messenger/userPage")
public class SignInSuccessServlet extends HttpServlet {

    private final IStorageService storageService;
    private final static String PERSON_ATTRIBUTE_NAME = "person";

    public SignInSuccessServlet() {
        this.storageService = new SessionStorage();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        Person person = storageService.getFromStorage(req);
        req.setAttribute(PERSON_ATTRIBUTE_NAME, (person.getFirstName() + " " + person.getLastName()));
        req.getRequestDispatcher("/views/signIn-success-page.jsp").forward(req, resp);
    }
}
