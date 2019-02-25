/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Amis;
import Entities.Sessions;
import Entities.User;
import Services.AmisCrud;
import Services.UserCrud;
import Utils.MyDBcon;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Jamila
 */
public class FXMLprofilAmisController implements Initializable {

    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField firstname;
    @FXML
    private JFXTextField lastname;
    @FXML
    private JFXTextField nationalite;
    @FXML
    private JFXButton envoyer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            Connection cnx;
            cnx = MyDBcon.getInstance().getCon();

            String req2 = "SELECT username,firstname,lastname,nationalite from user where id=2";
            PreparedStatement pstm2 = cnx.prepareStatement(req2);
            ResultSet rs = pstm2.executeQuery(req2);

            while (rs.next()) {

                username.setText(rs.getString("username"));
                firstname.setText(rs.getString("firstname"));
                lastname.setText(rs.getString("lastname"));
                nationalite.setText(rs.getString("nationalite"));
            }

        } catch (SQLException ex) {

        }

    }

//    @FXML
//    public void envoyerInvitation(ActionEvent event) {
//        AmisCrud ps;
//        try {
//            ps = new AmisCrud();
//
//            ps.ajouterAmis(a);
//
//        } catch (SQLException ex) {
//            Logger.getLogger(FXMLprofilAmisController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    public void invit(int a,int s)
    {
        try {
            AmisCrud ps=new AmisCrud();
            envoyer.setOnAction(e->ps.ajouterAmis(a,s));
        } catch (SQLException ex) {
            Logger.getLogger(FXMLprofilAmisController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setText(User u) {
        username.setText(u.getUsername());
        firstname.setText(u.getFirstname());
        lastname.setText(u.getLastname());
        nationalite.setText(u.getNationalite());
    }
}
