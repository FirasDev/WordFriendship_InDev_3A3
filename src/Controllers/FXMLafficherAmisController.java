/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Sessions;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Services.UserCrud;
import Entities.User;
import Services.AdminService;
import Services.AmisCrud;
import Services.invitationAmis;
import Utils.MyDBcon;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jamila
 */
public class FXMLafficherAmisController implements Initializable {

    @FXML
    private TableColumn<User, String> firstname;
    @FXML
    private TableColumn<User, String> lastname;
    @FXML
    private TableColumn<User, Integer> idd;
    @FXML
    private TableView<User> tableView;
    @FXML
    private Button profil;
    @FXML
    private Button supprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        UserCrud ps;
        try {
            ps = new UserCrud();

            ArrayList<User> arrayList = (ArrayList<User>) ps.getAmisU(Sessions.getCurrentSession());
            System.out.println((ArrayList<User>) ps.getAmisU(Sessions.getCurrentSession()));
            ObservableList obs = FXCollections.observableArrayList(arrayList);
            tableView.setItems(obs);
            firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
            lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
            idd.setCellValueFactory(new PropertyValueFactory<>("id"));
        } catch (SQLException ex) {
            Logger.getLogger(FXMLafficherAmisController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tableView.setRowFactory(tv -> {
            TableRow<User> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {

                    try {
                        int myIndex = tableView.getSelectionModel().getSelectedIndex();
                        User clickedRow = tableView.getItems().get(myIndex);

                        printRow(clickedRow);
                        System.out.println(clickedRow);
                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLafficherAmisController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });
            return row;
        });

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

    }

    private void printRow(User item) throws SQLException {

        Connection cnx;
        cnx = MyDBcon.getInstance().getCon();

        supprimer.setOnAction(new EventHandler<ActionEvent>() {
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
                        ac.supprimer(item.getId(), Sessions.getCurrentSession());
                        display();
                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLinvitationAmisController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    System.out.println("User chose Cancel or closed the dialog-box");
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

            //filter = new FilteredList(obs,e->true);
            tableView.setItems(obs);

            firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
            lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));

        } catch (SQLException ex) {
            Logger.getLogger(FXMLinvitationAmisController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
