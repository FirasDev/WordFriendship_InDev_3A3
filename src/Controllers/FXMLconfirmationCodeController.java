/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.FXMLajouterUserController.showAlert;
import Entities.Grade;
import Entities.Sessions;
import Entities.User;
import Services.UserCrud;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javax.mail.Session;

/**
 * FXML Controller class
 *
 * @author Jamila
 */
public class FXMLconfirmationCodeController implements Initializable {

    @FXML
    private JFXTextField code;
    @FXML
    private JFXTextField email;
    @FXML
    private Button confirm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String c, e;
                c = code.getText();
                e = email.getText();
                int id = -1;

                try {
                    UserCrud ps = new UserCrud();
                    id = ps.getId_Email(e);
                    System.out.println("recuperation id" + id);
                    
                            
                    if (UserCrud.getCodeconfirmation(id)) {
                        ps.aotorisation(id);
                        System.out.println("ok");
                        confirm.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/FXMLlogin.fxml")));
                    }
                    
                     else { System.out.println("c pas ok");
                         }

                 

                } catch (SQLException ex) {
                    Logger.getLogger(FXMLconfirmationCodeController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(FXMLconfirmationCodeController.class.getName()).log(Level.SEVERE, null, ex);
                }

               
              

            
               

            // User u = new User(code.getInt());
            // ps.ajouterUser(email.getText(),u,g);
            //  EmailSend em = new EmailSend();
            //  em.sendmail();
        }

    }

);
    }
    
}
