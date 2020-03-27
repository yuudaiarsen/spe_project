package ru.ikbo1018.servlets;

import ru.ikbo1018.dao.ImageDao;
import ru.ikbo1018.dao.ImageDaoImpl;
import ru.ikbo1018.models.Image;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

@WebServlet("/image")
public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer account_id = (Integer)req.getSession().getAttribute("account_id");

        if(account_id == null || req.getSession().getAttribute("auth") != Boolean.TRUE)
        {
            resp.sendError(401);
            return;
        }

        try
        {
            Integer id = Integer.parseInt(req.getParameter("id"));
            ImageDao imageDao = new ImageDaoImpl();
            Image image = imageDao.findById(id);
            if(image != null)
            {
                OutputStream out = resp.getOutputStream();
                InputStream in = image.getData();
                byte[] buffer = new byte[4096];
                int count = 0;
                while ((count = in.read(buffer)) >= 0)
                {
                    out.write(buffer, 0, count);
                }
            }
            else
            {
                resp.sendError(503);
            }
        }catch (NumberFormatException e)
        {
            resp.sendError(400);
        }
        catch (SQLException e)
        {
            resp.sendError(503);
        }
    }
}
