/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.User;
import Services.UserCrud;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Jamila
 */
public class FXMLdesactivierController implements Initializable {

    @FXML
    private JFXTextField password;
    @FXML
    private JFXButton valider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void desactiver(ActionEvent event) {
         UserCrud ps;
        try {
            ps = new UserCrud();
     
            //User u = new User(63,password.getText());
     ps.desactiverUser(28,password.getText());
                 
                    } catch (SQLException ex) {
            Logger.getLogger(FXMLprofilAmisController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
