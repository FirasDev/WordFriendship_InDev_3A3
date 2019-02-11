/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Firas
 */
public class MyDBcon {

    private static MyDBcon instance;
    private Connection con;

    final String url = "jdbc:mysql://127.0.0.1/wordfriendship";
    final String login = "root";
    final String password = "";

    private MyDBcon() {
        try {
            con = DriverManager.getConnection(url, login, password);
            System.out.println("connexion établie!!!!");
        } catch (SQLException ex) {
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