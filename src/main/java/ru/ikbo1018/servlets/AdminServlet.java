package ru.ikbo1018.servlets;

import com.google.gson.Gson;
import ru.ikbo1018.dao.AccountDao;
import ru.ikbo1018.dao.AccountDaoImpl;
import ru.ikbo1018.dao.TypeDao;
import ru.ikbo1018.dao.TypeDaoImpl;
import ru.ikbo1018.models.Account;
import ru.ikbo1018.models.Type;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
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
                if (account.getSecLevel() != 2)
                {
                    resp.sendRedirect("/index");
                    return;
                }
                req.getRequestDispatcher("/admin/admin.jsp").forward(req, resp);
            }catch (SQLException e)
            {
                resp.sendError(503);
            }
        }
        else {
            resp.sendRedirect("/index");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer account_id = (Integer) req.getSession().getAttribute("account_id");

        if(account_id != null && req.getSession().getAttribute("auth") == Boolean.TRUE) {
            try {
                AccountDao accountDao = new AccountDaoImpl();
                TypeDao typeDao = new TypeDaoImpl();
                Account account = accountDao.findById(account_id);
                if(account == null)
                {
                    resp.sendError(400);
                    return;
                }
                if (account.getSecLevel() != 2)
                {
                    resp.sendRedirect("/index");
                    return;
                }

                String action = req.getParameter("action");
                System.out.println(action);
                if(action == null)
                {
                    resp.sendError(400);
                    return;
                }

                Map<String, String> result = new HashMap<>();
                resp.setContentType("text/json");
                resp.setCharacterEncoding("utf-8");

                if(action.equals("find"))
                {
                    try
                    {
                        int id = Integer.parseInt(req.getParameter("id"));
                        Account findAcc = accountDao.findById(id);
                        if(findAcc == null)
                        {
                            resp.sendError(400);
                            return;
                        }
                        //System.out.println(Integer.toString(findAcc.getId()));
                        //System.out.println(findAcc.getFirstName());
                        result.put("id", Integer.toString(findAcc.getId()));
                        result.put("first_name", findAcc.getFirstName());
                        result.put("last_name", findAcc.getLastName());
                        result.put("mid_name", findAcc.getMidName());
                        result.put("email", findAcc.getEmail());
                        result.put("phone", findAcc.getPhone());
                        result.put("sec_level", Integer.toString(findAcc.getSecLevel()));
                        result.put("reg_date", findAcc.getRegDate().toString());
                        resp.getWriter().print(new Gson().toJson(result));
                    }catch (NumberFormatException e)
                    {
                        resp.sendError(400);
                    }
                }
                else if(action.equals("get_type_list"))
                {
                    List<Type> types;
                    try {
                        types = typeDao.findAll();
                        Map<Integer, Map<String, String>> t_res = new HashMap<>();
                        for(Type type : types)
                        {
                            result.clear();
                            result.put("name", type.getName());
                            result.put("desc", type.getDesc());
                            t_res.put(type.getId(), new HashMap<>(result));
                        }
                        resp.getWriter().print(new Gson().toJson(t_res));
                    }catch (SQLException e)
                    {
                        resp.sendError(503);
                    }
                }
                else if(action.equals("create_type"))
                {
                    String name = req.getParameter("name");
                    String desc = req.getParameter("desc");

                    if(name == null || desc == null)
                    {
                        resp.sendError(400);
                        return;
                    }

                    typeDao.create(new Type(name, desc));
                    resp.setStatus(200);
                }
                else if(action.equals("delete_type"))
                {
                    try
                    {
                        int id = Integer.parseInt(req.getParameter("id"));
                        typeDao.delete(id);
                        resp.setStatus(200);
                    }catch (NumberFormatException e)
                    {
                        resp.sendError(400);
                    }catch (SQLException e)
                    {
                        resp.sendError(503);
                    }
                }

            }catch (SQLException e)
            {
                resp.sendError(503);
            }
        }
        else{
            resp.sendError(401);
        }
    }
}
