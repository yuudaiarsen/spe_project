package ru.ikbo1018.dao;

import ru.ikbo1018.models.Appeal;

import java.sql.SQLException;

public interface AppealDao extends CrudDao<Appeal> {
    int getMaxId() throws SQLException;
}
