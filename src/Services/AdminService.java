/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Grade;
import Entities.User;
import static Services.UserCrud.cnx;
import Utils.MyDBcon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Jamila
 */
public class AdminService {
     static Connection cnx;

    public AdminService() throws SQLException {
        cnx = MyDBcon.getInstance().getCon();

    }
    /*public static  HashMap<User,Grade> afficheProfil() throws SQLException {//afficher liste des profils
            HashMap<User,Grade> matchingTypes = new HashMap<>();
         
            
                 String req2 = "SELECT user.username,user.email,user.roles,user.firstname,user.lastname,user.nationalite,user.langues,\n" +
                               " grade.grade,user.date_naissance,user.descriptions,user.sexe FROM user,grade where roles='ABONNEE' and grade.id_ug=user.id";
                 PreparedStatement pstm2 = cnx.prepareStatement(req2);               
                 ResultSet rs= pstm2.executeQuery(req2); 
 
                 while (rs.next() )
                 {     
            
            String username = rs.getString("username");
            String email = rs.getString("email");
            String roles = rs.getString("roles");
            String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");
            String nationalite = rs.getString("nationalite");
            String langues = rs.getString("langues");
            String date_naissance = rs.getString("date_naissance");
            String descriptions = rs.getString("descriptions");
            String sexe=rs.getString("sexe");
            String grade = rs.getString("grade");
                    
            matchingTypes.put(new User(username,email,roles,firstname,lastname,nationalite,langues,date_naissance,descriptions,sexe),new Grade(grade));
            
            }
                    
            return matchingTypes;
        }*/
     public static ArrayList<User> getUsersAdmin() throws SQLException {
        ArrayList<User> retour = new ArrayList<>();
        Statement stm = cnx.createStatement();
        String req = "SELECT * FROM user";
        ResultSet resultat = stm.executeQuery(req);
        while (resultat.next()) {

            String email = resultat.getString("email");
            
            String firstname = resultat.getString("firstname");
            String lastname = resultat.getString("lastname");
            String nationalite = resultat.getString("nationalite");
            String langues = resultat.getString("langues");
            
            String descriptions = resultat.getString("descriptions");
            String sexe = resultat.getString("sexe");
            retour.add(new User(email, firstname, lastname, nationalite, langues, descriptions,sexe));

        }

        return retour;
    }
}
