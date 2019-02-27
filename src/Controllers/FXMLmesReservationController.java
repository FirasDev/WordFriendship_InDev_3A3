package Controllers;

import Entities.Event;
import Services.ReservationCrud;
import Utils.MyDBcon;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class FXMLmesReservationController implements Initializable {

    private static int id = 2;

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
    private TableView<Event> table;
    @FXML
    private Button Annuler;
    @FXML
    private JFXButton Return;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        afficher();
        table.setRowFactory(tv -> {
            TableRow<Event> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {

                    int myIndex = table.getSelectionModel().getSelectedIndex();
                    Event clickedRow = table.getItems().get(myIndex);

                    try {
                        printRow(clickedRow);
                       
                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLmesReservationController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });
            return row;
        });

        Return.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Return.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/FXMLafficheAllEvents.fxml")));

                } catch (IOException ex) {
                    Logger.getLogger(FXMLafficheAllEventsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
    }

    private void printRow(Event item) throws SQLException {

        Connection cnx;
        cnx = MyDBcon.getInstance().getCon();

        Annuler.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);
                dialogC.setTitle("A confirmation dialog-box");
                dialogC.setHeaderText(null);
                dialogC.setContentText("Voulez vous vraiment Supprimer cet evenement ?");
                Optional<ButtonType> answer = dialogC.showAndWait();
                if (answer.get() == ButtonType.OK) {

                    ReservationCrud ps = new ReservationCrud();
                    ps.annulerReservation(item.getId());
                    
                } else {
                    System.out.println("User chose Cancel or closed the dialog-box");
                }
                afficher();

                Notifications.create()
                        .title("Amiticia")
                        .text("Reservation annuler avec success !!").darkStyle().hideAfter(Duration.seconds(5)).position(Pos.BOTTOM_RIGHT)
                        .showInformation();

                afficher();
            }
        });
    }

    public void afficher() {
        ReservationCrud ps = new ReservationCrud();
        ArrayList<Event> ev = new ArrayList<>();
        try {
            ev = (ArrayList<Event>) ps.getAllReserv(id);
            System.out.println(ev);

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

        table.setItems(obsl);

    }
}
