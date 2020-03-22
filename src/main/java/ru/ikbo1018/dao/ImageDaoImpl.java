package ru.ikbo1018.dao;

import ru.ikbo1018.models.Image;
import ru.ikbo1018.storage.DataBaseController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImageDaoImpl implements ImageDao {
    //language=SQL
    private static final String SQL_INSERT = "INSERT INTO images VALUES (DEFAULT, ?, ?);";

    private Connection connection;

    public ImageDaoImpl()
    {
        connection = DataBaseController.getInstance().getConnection();
    }

    public void create(Image model) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_INSERT);
        statement.setInt(1, model.getAppeal_id());
        statement.setBlob(2, model.getData());
        statement.execute();
    }

    public void update(Image model) {

    }

    public void delete(Integer id) {

    }

    public Image find(Integer id) {
        return null;
    }
}
