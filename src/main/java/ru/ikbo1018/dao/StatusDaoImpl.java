package ru.ikbo1018.dao;

import ru.ikbo1018.models.Status;
import ru.ikbo1018.storage.DataBaseController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusDaoImpl implements StatusDao {
    private Connection connection;

    public StatusDaoImpl()
    {
        connection = DataBaseController.getInstance().getConnection();
    }

    //language=SQL
    private static final String SQL_FIND_BY_ID = "SELECT * FROM status WHERE id = ?;";

    @Override
    public Status findById(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next())
        {
            return new Status(resultSet.getInt("id"), resultSet.getString("desc"));
        }
        return null;
    }

    @Override
    public void create(Status model) throws SQLException {

    }

    @Override
    public void update(Status model) throws SQLException {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Status find(Integer id) {
        return null;
    }
}
