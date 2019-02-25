/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Experience;
import Services.ExperienceCrud;
import com.cloudinary.*;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.teknikindustries.bulksms.SMS;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;



/**
 * FXML Controller class
 *
 * @author Firas
 */
public class AddExperience2Controller implements Initializable {

    @FXML
    private AnchorPane AddExperienceWindow;

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
    
        @FXML
    private JFXButton submit;
        
        Cloudinary cloudinary;
        private File image1; //experience picture

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
//        cloudinary = new Cloudinary("cloudinary://999899397915145:wlM4YXAvjuZyuXYarY2LMnFUdxQ@amiticia");
          
        submit.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                
                
               
                try {
//                    Map uploadResult = cloudinary.uploader().upload(image, ObjectUtils.emptyMap()); //experience picture
                      java.sql.Date date = java.sql.Date.valueOf(dates.getValue());
//                    String type_exp = type.getSelectionModel().getSelectedItem().toString();

//(String) uploadResult.get("url")

                    Experience exp = new Experience(titre.getText(),type.getText(),desc.getText(),date,0f,217,"fakeimage",2) ;
                    ExperienceCrud.InsertExperience(exp);
                    Notifications.create()
              .title("Amiticia")
              .text("Experience ajouté avec succés!").darkStyle().hideAfter(Duration.seconds(5)).position(Pos.BOTTOM_RIGHT)
              .showInformation();
                    
                    //get current_user_phone_number
                    
                    
//                       SMS ss=new SMS();
//                    ss.SendSMS("Firas111", "19201995", "Experience ajouté avec succés!", "21654577109", "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
                 
                    RedirectToList();
                } catch (IOException | SQLException ex1) {
                    Logger.getLogger(AddExperience2Controller.class.getName()).log(Level.SEVERE, null, ex1);
                }
                    
                }
            
    });}    

    public void RedirectToList() throws IOException{
                    Pane newpane = FXMLLoader.load(getClass().getResource("/views/ExperienceCrud.fxml"));
                    AddExperienceWindow.getChildren().setAll(newpane);
    }
   

    @FXML
    private void UploadImage(MouseEvent event) {
        
        FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choisir une photo");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
		image1 = fileChooser.showOpenDialog(null);
                
		image.setText(image1.getPath()); 
    }


}
