package ru.ikbo1018.dao;

import ru.ikbo1018.models.Appeal;
import ru.ikbo1018.storage.DataBaseController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppealDaoImpl implements AppealDao {
    //language=SQL
    private static final String SQL_INSERT = "INSERT INTO appeal VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    //language=SQL
    private static final String SQL_GET_MAX_ID = "SELECT MAX(id) FROM appeal;";

    //language=SQL
    private static final String SQL_FIND_BY_ID = "SELECT * FROM appeal WHERE id = ?;";

    //language=SQL
    private static final String SQL_FIND_BY_ACCOUNT_ID = "SELECT * FROM appeal WHERE account_id = ?;";

    //language=SQL
    private static final String SQL_FIND_BY_ACCOUNT_ID_IN_RANGE = "SELECT * FROM appeal WHERE account_id = ? LIMIT ?,?;";

    //language=SQL
    private static final String SQL_FIND_BY_STATUS = "SELECT * FROM appeal WHERE status = ?;";

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
        statement.setString(2, model.getSendDate());
        statement.setInt(3, model.getStatus());
        statement.setString(4, model.getSendDate());
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

    @Override
    public Appeal findById(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next())
        {
            return new Appeal(resultSet.getInt("id"), resultSet.getInt("account_id"), resultSet.getString("send_date"),
                    resultSet.getInt("status"), resultSet.getString("check_date"), resultSet.getInt("operator_id"),
                    resultSet.getString("appeal_text"), resultSet.getString("answer_text"), resultSet.getString("address"),
                    resultSet.getInt("type_id"));
        }
        return null;
    }

    @Override
    public List<Appeal> findAllByAccountId(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ACCOUNT_ID);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        List<Appeal> result = new ArrayList<Appeal>();
        while (resultSet.next())
        {
            result.add(new Appeal(resultSet.getInt("id"), resultSet.getInt("account_id"), resultSet.getString("send_date"),
                    resultSet.getInt("status"), resultSet.getString("check_date"), resultSet.getInt("operator_id"),
                    resultSet.getString("appeal_text"), resultSet.getString("answer_text"), resultSet.getString("address"),
                    resultSet.getInt("type_id")));
        }
        return result;
    }

    @Override
    public List<Appeal> findInRangeByAccountId(int id, int start, int end) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ACCOUNT_ID_IN_RANGE);
        statement.setInt(1, id);
        statement.setInt(2, start);
        statement.setInt(3, end);
        ResultSet resultSet = statement.executeQuery();
        List<Appeal> result = new ArrayList<Appeal>();
        while (resultSet.next())
        {
            result.add(new Appeal(resultSet.getInt("id"), resultSet.getInt("account_id"), resultSet.getString("send_date"),
                    resultSet.getInt("status"), resultSet.getString("check_date"), resultSet.getInt("operator_id"),
                    resultSet.getString("appeal_text"), resultSet.getString("answer_text"), resultSet.getString("address"),
                    resultSet.getInt("type_id")));
        }
        return result;
    }

    @Override
    public void updateColumnIntById(int id, String column, int newVal) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE appeal SET `" + column + "` = ? WHERE id = ?;");
        statement.setInt(1, newVal);
        statement.setInt(2, id);
        statement.execute();
    }

    @Override
    public List<Appeal> findAllByStatus(int status) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_STATUS);
        statement.setInt(1, status);
        ResultSet resultSet = statement.executeQuery();
        List<Appeal> result = new ArrayList<Appeal>();
        while (resultSet.next())
        {
            result.add(new Appeal(resultSet.getInt("id"), resultSet.getInt("account_id"), resultSet.getString("send_date"),
                    resultSet.getInt("status"), resultSet.getString("check_date"), resultSet.getInt("operator_id"),
                    resultSet.getString("appeal_text"), resultSet.getString("answer_text"), resultSet.getString("address"),
                    resultSet.getInt("type_id")));
        }
        return result;
    }
}
