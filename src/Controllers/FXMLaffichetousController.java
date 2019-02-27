/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import Services.LogementCrud;
import Entities.Cite;
import Entities.Logement;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DongFeng
 */
public class FXMLaffichetousController implements Initializable {

    @FXML
    private TableView<Logement> affichelog;
    @FXML
    private TableColumn<Logement, Integer> nomlog;
    @FXML
    private TableColumn<Logement, String> desc;
    @FXML
    private TableColumn<Logement, String> prix;
    @FXML
    private TableColumn<Logement, String> nbr_place;
    @FXML
    private TableColumn<Logement, String> dope; ////ejdiiiiiiiid
    @FXML
    private TableColumn<Logement, String> id;
    @FXML
    private Button Valider;
    @FXML
    private TextField recherche;
    @FXML
    private Button ajouter;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        afficher(); //affichaagee des 2 table view aveec recherche dynamiquee 

        Valider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Valider.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/FXMLafficheLogement.fxml")));

                } catch (IOException ex) {
                    Logger.getLogger(FXMLaffichetousController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });

        ///// appel boutton ajouter un logement
        ajouter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    ajouter.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/FXMLajoutLogement.fxml")));

                } catch (IOException ex) {
                    Logger.getLogger(FXMLaffichetousController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        /////////// l5edmaaaa ejdiidaaaa mtaa3 erooow 
        affichelog.setRowFactory(tv -> {
            TableRow<Logement> row = new TableRow<>();
            row.setOnMouseClicked((MouseEvent event) -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {

                    int myIndex = affichelog.getSelectionModel().getSelectedIndex();
                    Logement item = affichelog.getItems().get(myIndex);
                  
                         try {
                        
                        Stage popupwindow = new Stage();

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/FXMLPopUpLog.fxml"));
                        FXMLpopuplogController.setLog(item);                     
                        Parent root = loader.load();  
                        FXMLpopuplogController c = loader.getController();
                        c.setLog(item);
                        Scene scene = new Scene(root);
                        popupwindow.setScene(scene);
                        popupwindow.showAndWait();
                        } catch (IOException ex) {
                        Logger.getLogger(FXMLaffichetousController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                }
            });
            return row;
        });

    }  /////// finn initialiizeeee   

    public void afficher() {
        HashMap<Logement, Cite> logements = new HashMap<>();
        try {
            LogementCrud lc = new LogementCrud();
            logements = (HashMap<Logement, Cite>) lc.Affiche_complet();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLafficheLogementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Logement> log = new ArrayList<>(logements.keySet());
    //    ArrayList<Cite> cit = new ArrayList<>(logements.values());

        ObservableList<Logement> obs = FXCollections.observableArrayList(log);
     //   ObservableList<Cite> obs1 = FXCollections.observableArrayList(cit);

        affichelog.setItems(obs);
    //    affichecite.setItems(obs1);

        nomlog.setCellValueFactory(new PropertyValueFactory<>("nom"));
        desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        nbr_place.setCellValueFactory(new PropertyValueFactory<>("nbr_place"));
        dope.setCellValueFactory(new PropertyValueFactory<>("nomc")); ////////rrrrrrrrrrrr
     //   nomcite.setCellValueFactory(new PropertyValueFactory<>("nom"));
     //   nd.setCellValueFactory(new PropertyValueFactory<>("num_villa"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        ////////////////////////Recheercheeee Dynaamiqueeee////////////////////////////////////
        FilteredList<Logement> filterData = new FilteredList<Logement>(obs, e -> true);
        recherche.setOnKeyReleased(e -> {
            recherche.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filterData.setPredicate((Predicate<? super Logement>) Logement -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (Logement.getNom().contains(newValue)) {
                        return true;
                    } else if (Logement.getDescription().toLowerCase().contains(newValue)) {
                        return true;
                    } else if (Integer.toString(Logement.getPrix()).contains(newValue)) {
                        return true;
                    } else if (Integer.toString(Logement.getNbr_place()).contains(newValue)) {
                        return true;
                    }else if (Logement.getNomc().toLowerCase().contains(newValue)) {
                        return true;
                    }
                    
                    return false;
                });
            });
            SortedList<Logement> sortedData = new SortedList<>(filterData);
            sortedData.comparatorProperty().bind(affichelog.comparatorProperty());
            affichelog.setItems(sortedData);

        });

        ////////////////////////////////fiiin Recheercheeee Dynaamiqqqueee////////////////////////////
    }
   
}
