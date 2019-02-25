/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Amis;
import Entities.Sessions;
import Utils.MyDBcon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Entities.User;
import static Services.UserCrud.cnx;
/**
 *
 * @author Jamila
 */
public class AmisCrud {
    
        
static Connection cnx;

    public AmisCrud() throws SQLException {
        cnx = MyDBcon.getInstance().getCon();

    }

    public static void ajouterAmis(int a,int s) {
        try {
   
            
            
            System.out.println("connexion établie");
            Statement stm = cnx.createStatement();
            String req = "INSERT INTO `amis`( id_ue, `id_ur`) VALUES ('"+s+"','" +a+ "')";
            stm.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   public static void supprimerAmis(int z,int s) {  //refuse une invitation
        try {
            String req = "delete from amis where (id_ue='"+z+"' and id_ur='"+s+"')";
           Statement pstm=cnx.createStatement();
            
       
          
        pstm.execute(req);
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
   
   public static void supprimer(int z,int s) {  //supprimer amis
        try {
           String req = "delete from amis where (id_ue='"+z+"' and id_ur='"+s+"' and etat_a=1) or (id_ue='"+s+"' and id_ur='"+z+"' and etat_a=1)";
           Statement pstm=cnx.createStatement();
            
       
          
        pstm.execute(req);
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void AccepterInvit(int id_ue) { //aacepter une invitation 
        try {
            String req = "update amis set etat_a=1 where id_ue='"+id_ue+"' and id_ur='"+Sessions.getCurrentSession()+"'";
            PreparedStatement pstm = cnx.prepareStatement(req);
           
            pstm.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
 
 /*public static void historique(int id){
	
	PreparedStatement ps=null;
	ResultSet rs=null;
	try {
		String query="select user.firstname,user.lastname ,amis.date_a from user,amis where amis.id_ur=user.id and etat_a=1 and amis.id_ue=?";
		ps=cnx.prepareStatement(query);
		 ps.setInt(1, id);
		rs=ps.executeQuery();
		while(rs.next()){
		System.out.println("firstname -"+rs.getString("firstname"));
                System.out.println("lastname -"+rs.getString("lastname"));
		System.out.println("date -"+rs.getDate("date_a"));
		
		
		}
                String query2="select user.firstname,user.lastname ,amis.date_a from user,amis where amis.id_ue=user.id and etat_a=1 and amis.id_ur=?";
		ps=cnx.prepareStatement(query2);
		 ps.setInt(1, id);
		rs=ps.executeQuery();
		while(rs.next()){
		System.out.println("firstname -"+rs.getString("firstname"));
                System.out.println("lastname -"+rs.getString("lastname"));
		System.out.println("date -"+rs.getDate("date_a"));
		
		
		}
	} catch (Exception e) {
		System.out.println(e);
	}
}*/
 public static void ListAmis(int id){
	
	PreparedStatement ps=null;
	ResultSet rs=null;
	try {
		String query="select user.firstname,user.lastname from user,amis where amis.id_ur=user.id and etat_a=1 and amis.id_ue=?";
		ps=cnx.prepareStatement(query);
		 ps.setInt(1, id);
		rs=ps.executeQuery();
		while(rs.next()){
		System.out.println("firstname -"+rs.getString("firstname"));
                System.out.println("lastname -"+rs.getString("lastname"));
		
		
		
		}
                String query2="select user.firstname,user.lastname from user,amis where amis.id_ue=user.id and etat_a=1 and amis.id_ur=?";
		ps=cnx.prepareStatement(query2);
		 ps.setInt(1, id);
		rs=ps.executeQuery();
		while(rs.next()){
		System.out.println("firstname -"+rs.getString("firstname"));
                System.out.println("lastname -"+rs.getString("lastname"));
		
		
		
		}
	} catch (Exception e) {
		System.out.println(e);
	}
}
 
  public static ArrayList<User> getUsersAmis() throws SQLException {
        ArrayList<User> retour = new ArrayList<>();
        Statement stm = cnx.createStatement();
        String req = "SELECT id,username,email,firstname,lastname,nationalite,langues,descriptions,sexe FROM user";
        ResultSet resultat = stm.executeQuery(req);
        while (resultat.next()) {
            int id=resultat.getInt("id");
            String username = resultat.getString("username");
            String email = resultat.getString("email");
            String firstname = resultat.getString("firstname");
            String lastname = resultat.getString("lastname");
            String nationalite = resultat.getString("nationalite");
            String langues = resultat.getString("langues");
            String descriptions = resultat.getString("descriptions");
            String sexe = resultat.getString("sexe");
            retour.add(new User(id,username,email, firstname, lastname, nationalite, langues, descriptions,sexe));

        }

        return retour;
    } 
 

 

}
