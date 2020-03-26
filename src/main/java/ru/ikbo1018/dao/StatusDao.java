package ru.ikbo1018.dao;

import ru.ikbo1018.models.Status;

import java.sql.SQLException;

public interface StatusDao extends CrudDao<Status>{
    Status findById(int id) throws SQLException;
}
