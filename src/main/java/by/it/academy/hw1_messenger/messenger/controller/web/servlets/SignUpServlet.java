package by.it.academy.hw1_messenger.messenger.controller.web.servlets;

import by.it.academy.hw1_messenger.messenger.model.User;
import by.it.academy.hw1_messenger.messenger.view.UserService;
import by.it.academy.hw1_messenger.messenger.view.api.IUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "SignUpServlet", urlPatterns = "/messenger/signUp")
public class SignUpServlet extends HttpServlet {

    private final IUserService service;

    public SignUpServlet() {
        this.service = UserService.getInstance();
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

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String birthday = req.getParameter("birthday");

        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBirthday(LocalDate.parse(birthday));

        try {
            this.service.signUp(user);
            req.getSession().setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/messenger/userPage");
        } catch (IllegalArgumentException e) {
            req.setAttribute("requiredParameter", e.getMessage());
            req.getRequestDispatcher("/views/signUp-page.jsp").forward(req, resp);
        }
    }
}
