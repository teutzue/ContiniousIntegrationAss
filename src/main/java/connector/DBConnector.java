package connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/cupcakeshop";
    private static String user = "root";
    private static String password = "root";
    private static Connection conn = null;


    public Connection getConnection() {
        if (conn == null) {
            try {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return conn;
    }

    public void injectConnection(Connection c){
        conn = c;
    }
}
