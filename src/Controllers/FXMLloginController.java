/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Sessions;
import Services.EmailSend;
import Services.UserCrud;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import Entities.User;
import com.jfoenix.controls.JFXPasswordField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.scene.control.PasswordField;
import org.controlsfx.control.Notifications;



/**
 * FXML Controller class
 *
 * @author Jamila
 */
public class FXMLloginController implements Initializable {

    @FXML
    private Button cnxx;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXPasswordField password;
    //private JFXPasswordField password;
    @FXML
    private Button inscription;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cnxx.setOnAction((ActionEvent event) -> {
            try {
                connexion(event);
              //  Notifications.create().title("aa").showInformation();
            } catch (IOException | SQLException ex) {
                Logger.getLogger(FXMLloginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        inscription.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    inscription.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/FXMLajouterUser.fxml")));

                } catch (IOException ex) {
                    Logger.getLogger(FXMLajouterUserController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

    }

    // @FXML	
    /*private void VersPageInscription(ActionEvent event) throws IOException {
	
    Stage stage;

    Parent page2 = FXMLLoader.load(getClass().getResource("../GUI/Inscription.fxml"));
    Scene scene = new Scene(page2);
    stage = (Stage) inscrire.getScene().getWindow();
    stage.hide();
    stage.setScene(scene);
    stage.show();
  }*/
    @FXML

    private void connexion(ActionEvent event) throws IOException, SQLException {
        int c = -1;
        String confirm_token;
        int roles = -1;
        int id = -1;
        UserCrud us = new UserCrud();
        User u = new User();

        //verif email and pssword
        String e, p;

        e = email.getText();
        p = password.getText();
        //System.out.println(e); PasswordField
    
        if (UserCrud.CheckUserEmail(e)) { //verif email
            System.out.println("email c bn");
            u.setEmail(email.getText());

            if (UserCrud.CheckUserPassword(e, p)) { //verif password de l'email
                System.out.println("password c bn");
                u.setPassword(password.getText());

                confirm_token = UserCrud.getConfirmation_token(e); //verif si oui ou nn 
                System.out.println("le ct est " + confirm_token);

                if (confirm_token.equals("oui")) {
                    

                    roles = us.VerificationUtilisateur(u);
                    System.out.println("role" + roles);
                    Stage stage;

                    /*Connection : Admin*/
                    if (roles == 0) {

                        // Session.start(u.getId());
                        System.out.println("user id is : " + u.getId());

                        Sessions.start(u.getId());
                        System.out.println(Sessions.getCurrentSession());
                        us.loggin(u, Sessions.getCurrentSession());
                        System.out.println("Role from login! : admin");

                        Parent page2 = FXMLLoader.load(getClass().getResource("../Views/FXMLadminUser.fxml"));
                        Scene scene = new Scene(page2);
                        stage = (Stage) email.getScene().getWindow();
                        stage.setScene(scene);
                        stage.setUserData(u);
                        stage.show();

                    }

                    /*Connection : Abonnée*/
                    if (roles == 1) {
                        System.out.println("user id is : " + u.getId());

                        Sessions.start(u.getId());
                        System.out.println(Sessions.getCurrentSession());
                        us.loggin(u, Sessions.getCurrentSession());
                        System.out.println("Role from login! : member");

                        Parent page2 = FXMLLoader.load(getClass().getResource("../Views/FXMLmenue.fxml"));
                        Scene scene = new Scene(page2);
                        stage = (Stage) email.getScene().getWindow();
                        stage.setScene(scene);
                        stage.setUserData(u);
                        stage.show();

                    }

                    if (roles != 0 && roles != 1) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Vous ne pouvez pas vous connecter");
                        alert.setContentText("S'il vous plait verifier vos informations d'identification!");
                        alert.showAndWait();
                    }

                } else {
                    Sessions.start(u.getId());
                    System.out.println("you need verivication");
                    us.loggin(u, Sessions.getCurrentSession());
                    Parent page2 = FXMLLoader.load(getClass().getResource("../Views/FXMLconfirmationCode.fxml"));
                    Scene scene = new Scene(page2);
                    Stage stage = (Stage) email.getScene().getWindow();
                    stage.setScene(scene);
                    stage.setUserData(u);
                    stage.show();
                }

            } else {
                System.out.println("verifier password");
            }

        } else {
            System.out.println("verifier email");

        }

    }

}
