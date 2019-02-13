/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Grade;
import Entities.Profil;
import Entities.User;
import Utils.MyDBcon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Firas
 */
public class UserCrud {

    static Connection cnx;

    public UserCrud() throws SQLException {
        cnx = MyDBcon.getInstance().getCon();

    }

    public static void ajouterUser(User u, Profil p, Grade g) {

        try {

            String req = "INSERT INTO `user`(`username`, `email`,`password`,`firstname`,`lastname`,`nationalite`,`langues`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pstm = cnx.prepareStatement(req);
            pstm.setString(1, u.getUsername());
            pstm.setString(2, u.getEmail());
            pstm.setString(3, u.getPassword());
            pstm.setString(4, u.getFirstname());
            pstm.setString(5, u.getLastname());
            pstm.setString(6, u.getNationalite());
            pstm.setString(7, u.getLangues());
            int id_user = u.getId();

            pstm.executeUpdate();
            //+++++++++

            Statement stm = cnx.createStatement();
            String req3 = "SELECT MAX(id) from user";

            ResultSet resultat = stm.executeQuery(req3);
            while (resultat.next()) {
                int id_p = resultat.getInt(1);
                //+++++++++++++

                String req2 = "INSERT INTO `profil`(`id_up`, `date_naissance`,`photo_p`,`descriptions`) VALUES (?,?,?,?)";
                PreparedStatement pstm2 = cnx.prepareStatement(req2);
                pstm2.setInt(1, id_p);
                pstm2.setDate(2, (java.sql.Date) p.getDate_naissance());
                pstm2.setString(3, p.getPhoto_p());
                pstm2.setString(4, p.getDescriptions());

                pstm2.executeUpdate();
                String req4 = "INSERT INTO `grade`( id_ug,`grade`) VALUES (?,?)";
                PreparedStatement pstm4 = cnx.prepareStatement(req4);
                pstm4.setInt(1, id_p);
                pstm4.setString(2, g.getGrade());
                pstm4.executeUpdate();
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void desactivierUser(int id) {
        try {
            String req = "update user set enabled=0 where id=?";
            PreparedStatement pstm = cnx.prepareStatement(req);
            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void modifierUser(int id, String username, String email, String password,
            String firstname, String lastname, String nationalite, String langues) {
        try {
            String req = "update user set username=?,email=?,password=?,firstname=?,lastname=?,nationalite=?,langues=? where id=?";
            PreparedStatement pstm = cnx.prepareStatement(req);
            pstm.setString(1, username);
            pstm.setString(2, email);
            pstm.setString(3, password);
            pstm.setString(4, firstname);
            pstm.setString(5, lastname);
            pstm.setString(6, nationalite);
            pstm.setString(7, langues);
            pstm.setInt(8, id);
            System.out.println(pstm);
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
