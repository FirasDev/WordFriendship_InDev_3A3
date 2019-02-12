/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.TravelGroup ;
import Entities.Travelbuddy;
import Utils.MyDBcon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yefet
 */
public class TravelGroupCrud {

    public static void AjouterTravelGroup(TravelGroup t) throws SQLException {
        try {
            Connection con = MyDBcon.getInstance().getCon();

            String req = "INSERT INTO `travel_group`( `description, `destination`, `date_debut`, `date_fin` , `plan`) VALUES (?,?,?,?,?)";
            PreparedStatement pstm = con.prepareStatement(req);
            pstm.setString(1, t.getTitle());
            pstm.setString(2, t.getDestination());
            pstm.setDate(3, (java.sql.Date) t.getDate_debut());
            pstm.setDate(4, (java.sql.Date) t.getDate_fin());
            pstm.setString(5, t.getPlan());

            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TravelGroupCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void supprimerTravelGroup(TravelGroup t) throws SQLException {

        Connection con = MyDBcon.getInstance().getCon();
        String query = "DELETE FROM `travel_group` WHERE id =?";
        try {

            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1, t.getId());
            stm.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(TravelbuddyCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void ajouterUnMember(Travelbuddy t)  {

        try {
            Connection con = MyDBcon.getInstance().getCon();

            Statement stm = con.createStatement();
            String req = "INSERT INTO `travel_buddy`( `id_travel_group ) " + "" + "VALUES ('   " + t.getId() + "')";
            stm.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(TravelGroupCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void supprimerUnMember(Travelbuddy t) throws SQLException {
        Connection con = MyDBcon.getInstance().getCon();
        String query = "DELETE id_travel_group FROM `travel_buddy` WHERE id_travel_group =?";
        try {

            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1, t.getId());
            stm.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(TravelbuddyCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    public static List<TravelGroup> getAllTravelGroup() throws SQLException {

        Connection con = MyDBcon.getInstance().getCon();
        String query = "SELECT * FROM `travel_group` ";
        List<TravelGroup> retour = new ArrayList<TravelGroup>();
        retour = null;
        try {

            Statement stm = con.createStatement();
            ResultSet resultat = stm.executeQuery(query);
            while (resultat.next()) {

                retour.add(new TravelGroup(resultat.getString("title"),resultat.getString("destination"), resultat.getDate("date_debut"), resultat.getDate("date_fin"), resultat.getString("plan")));
                return retour;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TravelbuddyCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public static List<TravelGroup> RechercherSelonDestination(String dest) throws SQLException {
    List<TravelGroup> retour = new ArrayList<TravelGroup>() ;
     retour =null ; 
     Connection con = MyDBcon.getInstance().getCon();
     String query = "SELECT * `travel_group` WHERE destination =? ";
     try {
            PreparedStatement stm = con.prepareStatement(query);
            ResultSet resultat = stm.executeQuery();
            stm.setString(1, dest);
            while(resultat.next())
            {
retour.add(new TravelGroup(resultat.getString("title"), resultat.getString("destination"), resultat.getDate("date_debut"), resultat.getDate("date_fin"), resultat.getString("plan")));                
            }
        } catch (SQLException ex) {
            Logger.getLogger(TravelbuddyCrud.class.getName()).log(Level.SEVERE, null, ex);
            
        }
     return null ; 
     
 }
    
    
    
}
