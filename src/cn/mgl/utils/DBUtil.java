package cn.mgl.utils;

import java.sql.*;
import java.util.ResourceBundle;

public class DBUtil {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("resource/jdbc");

    private static String url = resourceBundle.getString("url");
    private static String username = resourceBundle.getString("username");
    private static String password = resourceBundle.getString("password");
    private static String driverName = resourceBundle.getString("driverName");

    private static ThreadLocal<Connection> local = new ThreadLocal();

    private DBUtil() {
    }

    static {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection conn = local.get();
        if (conn == null) {
            conn = DriverManager.getConnection(url, username, password);
            local.set(conn);
        }
        return conn;
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
                local.remove();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
