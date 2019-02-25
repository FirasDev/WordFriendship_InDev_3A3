/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class UpdateExperienceController implements Initializable {

    @FXML
    private JFXTextField titre;
    @FXML
    private JFXTextField type;
    @FXML
    private JFXTextArea desc;
    @FXML
    private DatePicker dates;
    @FXML
    private JFXComboBox<?> pays;
    @FXML
    private JFXTextField image;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    

    @FXML
    private void UploadImage(MouseEvent event) {
    }

    @FXML
    private void UpdateExperience(MouseEvent event) {
    }
    
}
