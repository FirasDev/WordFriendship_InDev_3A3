/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Amis;
import Entities.Grade;
import Entities.Sessions;
import Entities.User;
import Services.ChercherAmis;
import Services.AdminService;
import Services.AmisCrud;
import Services.invitationAmis;
import Utils.MyDBcon;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;

/**
 * FXML Controller class
 *
 * @author Jamila
 */
public class FXMLinvitationAmisController implements Initializable {

    @FXML
    private TableView<User> tableview;
    @FXML
    private TableColumn<User, String> username;
    @FXML
    private TableColumn<User, String> firstname;
    @FXML
    private TableColumn<User, String> lastname;
    @FXML
    private TableColumn<User, Integer> id;
    @FXML
    private Button accepter;
    @FXML
    private Button refuser;
    @FXML
    private Button profil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        profil.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    profil.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/FXMLafficherUser.fxml")));

                } catch (IOException ex) {
                    Logger.getLogger(FXMLajouterUserController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        display();
        // TODO
        tableview.setRowFactory(tv -> {
            TableRow<User> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {

                    int myIndex = tableview.getSelectionModel().getSelectedIndex();
                    User clickedRow = tableview.getItems().get(myIndex);

                    try {
                        printRow(clickedRow);

                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLinvitationAmisController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });
            return row;
        });

    }

    private void printRow(User item) throws SQLException {

        Connection cnx;
        cnx = MyDBcon.getInstance().getCon();

        refuser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);
                dialogC.setTitle("A confirmation dialog-box");
                dialogC.setHeaderText(null);
                dialogC.setContentText("Voulez vous vraiment Supprimer cet evenement ?");
                Optional<ButtonType> answer = dialogC.showAndWait();
                if (answer.get() == ButtonType.OK) {
                    try {
                        AmisCrud ac = new AmisCrud();
                        ac.supprimerAmis(item.getId(), Sessions.getCurrentSession());
                        display();
                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLinvitationAmisController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    System.out.println("User chose Cancel or closed the dialog-box");
                }

            }
        });

        accepter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    AmisCrud ac = new AmisCrud();
                    ac.AccepterInvit(item.getId());
                    display();
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLinvitationAmisController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        
    }

    private void display() {
        try {

            try {
                AdminService lc = new AdminService();

            } catch (SQLException ex) {
                Logger.getLogger(FXMLadminUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ArrayList<User> user = invitationAmis.afficherProfil();

            ObservableList<User> obs = FXCollections.observableArrayList(user);

            //  filter = new FilteredList(obs,e->true);
            tableview.setItems(obs);

            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            username.setCellValueFactory(new PropertyValueFactory<>("username"));
            firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
            lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));

        } catch (SQLException ex) {
            Logger.getLogger(FXMLinvitationAmisController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
