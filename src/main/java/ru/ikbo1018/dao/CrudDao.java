package ru.ikbo1018.dao;

import java.sql.SQLException;

public interface CrudDao<T> {
    void create(T model) throws SQLException;
    void update(T model);
    void delete(Integer id);
    T find(Integer id);
}
