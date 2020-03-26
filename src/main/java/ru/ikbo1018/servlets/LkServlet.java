package ru.ikbo1018.servlets;

import com.google.gson.Gson;
import ru.ikbo1018.dao.*;
import ru.ikbo1018.models.Account;
import ru.ikbo1018.models.Appeal;
import ru.ikbo1018.storage.StatusStringController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@WebServlet("/lk")
public class LkServlet extends HttpServlet {

    private static final int APPEAL_NUMBER = 10;

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
            resp.setContentType("text/json");
            resp.setCharacterEncoding("utf-8");
            Map<String, String> result = new HashMap<String, String>();

            String page = req.getParameter("page");
            if(page != null)
            {
                try
                {
                    int pageNum = Integer.parseInt(page);
                    AppealDao appealDao = new AppealDaoImpl();
                    List<Appeal> appealList = appealDao.findInRangeByAccountId(account_id, pageNum * APPEAL_NUMBER, pageNum * APPEAL_NUMBER + APPEAL_NUMBER);
                    Map<Integer, Map<String, String>> res = new HashMap<Integer, Map<String, String>>();
                    TypeDao typeDao = new TypeDaoImpl();
                    int i = 0;
                    for(Appeal app : appealList)
                    {
                        HashMap<String, String> tmp = new HashMap<String, String>();
                        String typeName = typeDao.findDescById(app.getType());
                        tmp.put("id", app.getId().toString());
                        tmp.put("send_date", app.getSendDate().toString());
                        tmp.put("status", StatusStringController.getInstance().getStatus(app.getStatus()));
                        tmp.put("check_date", app.getCheckDate().toString());
                        tmp.put("appeal_text", app.getAppealText());
                        tmp.put("answer_text", app.getAnswerText());
                        tmp.put("address", app.getAddress());
                        tmp.put("type", typeName);
                        res.put(i, tmp);
                        i++;
                    }
                    resp.getWriter().print(new Gson().toJson(res));
                    return;
                }catch (NumberFormatException e)
                {
                    resp.sendError(400);
                }
                catch (Exception e)
                {
                    resp.sendError(503);
                }
            }


            String field = req.getParameter("field");
            String data = req.getParameter("data");

            if(field == null || data == null)
            {
                resp.sendError(400);
                return;
            }

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
