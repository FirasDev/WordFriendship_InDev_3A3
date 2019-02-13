package Services;

import Entities.Event;
import Entities.Reservation;
import Utils.MyDBcon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservationCrud {

    /* Connection cnx;

     public ReservationCrud() throws SQLException {
     cnx = MyDBcon.getInstance().getCon();
     */
    //////////////////////////////////////// Ajouter //////////////////////////////////////////////////
    public static void reserverEvenement(Reservation r) {
        try {
            Connection cnx = MyDBcon.getInstance().getCon();

            String req = "INSERT INTO `reservation`(`Id_event`, `Id_user`) VALUES (?,?)";
            PreparedStatement pstm = cnx.prepareStatement(req);

            pstm.setInt(1, r.getId_event());
            pstm.setInt(2, r.getId_user());

            pstm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ReservationCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //////////////////////////////////////// Annuler //////////////////////////////////////////////////
    public static void annulerReservation(int id) {
        try {
            Connection cnx = MyDBcon.getInstance().getCon();
            String sql = "DELETE FROM `reservation` WHERE `Id_event`=?";
            
            PreparedStatement pstmt = cnx.prepareStatement(sql);

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
