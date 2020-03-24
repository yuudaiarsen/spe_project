package ru.ikbo1018.dao;

import ru.ikbo1018.models.Type;
import ru.ikbo1018.storage.DataBaseController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeDaoImpl implements TypeDao {
    //language=SQL
    private static final String SQL_FIND_ALL = "SELECT * FROM types;";

    //language=SQL
    private static final String SQL_FIND_DESC_BY_ID = "SELECT `desc` FROM types WHERE `id` = ?;";

    private Connection connection;

    public TypeDaoImpl()
    {
        connection = DataBaseController.getInstance().getConnection();
    }

    @Override
    public void create(Type model) throws SQLException {

    }

    @Override
    public void update(Type model) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Type find(Integer id) {
        return null;
    }

    @Override
    public List<Type> findAll() throws SQLException{
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL);
        ResultSet resultSet = statement.executeQuery();
        List<Type> result = new ArrayList<Type>();
        while (resultSet.next())
        {
            result.add(new Type(resultSet.getInt("id"), resultSet.getString("name"),
                    resultSet.getString("desc")));
        }
        return result;
    }

    @Override
    public String findDescById(int id) throws SQLException{
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_DESC_BY_ID);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next())
        {
            return resultSet.getString("desc");
        }
        return "";
    }
}
