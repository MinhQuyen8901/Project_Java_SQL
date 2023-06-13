package Connect;

import Database.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

    public static Connect connect = null;
    public static String url = Config.PB_URL;
    public static String username = Config.USER_NAME;
    public static String password = Config.PASSWORD;
    public static Connection getConnection(){
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public static Connect getInstance(){
        return connect == null ? new Connect() : connect;
    }
}
