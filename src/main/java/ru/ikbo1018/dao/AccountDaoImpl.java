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
                    resultSet.getDate("reg_date")
            ));
        }
        return result;
    }

    public Account findById(int id) {
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
                resultSet.getDate("reg_date"));
    }

    public void create(Account model) {

    }

    public void update(Account model) {

    }

    public void delete(Integer id) {

    }

    public Account find(Integer id) {
        return null;
    }
}
