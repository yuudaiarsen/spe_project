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
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json;charset=UTF-8");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {
            AccountDao accountDao = new AccountDaoImpl();
            Account account = accountDao.findByEmail(email);
            if(account != null && password.equals(account.getPassword()))
            {
                resp.setStatus(HttpServletResponse.SC_OK);
                req.getSession().setAttribute("auth", Boolean.TRUE);
                req.getSession().setAttribute("account_id", account.getId());
                Map<String, String> res = new HashMap<String, String>();
                res.put("redirect", "/lk");
                resp.getWriter().print(new Gson().toJson(res));
            }
            else
            {
                resp.sendError(400);
            }
        }catch (SQLException e)
        {
            resp.sendError(503);
        }
    }
}
