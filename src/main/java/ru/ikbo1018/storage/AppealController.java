package ru.ikbo1018.storage;

import ru.ikbo1018.dao.AppealDao;
import ru.ikbo1018.dao.AppealDaoImpl;
import ru.ikbo1018.models.Appeal;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public final class AppealController {

    private static AppealController appealController;
    private static PriorityQueue<Appeal> queue;


    private AppealController()
    {
        Comparator<Appeal> comparator = (appeal, t1) -> {
            Date d1, d2;
            try {
                d1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(appeal.getSendDate());
                d2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(t1.getSendDate());
                return d1.compareTo(d2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return 0;
        };

        queue = new PriorityQueue<Appeal>( comparator);
        loadAppeals();
    }

    public static AppealController getInstance()
    {
        if(appealController == null)
        {
            appealController = new AppealController();
        }
        return appealController;
    }

    public void addAppeal(Appeal appeal)
    {
        queue.add(appeal);
    }

    public Appeal poll()
    {
        return queue.poll();
    }

    public int size()
    {
        return queue.size();
    }

    public boolean isEmpty()
    {
        return queue.isEmpty();
    }

    private void loadAppeals()
    {
        try {
            AppealDao appealDao = new AppealDaoImpl();
            List<Appeal> appeals = appealDao.findAllByStatus(1);
            queue.addAll(appeals);
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public Appeal[] getList()
    {
        return queue.toArray(new Appeal[0]);
    }

    public void remove(int id)
    {
        for(Appeal appeal : getList())
        {
            if(appeal.getId().equals(id))
            {
                queue.remove(appeal);
            }
        }
    }
}
