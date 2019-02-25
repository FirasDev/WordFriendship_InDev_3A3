/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Grade;
import Entities.Sessions;
import Entities.User;
import static Services.AdminService.cnx;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Jamila
 */
public class ChercherAmis {
    
   public static  ArrayList<User> chercherAmis() throws SQLException {//afficher liste des abonnés
            ArrayList<User> matchingTypes = new ArrayList<>();
         
            
                 String req2 = "SELECT id, `username`, `firstname`, `lastname` FROM user ";
                 PreparedStatement pstm2 = cnx.prepareStatement(req2);               
                 ResultSet rs= pstm2.executeQuery(req2); 
 
                 while (rs.next() )
                 {     
            int id=rs.getInt("id");
            String username = rs.getString("username");
            String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");
           
                    
            matchingTypes.add(new User(id,username,firstname,lastname));
                 
            }
                    
            return matchingTypes;
        }
    
}
