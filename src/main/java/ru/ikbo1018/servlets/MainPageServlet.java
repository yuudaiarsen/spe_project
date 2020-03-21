package ru.ikbo1018.servlets;

import ru.ikbo1018.dao.AccountDao;
import ru.ikbo1018.dao.AccountDaoImpl;
import ru.ikbo1018.models.Account;
import ru.ikbo1018.storage.DataBaseController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class MainPageServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        try {
            DataBaseController dataBaseController = DataBaseController.getInstance(
                    getServletContext().getRealPath("/WEB-INF/classes/db.properties")
            );
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       req.getRequestDispatcher("/startPage.jsp").forward(req, resp);
    }

}
