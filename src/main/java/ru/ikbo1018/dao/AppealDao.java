package ru.ikbo1018.dao;

import ru.ikbo1018.models.Appeal;

import java.sql.SQLException;
import java.util.List;

public interface AppealDao extends CrudDao<Appeal> {
    int getMaxId() throws SQLException;
    Appeal findById(int id) throws SQLException;
    List<Appeal> findAllByAccountId(int id) throws SQLException;
    List<Appeal> findInRangeByAccountId(int id, int start, int end) throws SQLException;
    void updateColumnIntById(int id, String column, int newVal) throws SQLException;
    List<Appeal> findAllByStatus(int status) throws SQLException;
}
