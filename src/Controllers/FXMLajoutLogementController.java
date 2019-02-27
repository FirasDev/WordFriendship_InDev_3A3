/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Cite;
import Entities.Logement;
import Services.LogementCrud;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import Services.CiteCrud;
import com.jfoenix.controls.JFXButton;
import com.teknikindustries.bulksms.SMS;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.controlsfx.control.Notifications;
import Entities.Sessions;
/**
 * FXML Controller class
 *
 * @author DongFeng
 */
public class FXMLajoutLogementController implements Initializable {

    @FXML
    private Button Valider;
    @FXML
    private Button Annuler;
    @FXML
    private TextField nom_log;
    @FXML
    private TextArea desc;
    @FXML
    private TextField nbr_place;
    @FXML
    private TextField prix;
    @FXML
    private TextField nom_cite;
    @FXML
    private TextField num_villa;
    @FXML
    private ImageView photo;
    private String content;

    @FXML
    private JFXButton ajouterPhoto;

    public static int id_user = Sessions.getCurrentSession();
   // public static int id_user = 1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Valider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (controleDeSaisi()) {

                    if (nom_log.getText().isEmpty()) {
                        nom_log.setText("");
                    }
                    if (desc.getText().isEmpty()) {
                        desc.setText("");
                    }
                    if (nbr_place.getText().isEmpty()) {
                        nbr_place.setText("");
                    }
                    if (prix.getText().isEmpty()) {
                        prix.setText("");
                    }
                    if (nom_cite.getText().isEmpty()) {
                        nom_cite.setText("");
                    }
                    if (num_villa.getText().isEmpty()) {
                        num_villa.setText("");
                    }

                }

                try {
                    LogementCrud lc = new LogementCrud();
                    CiteCrud cc = new CiteCrud();
                    cc.Ajouter_cite(new Cite(nom_cite.getText(), Integer.parseInt(num_villa.getText())), new Logement(nom_log.getText(), desc.getText(), Integer.parseInt(prix.getText()), Integer.parseInt(num_villa.getText()),content), id_user);
                    Notifications.create().darkStyle().title("Amiticia").text("Ajout avec Succes").position(Pos.BOTTOM_RIGHT).hideAfter(Duration.seconds(3)).showInformation();
                    nom_cite.clear();
                    num_villa.clear();
                    desc.clear();
                    nom_log.clear();
                    nbr_place.clear();
                    prix.clear();
                 //   SMS ss=new SMS();
                    //   ss.SendSMS("abdou525", "19201995", "heello", "21654577109", "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLajoutLogementController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        Annuler.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    Annuler.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/Dashboard.fxml")));

                } catch (IOException ex) {
                    Logger.getLogger(FXMLaffichetousController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }///////fin initialize

    ////controle saisieee lappel mtee3ou lfouu9 fou9 lajout 
    private boolean controleDeSaisi() {

        if (nom_log.getText().isEmpty() || desc.getText().isEmpty() || nbr_place.getText().isEmpty()
                || prix.getText().isEmpty() || nom_cite.getText().isEmpty() || num_villa.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
            return false;
        } else {

            if (!Pattern.matches("[0-9]*", prix.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez la reference ! ");
                prix.requestFocus();
                prix.selectEnd();
                return false;
            }

            if (!Pattern.matches("[0-9]*", nbr_place.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le libelle du produit ! ");
                nbr_place.requestFocus();
                nbr_place.selectEnd();
                return false;
            }

        }
        return true;
    }

    public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }

    @FXML
    String uploadPhoto(ActionEvent event) throws IOException {
        FileChooser file = new FileChooser(); //pour choisir la photo
        file.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", "*.jpg", "*.png", "*.bmp"));
        file.setTitle("Choisir une photo du produit");

        File selected_photo = file.showOpenDialog((Stage) ajouterPhoto.getScene().getWindow());
        if (selected_photo != null) {
            if ((selected_photo.length() / 1024) / 1024 < 2.0) {
                String path = selected_photo.getAbsolutePath();
                BufferedImage bufferedImage = ImageIO.read(selected_photo);
                WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
                photo.setImage(image);

                File img = new File(path);

                content = img.getPath();
                copyFile();

            } else {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Taile trop grande !", "Veuillez choisir une photo de profil avec une taille < 2 Mo");
            }
        }
        return content;

    }

    public void copyFile() throws IOException {
        File srcd = Paths.get(content).toFile();
        File sd = Paths.get("C:/Users/ASUS/Desktop/DB/WordFriendship_InDev_3A3-master/src/Assets/").toFile();

        //copy source to target using Files Class
        FileUtils.copyFileToDirectory(srcd, sd);

    }

}
