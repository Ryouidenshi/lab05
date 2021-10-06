package servlets;

import models.User;
import services.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = accountService.getUserBySessionId("sessionID-" + req.getRemoteAddr());
        if (user != null) {
            resp.sendRedirect("/lab02_war/files");
            return;
        }
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("pass");
        User user = accountService.getUserByLogin(login);
        if (user == null || !user.getPass().equals(password)) {
            resp.sendRedirect("/lab02_war/registration");
            return;
        }
        accountService.addSession(req.getSession().getId(), user);
        resp.sendRedirect("/lab02_war/files");
    }
}
