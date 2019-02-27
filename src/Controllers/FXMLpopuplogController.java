/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Cite;
import Entities.Logement;
import Services.CiteCrud;
import Utils.MyDBcon;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DongFeng
 */
public class FXMLpopuplogController implements Initializable {

    @FXML
    private Label price;
    @FXML
    private Label nomc;
    @FXML
    private Label descr;
    @FXML
    private Label nbr;
    @FXML
    private Label num;
    @FXML
    private Label nomm;
    @FXML
    private Button  ajouter;
    
    @FXML
    private ImageView photo;
    
    private static Logement log=new Logement();

    public static Logement getLog() {
        return log;
    }

    public static void setLog(Logement log) {
        FXMLpopuplogController.log = log;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        try {
            ArrayList<Cite> cit = CiteCrud.Afficher_cite2(log.getId());

            System.out.println(log.getNom());

            nomm.setText(log.getNom());
            price.setText(String.valueOf(log.getPrix()));
            descr.setText(log.getDescription());
            nbr.setText(String.valueOf(log.getNbr_place()));
           
             for (Cite c : cit) {
                
             nomc.setText(c.getNom());
             num.setText(String.valueOf(c.getNum()));
             }
             getImg(log.getId());
        } catch (SQLException ex) {
            Logger.getLogger(FXMLpopuplogController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLpopuplogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ajouter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
             
                try {                   
                    ajouter.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/FXMLpayementlog.fxml")));
                  
                } catch (IOException ex) {
                    Logger.getLogger(FXMLpopuplogController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
    }

    public static void display(Logement item) throws SQLException, IOException {

    }
        public void getImg(int idd) throws SQLException, IOException {
        Connection cnx = MyDBcon.getInstance().getCon();

        String q = "SELECT  `URL` FROM `logement` WHERE id='"+idd+"'";

        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(q);

        if (rs.next()) {

            String imagePath = "file:/" + rs.getString("URL");
            Image image = new Image(imagePath);

            photo.setImage(image);
        }
    }
                       
}
