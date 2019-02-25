/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Grade;
import Entities.User;
import Services.UserCrud;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;

import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Services.EmailSend;
import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Jamila
 */
public class FXMLajouterUserController implements Initializable {

    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField tel;
    @FXML
    private JFXTextField password;
    @FXML
    private JFXTextField passwordc;
    @FXML
    private JFXTextField firstname;
    @FXML
    private JFXTextField lastname;
    @FXML
    private JFXTextField nationalite;
    @FXML
    private JFXTextField langue;
    @FXML
    private DatePicker date_naissance;
    @FXML
    private JFXTextField photo;  
    @FXML
    private JFXTextField description;
    @FXML
    private JFXTextField sexe;
    @FXML
    private Button ok;
    @FXML
    private JFXButton btnbrowser;
    private FileChooser fileChooser;
    private File file;
    private Stage stage;
    @FXML
    private AnchorPane anchorPane;
    private Desktop deskTop=Desktop.getDesktop();
    @FXML
    private ImageView imageView;
    private Image image;
    @FXML
    private JFXRadioButton male;
    @FXML
    private ToggleGroup mygroup;
    @FXML
    private JFXRadioButton femele;
    @FXML
    private ComboBox<?> combolangue;
  

   // @FXML
   //private ComboBox<String> combolangue;
   //ObservableList<String>liste=FXCollections.observableArrayList("Anglais","Français","Arabe");
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            fileChooser= new FileChooser();
          fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All files","*.*"),
                new FileChooser.ExtensionFilter("images","*.png","*.jpg","*.gif"),
                new FileChooser.ExtensionFilter("Text File","*.txt")
                );
            
        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                UserCrud ps;
                
                
                
               
                //combolangue.setItems(liste);
                //langue.setText(combolangue.getValue());
                try {
                     ps = new UserCrud();
                     
                     User u = new User(username.getText(),email.getText(),password.getText(), firstname.getText(),lastname.getText(),nationalite.getText(),langue.getText(),date_naissance.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US)),  photo.getText(),description.getText(),sexe.getText(),Integer.parseInt(tel.getText())); //date_naissance.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US)),
        Grade g=new Grade("new user");
        
        if (username.getText().isEmpty() || email.getText().isEmpty() || password.getText().isEmpty()||firstname.getText().isEmpty()||lastname.getText().isEmpty()||nationalite.getText().isEmpty()||langue.getText().isEmpty()||description.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
           
        }
        else if(!Pattern.matches("^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$", email.getText()))
                {
                    showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Mail invalid !");
                }
       
       else if(!password.getText().equals(passwordc.getText()))
                {
                    showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "les deux mot de passe ne sont pas identique !");
                }
        else if (!Pattern.matches("^[\\p{L} .'-]+$", username.getText())||!Pattern.matches("^[a-zA-Z0-9]*$", password.getText())||!Pattern.matches("^[a-zA-Z0-9]*$", firstname.getText())||!Pattern.matches("^[a-zA-Z0-9]*$", lastname.getText())||!Pattern.matches("^[a-zA-Z0-9]*$", nationalite.getText())||!Pattern.matches("^[a-zA-Z0-9]*$", langue.getText())||!Pattern.matches("^[a-zA-Z0-9]*$", description.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "champ doit etre alphabetique ! ");
                
               
            }
         else {
            
            
        
         ps.ajouterUser(email.getText(),u,g);
         
         int code = ps.getCode_confir(email.getText());
         System.out.println("le code de mail est" + code);
         EmailSend em = new EmailSend();
         em.sendmail(email.getText(),code);
         Notifications.create()
                                .title("Amiticia")
                                .text("un nouveau membre s'est inscrit !").darkStyle().hideAfter(Duration.seconds(5)).position(Pos.BOTTOM_RIGHT)
                                .showInformation();

       
       
        }
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLajouterUserController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                 try {
                    ok.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/FXMLajouterUser.fxml")));
                    
                } catch (IOException ex) {
                    Logger.getLogger(FXMLajouterUserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

              
        });
                
                
    }
    
    

    @FXML
    private void handleBrowser(ActionEvent event) {
        stage=(Stage) anchorPane.getScene().getWindow();
        file=fileChooser.showOpenDialog(stage);
        /*try {
            deskTop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(FXMLajouterUserController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        if(file != null)
        {
            System.out.println(""+file.getAbsolutePath());
            image= new Image(file.getAbsoluteFile().toURI().toString(),imageView.getFitWidth(),imageView.getFitHeight(),true,true);
            imageView.setImage(image);
            imageView.setPreserveRatio(true);
            photo.setText(file.getAbsolutePath());
            
        }
    }
          public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }

    @FXML
    private void maleselected(ActionEvent event) {
        male=new JFXRadioButton("male");
               male.setToggleGroup(mygroup);
            
               sexe.setText(male.getText());
               
    }

    @FXML
    private void femeleselected(ActionEvent event) {
         femele=new JFXRadioButton("femele");
               femele.setToggleGroup(mygroup);
               
               sexe.setText(femele.getText());
    }
    
    
    
    
   
}
        
        
 
     
        
        
        
        
        
        
      
    

