/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import Entities.Grade;
import Entities.Sessions;
import Entities.User;
import Services.GradeCrud;
import Services.UserCrud;
import Utils.MyDBcon;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import Services.AdminService;
import java.io.IOException;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import java.util.ArrayList;
import static java.util.Collections.list;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import java.util.function.Predicate;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
/**
 * FXML Controller class
 *
 * @author Jamila
 */
public class FXMLadminUserController implements Initializable {

    @FXML
    private TableView<User> tableView1;
 
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, String> firstname;
    @FXML
    private TableColumn<User, String> lastname;
    @FXML
    private TableColumn<User, String> nationalite;
    @FXML
    private TableColumn<User, String> langue;
    
    @FXML
    private TableColumn<User, String> description;
    @FXML
    private TableColumn<User, String> sexe;
   
    @FXML
    private JFXTextField recherche;
    @FXML
    private Button logout;
    

    /**
     * Initializes the controller class.
     */
    
   
   
         FilteredList filter;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    logout(event);
                    logout.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/FXMLmenueout.fxml")));

                } catch (IOException ex) {
                    Logger.getLogger(FXMLajouterUserController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLadminUserController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    
        
       /* HashMap<User,Grade> users=new HashMap<>();       
        try {
            AdminService lc = new AdminService();
            users=(HashMap<User,Grade>) lc.afficheProfil();
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLadminUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
         ArrayList<User> user = new ArrayList<>(users.keySet());         
         ArrayList<Grade> g = new ArrayList<>(users.values());  
         ObservableList<User> obs = FXCollections.observableArrayList(user);
         filter = new FilteredList(obs,e->true);
        ObservableList<Grade> obs2 = FXCollections.observableArrayList(g);
        tableView1.setItems(obs);
        
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        roles.setCellValueFactory(new PropertyValueFactory<>("roles"));
        firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        nationalite.setCellValueFactory(new PropertyValueFactory<>("nationalite"));
        langue.setCellValueFactory(new PropertyValueFactory<>("langue"));
        date_naissance.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        
        tableView2.setItems(obs2);
        
       grade.setCellValueFactory(new PropertyValueFactory<>("grade"));*/
       AdminService ps;
        try {
            ps = new  AdminService();
           
       
        ArrayList<User> arrayList = (ArrayList<User>) ps.getUsersAdmin();
        ObservableList obs = FXCollections.observableArrayList(arrayList);
         filter = new FilteredList(obs,e->true);
        tableView1.setItems(obs);
      
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
       
        firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        nationalite.setCellValueFactory(new PropertyValueFactory<>("nationalite"));
        langue.setCellValueFactory(new PropertyValueFactory<>("langues"));
      
        description.setCellValueFactory(new PropertyValueFactory<>("descriptions"));
        sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));   
     } catch (SQLException ex) {
            Logger.getLogger(FXMLafficherAmisController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    

    @FXML
    private void search(KeyEvent event) {
        recherche.textProperty().addListener((observable,oldValue,newValue)->{
            filter.setPredicate((Predicate<? super User>) (User u)->{
            if(newValue.isEmpty() || newValue==null)
            {
                return true;
            }
            else if(u.getEmail().contains(newValue))
            {return true;}
                return false;
            
            
            });
        });
        SortedList sort=new SortedList(filter);
        sort.comparatorProperty().bind(tableView1.comparatorProperty());
        tableView1.setItems(sort);
    }
       
     private void logout(ActionEvent event) throws IOException, SQLException {
      
       UserCrud u = new UserCrud();
       u.Deconnexion(Sessions.getCurrentSession());


    }
   
}
