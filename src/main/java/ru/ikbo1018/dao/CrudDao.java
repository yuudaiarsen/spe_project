package ru.ikbo1018.dao;

import java.sql.SQLException;

public interface CrudDao<T> {
    void create(T model) throws SQLException;
    void update(T model) throws SQLException;
    void delete(Integer id) throws SQLException;
    T find(Integer id);
}
