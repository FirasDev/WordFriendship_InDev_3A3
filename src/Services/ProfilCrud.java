/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Profil;
import Entities.User;
import Utils.MyDBcon;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jamila
 */
public class ProfilCrud {

    static Connection cnx;

    public ProfilCrud() throws SQLException {
        cnx = MyDBcon.getInstance().getCon();

    }

    public static void modifierProfil(int id_p, Date date_naissnace, String photo_p, String descriptions) {
        try {
            String req = "update profil set date_naissance=?,photo_p=?,descriptions=? where id_p=?";
           PreparedStatement pstm = cnx.prepareStatement(req);

            pstm.setDate(1, date_naissnace);
            pstm.setString(2, photo_p);
            pstm.setString(3, descriptions);
            pstm.setInt(4, id_p);
            System.out.println(pstm);
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static ArrayList<Profil> getAllProfils() throws SQLException {//afficher liste des profils admin
        ArrayList<Profil> retour = new ArrayList<>();
        Statement stm = cnx.createStatement();
        String req = "SELECT profil.date_naissance,profil.photo_p,profil.descriptions,user.username,user.email,user.roles,user.firstname,user.lastname,user.nationalite,user.langues FROM profil,user where profil.id_up=user.id";
        ResultSet resultat = stm.executeQuery(req);
        while (resultat.next()) {

            Date date_naissance = resultat.getDate("date_naissance");
            String photo_p = resultat.getString("photo_p");
            String descriptions = resultat.getString("descriptions");
            String username = resultat.getString("username");
            String email = resultat.getString("email");
            String roles = resultat.getString("roles");
            String firstname = resultat.getString("firstname");
            String lastname = resultat.getString("lastname");
            String nationalite = resultat.getString("nationalite");
            String langues = resultat.getString("langues");
            retour.add(new Profil(date_naissance, photo_p, descriptions));

        }

        return retour;
    }
public static HashMap<Profil,User> afficheProfil(int id) throws SQLException {//afficher un profil
            HashMap<Profil,User> matchingTypes = new HashMap<>();
         
            {
                 String req2 = "SELECT profil.date_naissance,profil.photo_p,profil.descriptions,user.username,user.email,user.roles,user.firstname,user.lastname,user.nationalite,user.langues FROM profil,user where profil.id_up=user.id and profil.id_up=?";
                 PreparedStatement pstm2 = cnx.prepareStatement(req2); 
                 pstm2.setInt(1, id);
                  ResultSet rs= pstm2.executeQuery(req2);       
                 while (rs.next() )
                 {     
                  Date date_naissance = rs.getDate("date_naissance");
            String photo_p = rs.getString("photo_p");
            String descriptions = rs.getString("descriptions");
            String username = rs.getString("username");
            String email = rs.getString("email");
            String roles = rs.getString("roles");
            String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");
            String nationalite = rs.getString("nationalite");
            String langues = rs.getString("langues");
                matchingTypes.put(new Profil(date_naissance, photo_p, descriptions)
                ,new User(username,email,roles,firstname,lastname,nationalite,langues));          
            }}
            return matchingTypes;
        }

}