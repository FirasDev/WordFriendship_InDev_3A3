/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Grade;
import Utils.MyDBcon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jamila
 */
public class GradeCrud {
    static Connection cnx;
    
     public GradeCrud() throws SQLException {
       cnx = MyDBcon.getInstance().getCon();
       
        
    }
     
     public static void modifierAlabase(int id_ug){
	try {
            Statement stm = cnx.createStatement();
            String req2="SELECT score_final FROM score";
                    ResultSet resultat = stm.executeQuery(req2);
        while(resultat.next()){
           int score= resultat.getInt(1);
           String grade="";
        if(score>20)
        {
            grade="debutant";
        }
        else if (score>40)
        { grade="expert";
        }
		String req="update grade set grade=? where id_ug=?";
		PreparedStatement pstm = cnx.prepareStatement(req);
                pstm.setString(1,grade );
                pstm.setInt(2, id_ug);
		System.out.println(pstm);
		pstm.executeUpdate();
        
        }
	} catch (SQLException ex) {
             Logger.getLogger(GradeCrud.class.getName()).log(Level.SEVERE, null, ex);
         }
	}
     
    public static ArrayList<Grade> getAllGrades() throws SQLException {
       ArrayList<Grade> retour = new ArrayList<>();
       Statement stm = cnx.createStatement();
        String req = "SELECT grade.id_ug,grade.grade FROM grade,user where grade.id_ug=user.id";
        ResultSet resultat = stm.executeQuery(req);
        while(resultat.next()){
           
           
           String grade= resultat.getString("grade");
         retour.add(new Grade(grade));
            
        }
            
        return retour;
    } 
}
