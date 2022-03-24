package by.it.academy.hw1_messenger.messenger.controller.web.servlets;

import by.it.academy.hw1_messenger.messenger.model.AuditUser;
import by.it.academy.hw1_messenger.messenger.view.AuditService;
import by.it.academy.hw1_messenger.messenger.view.api.IAuditService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LogsServlet", urlPatterns = "/messenger/logs")
public class LogsServlet extends HttpServlet {

    private final IAuditService auditService;

    public LogsServlet() {
        this.auditService = AuditService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        List<AuditUser> auditUsers = auditService.get(null);
        if (auditUsers != null) {
            req.setAttribute("audit", auditUsers);
            req.getRequestDispatcher("/views/logs-page.jsp").forward(req, resp);
        } else {
            req.setAttribute("logs", "Нет записей logs");
            req.getRequestDispatcher("/views/logs-page.jsp").forward(req, resp);
        }
    }
}
