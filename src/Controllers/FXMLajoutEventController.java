package Controllers;

import Entities.Event;
import Services.EventCrud;
import Utils.MyDBcon;
import Entities.Sessions;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.controlsfx.control.Notifications;
import org.apache.commons.io.FileUtils;

public class FXMLajoutEventController implements Initializable {

    @FXML
    private JFXButton Valider;
    @FXML
    private JFXButton Annuler;
    @FXML
    private JFXTextField Nom;
    @FXML
    private JFXTextField Type;
    @FXML
    private JFXTextField Lieu;
    @FXML
    private JFXTextField Pays;
    @FXML
    private JFXTextField Frais;
    @FXML
    private JFXTextArea Descr;
    @FXML
    private JFXTextField nbr;
    @FXML
    private JFXTextField num;

    @FXML
    private DatePicker dated;
    @FXML
    private DatePicker datef;
    private static int Id_user = Sessions.getCurrentSession();
    @FXML
    private Button ajouterPhoto;
    @FXML
    private ImageView photo;

    @FXML
    private JFXButton recup;
    private String content;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Valider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (controleDeSaisi()) {
                    if (Nom.getText().isEmpty()) {
                        Nom.setText("");
                    }
                    if (Type.getText().isEmpty()) {
                        Type.setText("");
                    }
                    if (Lieu.getText().isEmpty()) {
                        Lieu.setText("");
                    }
                    if (Pays.getText().isEmpty()) {
                        Pays.setText("");
                    }
                    if (Frais.getText().isEmpty()) {
                        Frais.setText("");
                    }
                    if (Descr.getText().isEmpty()) {
                        Descr.setText("");
                    }
                    if (nbr.getText().isEmpty()) {
                        nbr.setText("");
                    }
                    if (num.getText().isEmpty()) {
                        num.setText("");
                    }

                    testDate();

                }
            }
        });
        Annuler.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Annuler.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/FXMLafficheAllEvents.fxml")));

                } catch (IOException ex) {
                    Logger.getLogger(FXMLajoutEventController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private boolean controleDeSaisi() {

        if (Nom.getText().isEmpty() || Type.getText().isEmpty() || Lieu.getText().isEmpty()
                || Pays.getText().isEmpty() || Descr.getText().isEmpty() || Frais.getText().isEmpty() || num.getText().isEmpty() || nbr.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
            return false;
        } else {

            if (!Pattern.matches("[0-9]*", Frais.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez la reference ! ");
                Frais.requestFocus();
                Frais.selectEnd();
                return false;
            }

            if (!Pattern.matches("[0-9]*", nbr.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le libelle du produit ! ");
                nbr.requestFocus();
                nbr.selectEnd();
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

    public void testDate() {
        int test = dated.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US)).compareTo(datef.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US)));

        if (test > 0) {
            Notifications.create().title("Error").text("Veillez saisir une date de fin > a la date de début").darkStyle().position(Pos.BOTTOM_RIGHT).showError();
        } else {
            EventCrud ps = new EventCrud();

            ps.ajouterEvenement(new Event(Nom.getText(), Type.getText(), Lieu.getText(), Integer.parseInt(num.getText()), Pays.getText(), dated.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US)), datef.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US)), Descr.getText(), Integer.parseInt(nbr.getText()), Float.parseFloat(Frais.getText()), Id_user, content));
            initChamps();
            Notifications.create()
                    .title("Amiticia")
                    .text("Evenement ajoute !!").darkStyle().hideAfter(Duration.seconds(5)).position(Pos.BOTTOM_RIGHT)
                    .showInformation();

        }
    }

    public void initChamps() {
        Nom.clear();
        Type.clear();
        Lieu.clear();
        Pays.clear();
        Descr.clear();
        Frais.clear();
        num.clear();
        nbr.clear();
        dated.getEditor().clear();
        datef.getEditor().clear();
        photo.setImage(null);
       
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

    public void copyFile() throws IOException {
        File srcd = Paths.get(content).toFile();
        File sd = Paths.get("C:/Users/ASUS/Desktop/DB/WordFriendship_InDev_3A3-master/src/Assets/").toFile();

        //copy source to target using Files Class
   FileUtils.copyFileToDirectory(srcd,sd);

   
    }

}
