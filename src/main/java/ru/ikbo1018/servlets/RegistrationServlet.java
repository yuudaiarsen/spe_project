package ru.ikbo1018.servlets;

import ru.ikbo1018.dao.AccountDao;
import ru.ikbo1018.dao.AccountDaoImpl;
import ru.ikbo1018.models.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.*;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json;charset=UTF-8");
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String midName = req.getParameter("mid_name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");

        Map<String, String> response = new HashMap<String, String>();

        if (firstName.length() == 0 || lastName.length() == 0 || email.length() == 0
                || password.length() == 0)
        {
            resp.setStatus(400);
            response.put("error", "Обязательные поля должны быть заполнены");
            response.put("code", "1");
            resp.getWriter().print(new Gson().toJson(response));
        }
        else
        {
            try
            {
                AccountDao accountDao = new AccountDaoImpl();
                Account account = accountDao.findByEmail(email);

                if(account == null)
                {
                    accountDao.create(new Account(firstName, lastName, midName, email, password, 0, phone, new Date(System.currentTimeMillis())));
                    resp.setStatus(200);
                    req.getSession().setAttribute("auth", Boolean.TRUE);
                    response.put("redirect", "/lk");
                    resp.getWriter().print(new Gson().toJson(response));
                }
                else
                {
                    resp.setStatus(400);
                    response.put("error", "Аккаунт с указанным адресом электронной почты уже существует");
                    response.put("code", "2");
                    resp.getWriter().print(new Gson().toJson(response));
                }
            }catch (SQLException e)
            {
                resp.setStatus(503);
            }
        }
    }
}
