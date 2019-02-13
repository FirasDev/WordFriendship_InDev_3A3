/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Amis;
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
 * @author Jamila
 */
public class AmisCrud {
    
        
static Connection cnx;

    public AmisCrud() throws SQLException {
        cnx = MyDBcon.getInstance().getCon();

    }

    public static void ajouterAmis(Amis a) {
        try {

            System.out.println("connexion établie");
            Statement stm = cnx.createStatement();
            String req = "INSERT INTO `amis`( `id_ue`, `id_ur`) VALUES ('" + a.getId_ue() + "','" + a.getId_ur() + "')";
            stm.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   public static void supprimerAmis(int id_ue, int id_ur) {  //refuse une invitation
        try {
            String req = "delete from amis where id_ue=? and id_ur=?";
            PreparedStatement pstm = cnx.prepareStatement(req);
            pstm.setInt(1, id_ue);
            pstm.setInt(2, id_ur);
            System.out.println(pstm);
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void AccepterInvit(int id_ue, int id_ur ) { //aacepter une invitation 
        try {
            String req = "update amis set etat_a=1 where id_ue=? and id_ur=?";
            PreparedStatement pstm = cnx.prepareStatement(req);
           // pstm.setInt(1, etat_a);
            pstm.setInt(1, id_ue);
            pstm.setInt(2, id_ur);
            pstm.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
 
 public static void historique(int id){
	
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
}
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
}
