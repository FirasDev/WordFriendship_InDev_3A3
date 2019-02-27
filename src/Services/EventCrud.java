package Services;

import Entities.Event;
import Utils.MyDBcon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventCrud {

    //////////////////////////////////////// Ajouter //////////////////////////////////////////////////

    public static void ajouterEvenement(Event p) {
        try {
            Connection cnx = MyDBcon.getInstance().getCon();

           

        

            String query1 = "INSERT INTO `evenement`( `Nom`, `Type`, `Lieu`, `Num_villa`, `Pays`, `Date_debut`, `Date_fin`, `Description`, `nbr_place`, `frais`,`Id_user`,`URL`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            
            PreparedStatement preparedStmt2 = cnx.prepareStatement(query1);
           
            preparedStmt2.setString(1, p.getNom());
            preparedStmt2.setString(2, p.getType());
            preparedStmt2.setString(3, p.getLieu());
            preparedStmt2.setInt(4, p.getNum_villa());

            preparedStmt2.setString(5, p.getPays());
            preparedStmt2.setString(6, p.getDate_debut());
            preparedStmt2.setString(7, p.getDate_fin());
            preparedStmt2.setString(8, p.getDescr());
            preparedStmt2.setInt(9, p.getNbr_place());
            preparedStmt2.setFloat(10, p.getFrais());
            preparedStmt2.setInt(11, p.getId_user());
            preparedStmt2.setString(12, p.getPicture());
            preparedStmt2.executeUpdate();
          

           
        } catch (SQLException ex) {
            Logger.getLogger(EventCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //////////////////////////////////////// Afficher //////////////////////////////////////////////////

    public static ArrayList<Event> getAllEvents(int id) throws SQLException {
        Connection cnx = MyDBcon.getInstance().getCon();
        ArrayList<Event> retour = new ArrayList<>();
        Statement stm = cnx.createStatement();
        String req = "SELECT `Nom`, `Type`, `Lieu`, `Num_villa`, `Pays`, `Date_debut`, `Date_fin`, `Description`, `nbr_place`, `frais` FROM `evenement` WHERE Id_user!='" + id + "'";
        ResultSet resultat = stm.executeQuery(req);
        while (resultat.next()) {

            String nom = resultat.getString(1);
            String type = resultat.getString(2);
            String lieu = resultat.getString(3);
            int num_villa = resultat.getInt(4);
            String pays = resultat.getString(5);
            String date_debut = resultat.getString(6);
            String date_fin = resultat.getString(7);
            String descr = resultat.getString(8);
            int nbr_place = resultat.getInt(9);
            float frais = resultat.getFloat(10);

            retour.add(new Event(nom, type, lieu, num_villa, pays, date_debut, date_fin, descr, nbr_place, frais));

        }

        return retour;
    }

    //////////////////////////////////////// Afficher //////////////////////////////////////////////////

    public static ArrayList<Event> getEvents(int id) throws SQLException {
        Connection cnx = MyDBcon.getInstance().getCon();
        ArrayList<Event> retour = new ArrayList<>();
        Statement stm = cnx.createStatement();
        String req = "SELECT `Id_event`,`Nom`, `Type`, `Lieu`, `Num_villa`, `Pays`, `Date_debut`, `Date_fin`, `Description`, `nbr_place`, `frais` FROM `evenement` where ID_user='" + id + "'";
        ResultSet resultat = stm.executeQuery(req);
        while (resultat.next()) {

            int ide = resultat.getInt(1);
            String nom = resultat.getString(2);
            String type = resultat.getString(3);
            String lieu = resultat.getString(4);
            int num_villa = resultat.getInt(5);
            String pays = resultat.getString(6);
            String date_debut = resultat.getString(7);
            String date_fin = resultat.getString(8);
            String descr = resultat.getString(9);
            int nbr_place = resultat.getInt(10);
            float frais = resultat.getFloat(11);

            retour.add(new Event(ide, nom, type, lieu, num_villa, pays, date_debut, date_fin, descr, nbr_place, frais));

        }

        return retour;
    }

    //////////////////////////////////////// Modifier //////////////////////////////////////////////////
    public static void updateEvent(int id, String nom, String type, String lieu, int num, String pays, String dated, String datef, String descr, int nbr, float frais) {

        try {
            Connection cnx = MyDBcon.getInstance().getCon();
            String sql = "UPDATE `evenement` SET `Nom`=?,`Type`=?,`Lieu`=?,`Num_villa`=?,`Pays`=?,`Date_debut`=?,`Date_fin`=?,`Description`=? ,`nbr_place`=?,`frais`=? WHERE Id_event=?";
            PreparedStatement pstmt = cnx.prepareStatement(sql);

            pstmt.setString(1, nom);
            pstmt.setString(2, type);
            pstmt.setString(3, lieu);
            pstmt.setInt(4, num);
            pstmt.setString(5, pays);
            pstmt.setString(6, dated);
            pstmt.setString(7, datef);
            pstmt.setString(8, descr);
            pstmt.setInt(9, nbr);
            pstmt.setFloat(10, frais);
            pstmt.setInt(11, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //////////////////////////////////////// Supprimer //////////////////////////////////////////////////
    public static void deleteEvent(int num) {

        try {
            Connection cnx = MyDBcon.getInstance().getCon();
            String sql = "DELETE FROM `evenement` WHERE Num_villa =?";

            PreparedStatement pstmt = cnx.prepareStatement(sql);

            pstmt.setInt(1, num);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //////////////////////////////////////// Afficher //////////////////////////////////////////////////
    public static ArrayList<Event> getAllEvent(int id) throws SQLException {
        Connection cnx = MyDBcon.getInstance().getCon();
        ArrayList<Event> retour = new ArrayList<>();
        Statement stm = cnx.createStatement();
        //String req = "SELECT `Id_event`, `Nom`, `Type`, `Lieu`, `Num_villa`, `Pays`, `Date_debut`, `Date_fin`, `Description`, `nbr_place`, `frais`,`Id_user` FROM `evenement` WHERE Id_user!='"+id+"'";
        String req = "SELECT  evenement.Id_event,`Nom`, `Type`, `Lieu`, `Num_villa`, `Pays`, `Date_debut`, `Date_fin`, `Description`, `nbr_place`, `frais`,evenement.Id_user FROM `evenement` inner join reservation where (evenement.Id_user!='" + id + "' and reservation.Id_user!=+'" + id + "')";
        ResultSet resultat = stm.executeQuery(req);
        while (resultat.next()) {

            int ide = resultat.getInt("evenement.Id_event");
            String nom = resultat.getString("Nom");
            String type = resultat.getString("Type");
            String lieu = resultat.getString("Lieu");
            int num_villa = resultat.getInt("Num_villa");
            String pays = resultat.getString("Pays");
            String date_debut = resultat.getString("Date_debut");
            String date_fin = resultat.getString("Date_fin");
            String descr = resultat.getString("Description");
            int nbr_place = resultat.getInt("nbr_place");
            float frais = resultat.getFloat("frais");
            int idu = resultat.getInt("Id_user");

            retour.add(new Event(ide, nom, type, lieu, num_villa, pays, date_debut, date_fin, descr, nbr_place, frais, idu));

        }

        return retour;
    }

}
