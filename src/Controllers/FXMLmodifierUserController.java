/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import static Controllers.FXMLajouterUserController.showAlert;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import Entities.Grade;
import Entities.Sessions;
import Entities.User;
import Services.UserCrud;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import Utils.MyDBcon;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;


/**
 * FXML Controller class
 *
 * @author Jamila
 */
public class FXMLmodifierUserController implements Initializable {
@FXML
    private TextField username;
       @FXML
    private TextField email;
       
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField nationalite;
    @FXML
    private TextField langues;
    
    @FXML
    private DatePicker date_naissance;
    
    @FXML
    private TextField photo_p;
    @FXML
    private TextField descriptions;
     @FXML
    private TextField sexe;
    @FXML
    private Button valider;
    @FXML
    private JFXButton desactiver;
    /**
     * Initializes the controller class.
     */
   @Override
    public void initialize(URL url, ResourceBundle rb) {
      try{
            Connection cnx;
            cnx = MyDBcon.getInstance().getCon();
            
            
            String req2 = "SELECT username,email,firstname,lastname,nationalite,langues,date_naissance,photo_p,descriptions,sexe FROM user where id='"+Sessions.getCurrentSession()+"'";
                 PreparedStatement pstm2 = cnx.prepareStatement(req2); 
                 ResultSet rs= pstm2.executeQuery(req2);   
                   
                 while (rs.next() )
                 {     
                     
                     username.setText(rs.getString("username"));
                       email.setText(rs.getString("email"));
                     firstname.setText(rs.getString("firstname"));
                     lastname.setText(rs.getString("lastname"));
                     nationalite.setText(rs.getString("nationalite"));
                     langues.setText(rs.getString("langues"));
                     
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                     LocalDate d1 = LocalDate.parse(rs.getString("date_naissance"), formatter);
                     date_naissance.setValue(d1);
                     
                     
                     photo_p.setText(rs.getString("photo_p"));
                    
                     descriptions.setText(rs.getString("descriptions"));
                     sexe.setText(rs.getString("sexe"));
                    

                 }
                  } catch (SQLException ex) {
                    Logger.getLogger(FXMLajouterUserController.class.getName()).log(Level.SEVERE, null, ex);
                }
        valider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
           
                UserCrud ps;
               
                try {
                     ps = new UserCrud();
                     //User u = new User(username.getText(),email.getText(),password.getText(), firstname.getText(),lastname.getText(),nationalite.getText(),langues.getText(), photo_p.getText(),descriptions.getText());
        //Grade g=new Grade("new user");
         if (username.getText().isEmpty() || email.getText().isEmpty() || firstname.getText().isEmpty()||lastname.getText().isEmpty()||nationalite.getText().isEmpty()||langues.getText().isEmpty()||descriptions.getText().isEmpty()||sexe.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
           
        }
        else if (!Pattern.matches("^[\\p{L} .'-]+$", username.getText())||!Pattern.matches("^[a-zA-Z0-9]*$", firstname.getText())||!Pattern.matches("^[a-zA-Z0-9]*$", lastname.getText())||!Pattern.matches("^[a-zA-Z0-9]*$", nationalite.getText())||!Pattern.matches("^[a-zA-Z0-9]*$", langues.getText())||!Pattern.matches("^[a-zA-Z0-9]*$", descriptions.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "champ doit etre alphabetique ! ");    
               
            }
        
        else if(!Pattern.matches("^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$", email.getText()))
                {
                    showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Mail invalid !");
                }
         else {
       ps.modifierUser(Sessions.getCurrentSession(),username.getText(),email.getText(), firstname.getText(),lastname.getText(),nationalite.getText(),langues.getText(),date_naissance.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US)), photo_p.getText(),descriptions.getText(),sexe.getText());
         }
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLajouterUserController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                 try {
                    valider.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/FXMLafficherUser.fxml")));
                } catch (IOException ex) {
                    Logger.getLogger(FXMLajouterUserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
                
                
    }

    @FXML
    private void desactivercompte(ActionEvent event) {
         try {
                    desactiver.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/FXMLdesactiver.fxml")));
                } catch (IOException ex) {
                    Logger.getLogger(FXMLajouterUserController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
}
        