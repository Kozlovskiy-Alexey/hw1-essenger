package by.it.academy.hw1_messenger.messenger.controller.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "TestServlet", urlPatterns = "/messenger/test")
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        HttpSession session = req.getSession();
        session.setMaxInactiveInterval(60);
        session.setAttribute("user", "userInSession");

        writer.write("<p>context path: " + req.getContextPath() + "</p>");
        writer.write("<p>" + "path info: " + req.getPathInfo() + "</p>");
        writer.write("<p>" + "request URI: " + req.getRequestURI() + "</p>");
        writer.write("<p>" + "servlet path: " + req.getServletPath() + "</p>");
        writer.write("<p>" + "query string: " + req.getQueryString() + "</p>");
        writer.write("<p>" + "session:  " + req.getSession() + "</p>");
        writer.write("<p>" + "auth type: " + req.getAuthType() + "</p>");
        writer.write("<p>" + "header: " + req.getHeader("user") + "</p>");
        writer.write("<p>" + "remote user: " + req.getRemoteUser() + "</p>");
        writer.write("<p>" + "method: " + req.getMethod() + "</p>");
        writer.write("<p>" + "request session id: " + req.getRequestedSessionId() + "</p>");
        writer.write("<p>" + "local address: " + req.getLocalAddr() + "</p>");
        writer.write("<p>" + "local name: " + req.getLocalName() + "</p>");
        writer.write("<p>" + "local port: " + req.getLocalPort() + "</p>");
        writer.write("<p>" + "protocol: " + req.getProtocol() + "</p>");
        writer.write("<p>" + "remote host: " + req.getRemoteHost() + "</p>");
        writer.write("<p>" + "scheme: " + req.getScheme() + "</p>");
        writer.write("<p>" + "servlet name: " + req.getServerName() + "</p>");
        writer.write("<p>" + "is Requested Session Id Valid: " + req.isRequestedSessionIdValid() + "</p>");
        writer.write("<p>" + "isUserInRole: " + req.isUserInRole("Alex") + "</p>");
        writer.write("<p> Данные из объекта сессии: </p>");
        writer.write("<p>" + "Session id from server: " + session.getId() + "</p>");
        writer.write("<p>" + "attributes names: " + session.getAttributeNames().toString() + "</p>");
        writer.write("<p>" + "creation time " + session.getCreationTime() + "</p>");
        writer.write("<p>" + "is new ? " + session.isNew() + "</p>");
        writer.write("<p>" + "max inactive interval " + session.getMaxInactiveInterval() + "</p>");
        writer.write("<p>" + "last access time " + session.getLastAccessedTime() + "</p>");
        writer.write("<p>" + "get attribute " + session.getAttribute("user") + "</p>");
    }
}
