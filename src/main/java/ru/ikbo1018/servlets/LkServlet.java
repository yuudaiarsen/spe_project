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

@WebServlet("/lk")
public class LkServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("auth") == Boolean.TRUE)
        {
            resp.getWriter().println("yes");
        }
        else
        {
            resp.sendRedirect("/index");
        }
    }
}
