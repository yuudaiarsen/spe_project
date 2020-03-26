package ru.ikbo1018.servlets;

import ru.ikbo1018.dao.AppealDao;
import ru.ikbo1018.dao.AppealDaoImpl;
import ru.ikbo1018.models.Appeal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/view")
public class AppealViewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer account_id = (Integer)req.getSession().getAttribute("account_id");
        if(account_id == null || req.getSession().getAttribute("auth") != Boolean.TRUE)
        {
            resp.sendRedirect("/index");
            return;
        }
        String id = req.getParameter("id");

        if(id == null )
        {
            resp.sendError(400);
            return;
        }

        try {
            AppealDao appealDao = new AppealDaoImpl();
            Appeal appeal = appealDao.findById(Integer.parseInt(id));
            if(appeal == null || !appeal.getAccountId().equals(account_id))
            {
                resp.sendError(400);
                return;
            }
            req.setAttribute("appeal", appeal);
            req.getRequestDispatcher("/view/view.jsp").forward(req, resp);
        }catch (SQLException e)
        {
            resp.sendError(503);
        }
        catch (NumberFormatException e)
        {
            resp.sendError(400);
        }
    }
}
