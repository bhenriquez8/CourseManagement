package database;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionFactory {
    private static DatabaseConnectionFactory ourInstance = new DatabaseConnectionFactory();
    private ComboPooledDataSource comboPooledDataSource;

    public static DatabaseConnectionFactory getInstance() {
        return ourInstance;
    }

    private DatabaseConnectionFactory() {
    }

    public synchronized void init() throws IOException {
        if (comboPooledDataSource != null)
            return;

        // load db.properties first
        InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("db.properties");
        Properties dbProperties = new Properties();
        dbProperties.load(inStream);
        inStream.close();

        // create ComboPooledDataSource specific pool properties
        try {
            comboPooledDataSource = new ComboPooledDataSource();
            comboPooledDataSource
                    .setDriverClass(dbProperties.getProperty("db_driver_class_name"));
            comboPooledDataSource
                    .setJdbcUrl(dbProperties.getProperty("db_jdbc_url"));
            comboPooledDataSource.setUser(dbProperties.getProperty("db_user_name"));
            comboPooledDataSource.setPassword(dbProperties.getProperty("db_password"));
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        Connection con = null;
        try {
            con = comboPooledDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
