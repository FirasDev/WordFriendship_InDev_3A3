/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class AddExperienceController implements Initializable {

    @FXML
    private AnchorPane AdviceWindow;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Understood(MouseEvent event) throws IOException {
        
        AnchorPane AddExperienceWindow = FXMLLoader.load(getClass().getResource("/Views/AddExperience2.fxml"));
        AdviceWindow.getChildren().setAll(AddExperienceWindow);
    }
    
}
