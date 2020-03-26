package ru.ikbo1018.storage;

import ru.ikbo1018.dao.StatusDao;
import ru.ikbo1018.dao.StatusDaoImpl;
import ru.ikbo1018.models.Status;

import java.sql.SQLException;
import java.util.Map;

public final class StatusStringController {
    private static StatusStringController controller;

    private StatusStringController()
    {

    }

    public static StatusStringController getInstance()
    {
        if(controller == null)
        {
            controller = new StatusStringController();
        }
        return controller;
    }

    public String getStatus(int code)
    {
        try
        {
            StatusDao statusDao = new StatusDaoImpl();
            Status status = statusDao.findById(code);
            if(status != null)
            {
                System.out.println(status.getDesc());
                return status.getDesc();
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return "";
    }
}
