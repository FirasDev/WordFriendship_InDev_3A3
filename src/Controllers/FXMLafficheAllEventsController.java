/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Event;
import Services.EventCrud;
import Services.ReservationCrud;

import Entities.Sessions;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FXMLafficheAllEventsController implements Initializable {

    @FXML
    private TableColumn<Event, String> Nom;
    @FXML
    private TableColumn<Event, String> Type;
    @FXML
    private TableColumn<Event, String> Lieu;
    @FXML
    private TableColumn<Event, Integer> NumeroVilla;
    @FXML
    private TableColumn<Event, String> Pays;
    @FXML
    private TableColumn<Event, String> DateDebut;
    @FXML
    private TableColumn<Event, String> DateFin;
    @FXML
    private TableColumn<Event, String> Description;
    @FXML
    private TableColumn<Event, Integer> NombreDePlace;
    @FXML
    private TableColumn<Event, Float> Frais;
    @FXML
    private TableColumn<Event, Integer> idee;

    @FXML
    private JFXTextField rech;

    @FXML
    private TableView<Event> table;

    private static int id = Sessions.getCurrentSession();

    @FXML
    private Button Valider;

    @FXML
    private Button Mesres;
    @FXML
    private Button Ajout;

    @FXML
    private Button top;

    static TextField place = new TextField();
    static TextField prix = new TextField();
    ReservationCrud rc = new ReservationCrud();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Ajout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {

                    Ajout.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/FXMLajoutEvent.fxml")));
                } catch (IOException ex) {
                    Logger.getLogger(FXMLafficheAllEventsController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
        top.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Stage s = new Stage();
                try {
                    s.setScene(StatController.loadStat());
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLafficheAllEventsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                s.showAndWait();

            }

        });
        Mesres.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Mesres.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/FXMLmesReservation.fxml")));

                } catch (IOException ex) {
                    Logger.getLogger(FXMLafficheAllEventsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        afficher();

        table.setRowFactory(tv -> {
            TableRow<Event> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {

                    try {
                        int myIndex = table.getSelectionModel().getSelectedIndex();
                        Event item = table.getItems().get(myIndex);

                        Stage popupwindow = new Stage();

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/FXMLPopUpEvent.fxml"));
                        FXMLPopUpEventController.setEvv(item);
                        Parent root = loader.load();
                        FXMLPopUpEventController c = loader.getController();
                        c.setEvv(item);
                        Scene scene = new Scene(root);
                        popupwindow.setScene(scene);
                        popupwindow.showAndWait();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLafficheAllEventsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            return row;
        });
        Valider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {

                    Valider.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/FXMLafficheEvent.fxml")));

                } catch (IOException ex) {
                    Logger.getLogger(FXMLafficheAllEventsController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
    }

    private static boolean controleDeSaisi() {

        if (place.getText().isEmpty() || prix.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
            return false;
        } else {

            if (!Pattern.matches("[0-9]*", place.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le nombre de place ! ");
                place.requestFocus();
                place.selectEnd();
                return false;
            }

        }
        return true;
    }

    public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }

    public static boolean testNbrPlace(Event e) throws SQLException {
        if (Integer.parseInt(place.getText()) > e.getNbr_place()) {
            Notifications.create().title("Erreur").text("Pas de place disponible").darkStyle().position(Pos.BOTTOM_RIGHT).hideAfter(Duration.seconds(2)).showError();
        } else {
            e.setNbr_place(e.getNbr_place() - Integer.valueOf(place.getText()));

            ReservationCrud.updateNombrePlace(e);

            return true;
        }
        return false;
    }

    public static boolean testPrix(Event e) {
        if (Integer.parseInt(prix.getText()) > e.getFrais()) {
            Notifications.create().title("Erreur").text("Le prix saisie est erroné").darkStyle().position(Pos.BOTTOM_RIGHT).hideAfter(Duration.seconds(2)).showError();
        } else {
            return true;
        }
        return false;
    }

    public void afficher() {

        ArrayList<Event> ev = new ArrayList<>();
        try {
            ev = (ArrayList<Event>) EventCrud.getAllEvent(id);

        } catch (SQLException ex) {
            Logger.getLogger(FXMLafficheEventController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ObservableList<Event> obsl = FXCollections.observableArrayList(ev);

        Nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        Lieu.setCellValueFactory(new PropertyValueFactory<>("Lieu"));
        NumeroVilla.setCellValueFactory(new PropertyValueFactory<>("num_villa"));
        Pays.setCellValueFactory(new PropertyValueFactory<>("Pays"));
        DateDebut.setCellValueFactory(new PropertyValueFactory<>("Date_debut"));
        DateFin.setCellValueFactory(new PropertyValueFactory<>("Date_fin"));
        Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        NombreDePlace.setCellValueFactory(new PropertyValueFactory<>("nbr_place"));
        Frais.setCellValueFactory(new PropertyValueFactory<>("Frais"));
        idee.setCellValueFactory(new PropertyValueFactory<>("Id_event"));

        table.setItems(obsl);

        ////////////////////////Recherche dynamique////////////////////////////////////
        FilteredList<Event> filterData = new FilteredList<Event>(obsl, e -> true);
        rech.setOnKeyReleased(e -> {
            rech.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filterData.setPredicate((Predicate<? super Event>) Event -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (Event.getNom().contains(newValue)) {
                        return true;
                    } else if (Event.getType().toLowerCase().contains(newValue)) {
                        return true;
                    } else if (Float.toString(Event.getFrais()).contains(newValue)) {
                        return true;
                    } else if (Integer.toString(Event.getNbr_place()).contains(newValue)) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Event> sortedData = new SortedList<>(filterData);
            sortedData.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sortedData);

        });

    }

  
}
