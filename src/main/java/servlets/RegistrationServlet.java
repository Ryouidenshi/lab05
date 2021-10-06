package servlets;

import models.User;
import services.AccountService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String firstPassword = req.getParameter("pass1");
        String secondPassword = req.getParameter("pass2");
        String email = req.getParameter("email");

        clearErrors(req);

        if (!checkErrors(req, login, firstPassword, secondPassword, email)) {
            User user = new User(login, firstPassword, email);
            accountService.addNewUser(user);
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else {
            req.setAttribute("login", login);
            req.setAttribute("pass1", firstPassword);
            req.setAttribute("pass2", secondPassword);
            req.setAttribute("email", email);
            req.getRequestDispatcher("registration.jsp").forward(req, resp);
        }
    }

    private boolean checkErrors(HttpServletRequest req, String login, String firstPassword, String secondPassword,
                                String email) {
        if (login == null || login.equals("")) {
            req.setAttribute("loginErr", "Поле не заполнено");
        } else if (firstPassword == null || firstPassword.equals("")) {
            req.setAttribute("pass1Err", "Поле не заполнено");
        } else if (secondPassword == null || secondPassword.equals("")) {
            req.setAttribute("pass2Err", "Поле не заполнено");
        } else if (email == null || email.equals("")) {
            req.setAttribute("emailErr", "Поле не заполнено");
        } else if (accountService.getUserByLogin(login) != null) {
            req.setAttribute("loginErr", "Данный логин уже существует");
        } else if (!firstPassword.equals(secondPassword)) {
            req.setAttribute("pass2Err", "Пароли не совпадают");
        } else
            return false;
        return true;
    }

    private void clearErrors(HttpServletRequest req) {
        req.setAttribute("loginErr", "");
        req.setAttribute("pass1Err", "");
        req.setAttribute("pass2Err", "");
        req.setAttribute("emailErr", "");
    }
}