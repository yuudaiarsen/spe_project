package ru.ikbo1018.servlets;

import com.google.gson.Gson;
import ru.ikbo1018.dao.*;
import ru.ikbo1018.models.Appeal;
import ru.ikbo1018.models.Image;
import ru.ikbo1018.storage.StatusStringController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            TypeDao typeDao = new TypeDaoImpl();
            req.setAttribute("type", typeDao.findDescById(appeal.getType()));
            req.setAttribute("status", StatusStringController.getInstance().getStatus(appeal.getStatus()));
            ImageDao imageDao = new ImageDaoImpl();
            List<Image> imageList = imageDao.findAllByAppealId(appeal.getId());
            List<Integer> imagesId = new ArrayList<Integer>();
            for(Image image : imageList)
            {
                imagesId.add(image.getId());
            }
            req.setAttribute("images", imagesId);
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer account_id = (Integer)req.getSession().getAttribute("account_id");
        if(account_id == null || req.getSession().getAttribute("auth") != Boolean.TRUE)
        {
            resp.sendError(401);
            return;
        }
        String id = req.getParameter("id");
        String action = req.getParameter("action");
        if(id == null || action == null)
        {
            resp.sendError(400);
            return;
        }

        try {
            AppealDao appealDao = new AppealDaoImpl();
            Appeal appeal = appealDao.findById(Integer.parseInt(id));
            Map<String, String> result = new HashMap<String, String>();
            if(appeal == null)
            {
                resp.setStatus(400);
                return;
            }
            if(action.equals("deny")) {
                if(appeal.getStatus() != 1)
                {
                    resp.setStatus(400);
                    return;
                }
                resp.setContentType("text/json");
                resp.setCharacterEncoding("utf-8");
                appealDao.updateColumnIntById(appeal.getId(), "status", 3);
                result.put("message", "Обращение успешно отозвано");
                resp.getWriter().print(new Gson().toJson(result));
            }
        }catch (NumberFormatException e)
        {
            resp.setStatus(400);
        }
        catch (SQLException e)
        {
            resp.setStatus(503);
            e.printStackTrace();
        }
    }
}
