package ru.ikbo1018.dao;

import ru.ikbo1018.models.Image;

import java.sql.SQLException;
import java.util.List;

public interface ImageDao extends CrudDao<Image>{
    List<Image> findAllByAppealId(int id) throws SQLException;
    Image findById(int id) throws SQLException;
}
