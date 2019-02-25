/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.apache.http.util.ByteArrayBuffer;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class ViewExperienceController implements Initializable {

    @FXML
    private JFXButton back;
    @FXML
    private JFXButton home;
    @FXML
    private JFXButton album;
    @FXML
    private Label titre;
    @FXML
    private Label pays;
    @FXML
    private Label type;
    @FXML
    private Label date;
    @FXML
    private ImageView image;
    @FXML
    private Label username;
    @FXML
    private JFXTextArea desc;
    @FXML
    private AnchorPane viewexperiencewindow;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        titre.setText("Workshop Esprit");
        type.setText("Education");
        pays.setText("Tunisie");
        date.setText("01/01/2019");
        username.setText("Firas");
        
//            Image image1 = new Image(new File("D:\\Programs\\xampp\\htdocs\\Wordfriendship\\assets\\experiences\\pic1.jpg").toURI().toString());
//            image.setImage(image1);
 
          Image image1 = new Image("file://127.0.0.1/wordfriendship/assets/experiences/pic1.jpg");
            image.setImage(image1);

        desc.setText("A game jam is a hackathon for video games. It is a gathering of people for the purpose of planning, designing, and creating one or more games within a short span of time, usually ranging between 24 and 72 hours, and some even longer. Participants are generally made up of programmers, game designers, artists, writers, and others in game development-related fields.");
        desc.setEditable(false);
        
    }    

    @FXML
    private void CountryInfo(MouseEvent event) {
        //load country window
    }
    
}
