package ru.ikbo1018.servlets;

import com.google.gson.Gson;
import ru.ikbo1018.dao.AppealDaoImpl;
import ru.ikbo1018.dao.ImageDaoImpl;
import ru.ikbo1018.dao.TypeDao;
import ru.ikbo1018.dao.TypeDaoImpl;
import ru.ikbo1018.models.Appeal;
import ru.ikbo1018.models.Image;
import ru.ikbo1018.models.Type;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@WebServlet("/appeal")
@MultipartConfig
public class AppealServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TypeDao typeDao = new TypeDaoImpl();
        List<Type> types;
        //StringBuilder list = new StringBuilder();
        try {
            types = typeDao.findAll();
            req.setAttribute("type_list", types);
        }catch (SQLException e)
        {
            resp.sendError(503);
            //e.printStackTrace();
            return;
        }

        req.getRequestDispatcher("/appeal/appeal.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer account_id = (Integer)req.getSession().getAttribute("account_id");

        if(account_id != null && req.getSession().getAttribute("auth") == Boolean.TRUE)
        {
            String selected = req.getParameter("value");

            resp.setContentType("text/json");
            resp.setCharacterEncoding("utf-8");
            Map<String, String> result = new HashMap<String, String>();

            if(selected != null)
            {
                try
                {
                    TypeDao typeDao = new TypeDaoImpl();
                    String desc = typeDao.findDescById(Integer.parseInt(selected));
                    result.put("desc", desc);
                    resp.getWriter().print(new Gson().toJson(result));
                    return;
                }catch (NumberFormatException e)
                {
                    resp.sendError(400);
                    return;
                }
                catch(SQLException e)
                {
                    resp.sendError(503);
                    //e.printStackTrace();
                    return;
                }
            }

            String type = req.getParameter("type"); // тип нарушения
            String address = req.getParameter("address");
            String appeal_text = req.getParameter("appeal_text");

            if(type == null || address == null || appeal_text == null)
            {
                resp.sendError(400);
                return;
            }

            ArrayList<Part> images = new ArrayList<Part>();

            for(Part part : req.getParts())
            {
                if(part.getName().equals("files") && part.getSize() > 0) {
                    if (part.getSize() > 10 * 1024 * 1024) {
                        resp.setStatus(200);
                        result.put("code", "1");
                        result.put("error", "Размер изображения не может быть больше 10 Мб");
                        resp.getWriter().print(new Gson().toJson(result));
                        return;
                    };
                    Pattern urlPattern = Pattern.compile(".*\\.png$");
                    Pattern jpgPattern = Pattern.compile(".*\\.jpg$");
                    Pattern jpegPattern = Pattern.compile(".*\\.jpeg$");
                    if ( !urlPattern.matcher(part.getSubmittedFileName()).matches()
                            && !jpgPattern.matcher(part.getSubmittedFileName()).matches()
                            && !jpegPattern.matcher(part.getSubmittedFileName()).matches()){
                        resp.setStatus(200);
                        result.put("code", "2");
                        result.put("error", "Неверный тип загружаемого файла");
                        resp.getWriter().print(new Gson().toJson(result));
                        return;
                    }
                    images.add(part);
                    if(images.size() > 5)
                    {
                        resp.setStatus(200);
                        result.put("code", "3");
                        result.put("error", "Нельзя загрузить более чем 5 изображений");
                        resp.getWriter().print(new Gson().toJson(result));
                        return;
                    }
                }
            }

            try {
                ImageDaoImpl imageDao = new ImageDaoImpl();
                ArrayList<Image> res_images = new ArrayList<Image>();
                for (Part part : images) {
                    res_images.add(new Image(part.getInputStream()));
                }

                AppealDaoImpl appealDao = new AppealDaoImpl();
                Appeal appeal = new Appeal();
                appeal.setAccountId(account_id);
                appeal.setSendDate(new Date(System.currentTimeMillis()));
                appeal.setStatus(Appeal.APPEAL_STATUS_WAIT);
                appeal.setAppealText(appeal_text);
                appeal.setAddress(address);
                appeal.setType(Integer.parseInt(type));
                appealDao.create(appeal);
                int newId = appealDao.getMaxId();

                for(Image img : res_images)
                {
                    img.setAppeal_id(newId);
                    imageDao.create(img);
                }

                result.put("redirect", "/index");
                resp.getWriter().print(new Gson().toJson(result));
            }catch (SQLException e)
            {
                resp.setStatus(503);
                //e.printStackTrace();
            }
        }
        else
        {
            resp.setStatus(401);
        }
    }
}
