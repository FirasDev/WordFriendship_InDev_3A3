/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Sessions;
import Entities.User;
import Services.UserCrud;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;


import java.util.logging.Level;
import java.util.logging.Logger;

import Utils.MyDBcon;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jamila
 */
public class FXMLafficherUserController implements Initializable {
    
    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField tel;
    @FXML
    private JFXTextField firstname;
    @FXML
    private JFXTextField lastname;
    @FXML
    private JFXTextField nationalite;
    @FXML
    private JFXTextField langues;
    @FXML
    private DatePicker date_naissance;
    
    @FXML
    private JFXTextField descriptions;
     @FXML
    private JFXTextField sexe;
    @FXML
    private JFXTextField grade;
    @FXML
    private Button modifier;
    @FXML
    private Button invit;
    @FXML
    private ImageView imageView;
     
    @FXML
    private Button liste;
    @FXML
    private Button logout;
    @FXML
    private Button menue;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
        
        try{
            Connection cnx;
            cnx = MyDBcon.getInstance().getCon();
            
            //user.id='"+Sessions.getCurrentSession()+"'
            String req2 = "SELECT user.username,user.email,user.firstname,user.lastname,user.nationalite,user.langues,user.photo_p,user.date_naissance,user.photo_p,user.descriptions,user.sexe,user.tel, grade.grade FROM user,grade where user.id='"+Sessions.getCurrentSession()+"' and grade.id_ug=user.id";
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
                     //imageView.setImage(new Image(getClass().getResourceAsStream("photo_p")));
                     descriptions.setText(rs.getString("descriptions"));
                     sexe.setText(rs.getString("sexe"));
                     tel.setText(String.valueOf(rs.getInt("tel"))); 
                     grade.setText(rs.getString("grade"));
                      
                     getImg(email.getText());
                 }
        }
        
        catch(SQLException ex){
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLafficherUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
         /* try {
                    OutputStream os= new FileOutputStream(new File(photo_p.getText()));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FXMLafficherUserController.class.getName()).log(Level.SEVERE, null, ex);
                }
                       image= new Image("file:photo_p.getText()",imageView.getFitWidth(),imageView.getFitHeight(),true,true);
                        imageView.setImage(image);  */
        /* file = new File(photo_p.getText());
image = new Image(file.toURI().toString());
imageView.setImage(image); */
        /*image  = new Image(getClass().getResourceAsStream(photo_p.getText()));
        imageView.setImage(image);*/
         /* try { 

    FileInputStream input = new FileInputStream(photo_p.getText()); 
     image = new Image(input); 
      imageView = new ImageView(image); 
     } catch (FileNotFoundException|NullPointerException | java.lang.IllegalArgumentException nupo) { 
      System.err.println(photo_p.getText() + " not found"); 
     } */
         
      /*  logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
        try {
                    logout.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/FXMLmodifierUser.fxml")));
                    
                } catch (IOException ex) {
                    Logger.getLogger(FXMLajouterUserController.class.getName()).log(Level.SEVERE, null, ex);
                }

        
           }
        });*/
        
        modifier.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
        try {
                    modifier.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/FXMLmodifierUser.fxml")));
                    
                } catch (IOException ex) {
                    Logger.getLogger(FXMLajouterUserController.class.getName()).log(Level.SEVERE, null, ex);
                }

        
           }
        });
        
        invit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
        try {
                    invit.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/FXMLinvitationAmis.fxml")));
                    
                } catch (IOException ex) {
                    Logger.getLogger(FXMLajouterUserController.class.getName()).log(Level.SEVERE, null, ex);
                }

        
           }
        });
        
       
        liste.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
        try {
                    liste.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/FXMLafficherAmis.fxml")));
                    
                } catch (IOException ex) {
                    Logger.getLogger(FXMLajouterUserController.class.getName()).log(Level.SEVERE, null, ex);
                }

        
           }
        }

        );
        
        
        
        menue.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
        try {
                    menue.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/FXMLmenue.fxml")));
                    
                } catch (IOException ex) {
                    Logger.getLogger(FXMLajouterUserController.class.getName()).log(Level.SEVERE, null, ex);
                }

        
           }
        }

        );
     
    }    

  /* @FXML
    private void listeAmis(ActionEvent event) {
         try {
                    modifier.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/FXMLafficherAmis.fxml")));
                } catch (IOException ex) {
                    Logger.getLogger(FXMLajouterUserController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }*/
    
    public void getImg(String idd) throws SQLException, IOException {
        Connection cnx = MyDBcon.getInstance().getCon();

        String q = "SELECT `photo_p` FROM `user` WHERE email='"+idd+"'";

        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(q);

        if (rs.next()) {
           
            String imagePath = "file:/" + rs.getString("photo_p");
            Image im = new Image(imagePath);
            System.out.println(im);
            imageView.setImage(im);
        }
    }
    
}
