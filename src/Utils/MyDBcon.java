package Utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Firas
 */
public class MyDBcon {

    private Connection con;
    private static MyDBcon instance;
    final String url = "jdbc:mysql://localhost:3306/wordfriendship";
    final String user = "root";
    final String pass = "";

    private MyDBcon() throws SQLException {
        try {
            con = DriverManager.getConnection(url, user, pass);
        } catch (SQLDataException ex) {
            Logger.getLogger(MyDBcon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getCon() {
        return con;
    }

    public static MyDBcon getInstance() throws SQLException {
        if (instance == null) {
            instance = new MyDBcon();
        }

        return instance;
    }

}
