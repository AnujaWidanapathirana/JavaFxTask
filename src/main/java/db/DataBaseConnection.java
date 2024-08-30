package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DataBaseConnection {
    private static DataBaseConnection instance;
    private Connection connection;
    private DataBaseConnection() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/TaskManagement", "root", "1234");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }
    public static synchronized DataBaseConnection getInstance() {
        if (instance == null) {
            instance = new DataBaseConnection();
        }
        return instance;
    }
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/TaskManagement", "root", "1234");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to reconnect to the database", e);
        }
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
