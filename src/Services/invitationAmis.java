/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Sessions;
import Entities.User;
import static Services.AdminService.cnx;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.mail.Session;

/**
 *
 * @author Jamila
 */
public class invitationAmis {
    
     public static  ArrayList<User> afficherProfil() throws SQLException {//afficher liste invit
            ArrayList<User> matchingTypes = new ArrayList<>();
         
            
                 String req2 = "SELECT user.id, `username`, `firstname`, `lastname` FROM user inner join amis WHERE (amis.id_ur='"+Sessions.getCurrentSession()+"' and etat_a=0 and user.id=amis.id_ue)";
                 PreparedStatement pstm2 = cnx.prepareStatement(req2);               
                 ResultSet rs= pstm2.executeQuery(req2); 
 
                 while (rs.next() )
                 {     
            int id=rs.getInt("user.id");
            String username = rs.getString("username");
            String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");
           
                    
            matchingTypes.add(new User(id,username,firstname,lastname));
                 
            }
                    
            return matchingTypes;
        }
    
}
