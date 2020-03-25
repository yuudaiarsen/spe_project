package ru.ikbo1018.dao;

import ru.ikbo1018.models.Account;
import ru.ikbo1018.storage.DataBaseController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl implements AccountDao {
    private Connection connection;

    public AccountDaoImpl()
    {
        connection = DataBaseController.getInstance().getConnection();
    }

    //language=SQL
    private static final String SQL_FIND_ALL = "SELECT * FROM accounts;";

    //language=SQL
    private static final String SQL_FIND_BY_EMAIL = "SELECT * FROM accounts WHERE email = ?;";

    //language=SQL
    private static final String SQL_INSERT = "INSERT INTO accounts VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?);";

    //language=SQL
    private static final String SQL_FIND_BY_ID = "SELECT * FROM accounts WHERE id = ?;";

    //language=SQL
    private static final String SQL_UPDATE_FIELD_BY_ID = "UPDATE accounts SET `?` = ? WHERE id = ?;";


    public List<Account> findAll() throws SQLException {

        PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL);
        ResultSet resultSet = statement.executeQuery();
        List<Account> result = new ArrayList<Account>();
        while (resultSet.next())
        {
            result.add(new Account(
                    resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("mid_name"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getInt("sec_level"),
                    resultSet.getString("phone"),
                    resultSet.getDate("reg_date")
            ));
        }
        return result;
    }

    public Account findById(int id) throws SQLException{
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next())
        {
            return new Account(resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("mid_name"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getInt("sec_level"),
                    resultSet.getString("phone"),
                    resultSet.getDate("reg_date"));
        }
        return null;
    }

    public Account findByEmail(String email) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_EMAIL);
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();
        if(!resultSet.next()) {
            return null;
        }
        return new Account(resultSet.getInt("id"), resultSet.getString("first_name"),
                resultSet.getString("last_name"), resultSet.getString("mid_name"),
                resultSet.getString("email"), resultSet.getString("password"), resultSet.getInt("sec_level"),
                resultSet.getString("phone"),  resultSet.getDate("reg_date"));
    }

    @Override
    public void updateField(String field, String data, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE accounts SET " + field + " = ? WHERE id = ?;");
        statement.setString(1, data);
        statement.setInt(2, id);
        statement.execute();
    }

    public void create(Account model) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT);
        statement.setString(1, model.getFirstName());
        statement.setString(2, model.getLastName());
        statement.setString(3, model.getMidName());
        statement.setString(4, model.getEmail());
        statement.setString(5, model.getPassword());
        statement.setInt(6, model.getSecLevel());
        statement.setString(7, model.getPhone());
        statement.setDate(8, model.getRegDate());

        statement.execute();
    }

    public void update(Account model) throws SQLException{

    }

    public void delete(Integer id) {

    }

    public Account find(Integer id) {
        return null;
    }
}
