/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Sessions;
import Entities.User;
import Services.UserCrud;
import Utils.MyDBcon;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

/**
 * FXML Controller class
 *
 * @author Jamila
 */
public class FXMLmenueController implements Initializable {

    
    @FXML
    private Button profil; 
    @FXML
    private Button chercher_amis;
    
    @FXML
    private Button logout;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        profil.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    profil.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/FXMLafficherUser.fxml")));

                } catch (IOException ex) {
                    Logger.getLogger(FXMLmenueController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        
        chercher_amis.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    chercher_amis.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/FXMLchercherAmis.fxml")));

                } catch (IOException ex) {
                    Logger.getLogger(FXMLmenueController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        
        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    logout(event);
                    logout.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/FXMLmenueout.fxml")));

                } catch (IOException ex) {
                    Logger.getLogger(FXMLajouterUserController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLmenueController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
  
        // TODO
    }    
    
    
        private void logout(ActionEvent event) throws IOException, SQLException {
      
       UserCrud u = new UserCrud();
       u.Deconnexion(Sessions.getCurrentSession());


    }

    
}
