package ru.ikbo1018.dao;

import ru.ikbo1018.models.Account;

import java.sql.SQLException;
import java.util.List;

public interface AccountDao extends CrudDao<Account> {
    List<Account> findAll() throws SQLException;
    Account findById(int id) throws SQLException;
    Account findByEmail(String email) throws SQLException;
    void updateField(String field, String data, int id) throws SQLException;
}
