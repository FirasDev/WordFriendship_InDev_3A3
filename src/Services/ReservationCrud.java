package Services;

import Entities.Event;
import Entities.Reservation;
import Utils.MyDBcon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservationCrud {

    //////////////////////////////////////// Ajouter //////////////////////////////////////////////////
    public static void reserverEvenement(Event r, int Id_user) {
        try {
            Connection cnx = MyDBcon.getInstance().getCon();

            String req = "INSERT INTO `reservation`(`Id_event`, `Id_user`) VALUES (?,?)";
            PreparedStatement pstm = cnx.prepareStatement(req);

            pstm.setInt(1, r.getId());
            pstm.setInt(2, Id_user);

            pstm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ReservationCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //////////////////////////////////////// Annuler //////////////////////////////////////////////////
    public static void annulerReservation(int id) {
        try {
            Connection cnx = MyDBcon.getInstance().getCon();
            String sql = "DELETE FROM `reservation` WHERE Id_event=?";

            PreparedStatement pstmt = cnx.prepareStatement(sql);

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
      //////////////////////////////////////// Afficher //////////////////////////////////////////////////

    public static ArrayList<Event> getAllReserv(int id) throws SQLException {
        Connection cnx = MyDBcon.getInstance().getCon();
        ArrayList<Event> retour = new ArrayList<>();
        Statement stm = cnx.createStatement();
        String req = "SELECT evenement.Id_event,`Nom`, `Type`, `Lieu`, `Num_villa`, `Pays`, `Date_debut`, `Date_fin`, `Description`, `nbr_place`, `frais` FROM `evenement` inner join reservation where (evenement.Id_event=reservation.Id_event and reservation.Id_user='" + id + "')";

        ResultSet resultat = stm.executeQuery(req);
        while (resultat.next()) {
            int ide=resultat.getInt(1);
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

            retour.add(new Event(ide, nom, type, lieu,num_villa, pays,  date_debut, date_fin,descr,  nbr_place, frais));

        }

        return retour;
    }

    public static void updateNombrePlace(Event e) throws SQLException {
        Connection cnx = MyDBcon.getInstance().getCon();
        String req = "UPDATE `evenement` SET nbr_place=? where Id_event=?";
            PreparedStatement stm = cnx.prepareStatement(req);
        
            stm.setInt(1,e.getNbr_place());
            stm.setInt(2,e.getId());
            
             stm.executeUpdate();
    }
}
