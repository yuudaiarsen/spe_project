package ru.ikbo1018.dao;

import ru.ikbo1018.models.Type;

import java.sql.SQLException;
import java.util.List;

public interface TypeDao extends CrudDao<Type> {
    List<Type> findAll() throws SQLException;
    String findDescById(int id) throws SQLException;
}
