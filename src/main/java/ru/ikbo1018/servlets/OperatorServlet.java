package ru.ikbo1018.servlets;


import ru.ikbo1018.dao.*;
import ru.ikbo1018.models.Account;
import ru.ikbo1018.models.Appeal;
import ru.ikbo1018.storage.AppealController;
import ru.ikbo1018.storage.StatusStringController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/operator")
public class OperatorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer account_id = (Integer) req.getSession().getAttribute("account_id");
        if(account_id != null && req.getSession().getAttribute("auth") == Boolean.TRUE)
        {
            try {
                AccountDao accountDao = new AccountDaoImpl();
                Account account = accountDao.findById(account_id);
                if(account == null)
                {
                    resp.sendError(400);
                    return;
                }
                if(account.getSecLevel() < 1)
                {
                    resp.sendRedirect("/index");
                    return;
                }
                Appeal appeal = AppealController.getInstance().get();
                if(appeal == null)
                {
                    resp.setContentType("text/html");
                    resp.setCharacterEncoding("utf-8");
                    resp.getWriter().println("В данный момент нет обращений, ожидающих рассмотрения");
                    return;
                }
                req.setAttribute("appeal", appeal);
                TypeDao typeDao = new TypeDaoImpl();
                req.setAttribute("type", typeDao.findDescById(appeal.getType()));
                req.setAttribute("status", StatusStringController.getInstance().getStatus(appeal.getStatus()));
                req.getRequestDispatcher("/operator/operator.jsp").forward(req, resp);
            }catch (SQLException e)
            {
                resp.sendError(503);
            }
        }
        else
        {
            resp.sendRedirect("/index");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer account_id = (Integer) req.getSession().getAttribute("account_id");
        if(account_id != null && req.getSession().getAttribute("auth") == Boolean.TRUE)
        {
            try {
                AccountDao accountDao = new AccountDaoImpl();
                Account account = accountDao.findById(account_id);
                if (account == null) {
                    resp.sendError(400);
                    return;
                }
                if (account.getSecLevel() != 1) {
                    resp.sendRedirect("/index");
                    return;
                }

                Integer appeal_id = Integer.parseInt(req.getParameter("id"));
                String answer = req.getParameter("answer");
                String action = req.getParameter("action");

                if(answer == null | action == null)
                {
                   resp.sendError(400);
                   return;
                }

                AppealDao appealDao = new AppealDaoImpl();

                Appeal app = appealDao.findById(appeal_id);
                if(app.getStatus() != 1)
                {
                    resp.setStatus(409);
                    return;
                }


                if(action.equals("checked"))
                {
                    appealDao.updateColumnIntById(appeal_id, "status", 2);
                }
                else if(action.equals("denied"))
                {
                    appealDao.updateColumnIntById(appeal_id, "status", 4);
                }
                else
                {
                    resp.sendError(400);
                    return;
                }
                appealDao.updateColumnStringById(appeal_id, "answer_text", answer);
                appealDao.updateColumnStringById(appeal_id, "check_date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                AppealController.getInstance().checked(appeal_id);
                resp.setStatus(200);
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
}
