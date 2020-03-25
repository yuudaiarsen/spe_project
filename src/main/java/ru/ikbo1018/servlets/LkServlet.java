package ru.ikbo1018.servlets;

import com.google.gson.Gson;
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
import java.util.regex.Pattern;

@WebServlet("/lk")
public class LkServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("auth") == Boolean.TRUE)
        {
            try {
                Integer accountId = (Integer) req.getSession().getAttribute("account_id");
                AccountDao accountDao = new AccountDaoImpl();
                Account account = accountDao.findById(accountId);
                if(account == null)
                {
                    resp.sendError(503);
                    return;
                }
                req.setAttribute("account", account);
                //TODO: ПЕРЕДАВАТЬ СПИСОК ОБРАЩЕНИЙ
                req.getRequestDispatcher("/lk/lk.jsp").forward(req, resp);
            }catch (NumberFormatException e)
            {
                resp.sendError(503);
            }
            catch (SQLException e)
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
        Integer account_id = (Integer)req.getSession().getAttribute("account_id");
        if(account_id != null && req.getSession().getAttribute("auth") == Boolean.TRUE)
        {
            String field = req.getParameter("field");
            String data = req.getParameter("data");

            if(field == null || data == null)
            {
                resp.sendError(400);
                return;
            }

            resp.setContentType("text/json");
            resp.setCharacterEncoding("utf-8");
            Map<String, String> result = new HashMap<String, String>();

            Pattern checkSpace = Pattern.compile("^[а-яА-Я]+$");
            Pattern checkPhone = Pattern.compile("^[0-9+]+$");

            AccountDao accountDao = new AccountDaoImpl();

            if(field.equals("first_name") || field.equals("mid_name"))
            {
                if(data.length() == 0 || data.length() > 20)
                {
                    result.put("error", "Длина поля должна быть от 1 до 20");
                    resp.getWriter().print(new Gson().toJson(result));
                    return;
                }
                else if(!checkSpace.matcher(data).matches())
                {
                    result.put("error", "Поле может содержать только символы русского алфавита");
                    resp.getWriter().print(new Gson().toJson(result));
                    return;
                }
            }
            else if(field.equals("last_name"))
            {
                if(data.length() == 0 || data.length() > 40)
                {
                    result.put("error", "Длина поля должна быть от 1 до 40");
                    resp.getWriter().print(new Gson().toJson(result));
                    return;
                }
                else if(!checkSpace.matcher(data).matches())
                {
                    result.put("error", "Поле может содержать только символы русского алфавита");
                    resp.getWriter().print(new Gson().toJson(result));
                    return;
                }
            }
            else if(field.equals("phone"))
            {
                if(data.length() < 10 || data.length() > 20)
                {
                    result.put("error", "Длина поля должна быть от 10 до 20");
                    resp.getWriter().print(new Gson().toJson(result));
                    return;
                }
                else if(!checkPhone.matcher(data).matches())
                {
                    result.put("error", "Поле может содержать только цифры и знак +");
                    resp.getWriter().print(new Gson().toJson(result));
                    return;
                }
            }
            try {
                accountDao.updateField(field, data, account_id);

            }catch (SQLException e)
            {
                resp.sendError(503);
                //e.printStackTrace();
            }
            result.put("message", "Поле успешно изменено");
            resp.getWriter().print(new Gson().toJson(result));
        }
        else
        {
            resp.sendError(401);
        }
    }
}
