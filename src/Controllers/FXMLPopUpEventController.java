/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Event;
import Services.ReservationCrud;
import Utils.MyDBcon;
import Entities.Sessions;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class FXMLPopUpEventController implements Initializable {

    @FXML
    private Button res;

    @FXML
    private Button cancel;

    @FXML
    private Label nom;

    @FXML
    private Label type;

    @FXML
    private Label lieu;

    @FXML
    private Label pays;

    @FXML
    private Label dated;

    @FXML
    private Label datef;

    @FXML
    private Label nbr;

    @FXML
    private Label frais;

    @FXML
    private Label num;

    @FXML
    private Label descr;
    @FXML
    private JFXTextField place;
    
    @FXML
    private ImageView photo;


    private static Event evv = new Event();

    public static Event getEvv() {
        return evv;
    }

    public static void setEvv(Event evv) {
        FXMLPopUpEventController.evv = evv;
    }
    public static int id = Sessions.getCurrentSession();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        try {
            ReservationCrud rc = new ReservationCrud();
            
            afficher();
            
            
            res.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    
                    try {
                        
                        if (controleDeSaisi()) {
                            if (place.getText().isEmpty()) {
                                place.setText("");
                            }
                            
                        }

                        if (testNbrPlace(evv)) {
                            rc.reserverEvenement(evv, id);
                             System.out.println(evv);
                            afficher();
                            
                           
                            place.clear();
                            
                            Notifications.create()
                                    .title("Amiticia")
                                    .text("Reservation effectuer avec success !!").darkStyle().hideAfter(Duration.seconds(5)).position(Pos.BOTTOM_RIGHT)
                                    .showInformation();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLPopUpEventController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLPopUpEventController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(FXMLPopUpEventController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPopUpEventController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private boolean controleDeSaisi() {

        if (Pattern.matches("[0-9]*", place.getText()) && place.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le nombre de place ! ");
            place.requestFocus();
            place.selectEnd();
            return false;
        }

        return true;
    }

    public boolean testNbrPlace(Event e) throws SQLException {
        if (Integer.parseInt(place.getText()) > e.getNbr_place()) {
            Notifications.create().title("Erreur").text("Pas de place disponible").darkStyle().position(Pos.BOTTOM_RIGHT).hideAfter(Duration.seconds(2)).showError();
        } else {
            e.setNbr_place(e.getNbr_place() - Integer.valueOf(place.getText()));

            ReservationCrud.updateNombrePlace(e);

            return true;
        }
        return false;
    }

    public void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }

    public void afficher() throws SQLException, IOException {
        nom.setText(evv.getNom());
        type.setText(evv.getType());
        lieu.setText(evv.getLieu());
        pays.setText(evv.getPays());
        nbr.setText(String.valueOf(evv.getNbr_place()));
        num.setText(String.valueOf(evv.getNum_villa()));
        dated.setText(evv.getDate_debut());
        datef.setText(evv.getDate_fin());
        descr.setText(evv.getDescr());
        frais.setText(String.valueOf(evv.getFrais()));
         getImg(evv.getId());
        
    }
     public void getImg(int idd) throws SQLException, IOException {
        Connection cnx = MyDBcon.getInstance().getCon();

        String q = "SELECT `URL` FROM `evenement` WHERE Id_event='"+idd+"'";

        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(q);

        if (rs.next()) {

            String imagePath = "file:/" + rs.getString("URL");
            Image image = new Image(imagePath);

            photo.setImage(image);
        }
    }
}
