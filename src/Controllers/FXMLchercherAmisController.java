/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import Entities.Grade;
import Entities.Grade;
import Entities.Sessions;
import Entities.Sessions;
import Entities.User;
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
import Services.AmisCrud;
import Services.invitationAmis;
import java.io.IOException;
import static java.nio.file.Files.list;
import java.nio.file.Paths;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableRow;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jamila
 */
public class FXMLchercherAmisController implements Initializable {

    @FXML
    private TableView<User> tableView;
    @FXML
    private  TableColumn<User, String> username;
    @FXML
    private TableColumn<User, String> email;
     @FXML
    private TableColumn<User, Integer> id;

    @FXML
    private  TableColumn<User, String> firstname;
    @FXML
    private  TableColumn<User, String> lastname;
    @FXML
    private  TableColumn<User, String> nationalite;
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
                    Logger.getLogger(FXMLchercherAmisController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
     

        tableView.setRowFactory(tv -> {
            TableRow<User> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {

                    try {
                        int myIndex = tableView.getSelectionModel().getSelectedIndex();
                        User clickedRow = tableView.getItems().get(myIndex);
                        
                        FXMLLoader loader =new FXMLLoader();
                        loader.setLocation(getClass().getResource("../Views/FXMLprofilAmis.fxml"));
                        loader.load();
                        FXMLprofilAmisController profil = loader.getController();
                        profil.setText(clickedRow);
                        System.out.println(clickedRow.getId());
                        profil.invit(clickedRow.getId(),Sessions.getCurrentSession());
                        Parent root =loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.showAndWait();
                        
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLchercherAmisController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });
            return row;
        });
        AmisCrud ps;
        try {
            ps = new AmisCrud();

            ArrayList<User> arrayList = (ArrayList<User>) ps.getUsersAmis();
            ObservableList obs = FXCollections.observableArrayList(arrayList);
            filter = new FilteredList(obs, e -> true);
            tableView.setItems(obs);
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            username.setCellValueFactory(new PropertyValueFactory<>("username"));
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
        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate((Predicate<? super User>) (User u) -> {
                if (newValue.isEmpty() || newValue == null) {
                    return true;
                } else if (u.getUsername().contains(newValue)) {
                    return true;
                }
                return false;

            });
        });
        SortedList sort = new SortedList(filter);
        sort.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sort);
    }

    private void logout(ActionEvent event) throws IOException, SQLException {

        UserCrud u = new UserCrud();
        u.Deconnexion(Sessions.getCurrentSession());

    }

//    private static void printRow(User item) {
//        try {
//            Connection cnx;
//            cnx = MyDBcon.getInstance().getCon();
//
//            String req = "SELECT  username, email, firstname, lastname, nationalite, langues, descriptions, sexe FROM user where id='" + item.getId() + "' ";
//
//            PreparedStatement pstm = cnx.prepareStatement(req);
//
//            ResultSet rs = pstm.executeQuery(req);
//
//            while (rs.next()) {
//                item.setUsername(rs.getString(1));
//                item.setEmail(rs.getString(2));
//                item.setFirstname(rs.getString(3));
//                item.setLastname(rs.getString(4));
//                item.setNationalite(rs.getString(5));
//                item.setLangues(rs.getString(6));
//                item.setDescriptions(rs.getString(7));
//                item.setSexe(rs.getString(8));
//
//                username.setText(item.getUsername());
//                // email.setText(item.getEmail());
//                firstname.setText(item.getFirstname());
//                lastname.setText(item.getLastname());
//                nationalite.setText(item.getNationalite());
//                /* DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//                LocalDate d1 = LocalDate.parse(item.getDate_debut(), formatter);
//                dated1.setValue(d1);*/
//
////                langue.setText(item.getLangues());
////                description.setText(item.getDescriptions());
////                sexe.setText(item.getSexe());
//
//            
//            }
//            pstm.close();
//            rs.close();
//
//        } catch (SQLException ex) {
//            Logger.getLogger(FXMLafficheEventController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
}
