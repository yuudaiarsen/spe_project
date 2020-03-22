package ru.ikbo1018.dao;

import ru.ikbo1018.models.Appeal;
import ru.ikbo1018.storage.DataBaseController;

import java.sql.*;

public class AppealDaoImpl implements AppealDao {
    //language=SQL
    private static final String SQL_INSERT = "INSERT INTO appeal VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    //language=SQL
    private static final String SQL_GET_MAX_ID = "SELECT MAX(id) FROM appeal;";

    private Connection connection;

    public AppealDaoImpl()
    {
        connection = DataBaseController.getInstance().getConnection();
    }

    @Override
    public void create(Appeal model) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT);

        Integer operatorId = model.getOperatorId();

        statement.setInt(1, model.getAccountId());
        statement.setDate(2, model.getSendDate());
        statement.setInt(3, model.getStatus());
        statement.setDate(4, model.getSendDate());
        if(operatorId > 0)
            statement.setInt(5, operatorId);
        else
            statement.setNull(5, Types.INTEGER);
        statement.setString(6, model.getAppealText());
        statement.setString(7, model.getAnswerText());
        statement.setString(8, model.getAddress());
        statement.setInt(9, model.getType());
        statement.execute();
    }

    @Override
    public void update(Appeal model) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Appeal find(Integer id) {
        return null;
    }

    @Override
    public int getMaxId() throws SQLException{
        ResultSet resultSet = connection.prepareStatement(SQL_GET_MAX_ID).executeQuery();

        if(resultSet.next())
        {
            return resultSet.getInt("MAX(id)");
        }
        return 0;
    }
}
