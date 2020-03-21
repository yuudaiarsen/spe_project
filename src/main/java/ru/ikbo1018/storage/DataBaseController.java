package ru.ikbo1018.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class DataBaseController {
    private Connection connection;
    private static DataBaseController dataBaseController;

    private DataBaseController() {}

    private DataBaseController(String pathToProp) throws IOException, ClassNotFoundException, SQLException {
        Properties db_prop = new Properties();
        db_prop.load(new FileInputStream(pathToProp));
        String url = db_prop.getProperty("db_url");
        String user = db_prop.getProperty("db_user");
        String password = db_prop.getProperty("db_password");
        Class.forName(db_prop.getProperty("db_classDriver"));
        connection = DriverManager.getConnection(url, user, password);
    }

    public static DataBaseController getInstance() {
        return dataBaseController;
    }

    public static DataBaseController getInstance(String pathToProp) throws IOException, ClassNotFoundException, SQLException {
        if(dataBaseController == null)
        {
            dataBaseController = new DataBaseController(pathToProp);
        }
        return dataBaseController;
    }

    public Connection getConnection()
    {
        return connection;
    }
}
