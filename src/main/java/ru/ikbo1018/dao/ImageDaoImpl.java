package ru.ikbo1018.dao;

import ru.ikbo1018.models.Image;
import ru.ikbo1018.storage.DataBaseController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImageDaoImpl implements ImageDao {
    //language=SQL
    private static final String SQL_INSERT = "INSERT INTO images VALUES (DEFAULT, ?, ?);";

    //language=SQL
    private static final String SQL_FIND_ALL_BY_APPEAL_ID = "SELECT * FROM images WHERE appeal_id = ?;";

    //language=SQL
    private static final String SQL_FIND_BY_ID = "SELECT * FROM images WHERE id = ?;";

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

    @Override
    public List<Image> findAllByAppealId(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_BY_APPEAL_ID);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        List<Image> result = new ArrayList<Image>();
        while(resultSet.next())
        {
            result.add(new Image(resultSet.getInt("id"), resultSet.getInt("appeal_id"),
                    resultSet.getBinaryStream("data")));
        }
        return result;
    }

    @Override
    public Image findById(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next())
        {
            return new Image(resultSet.getInt("id"), resultSet.getInt("appeal_id"),
                    resultSet.getBinaryStream("data"));
        }
        return null;
    }
}
