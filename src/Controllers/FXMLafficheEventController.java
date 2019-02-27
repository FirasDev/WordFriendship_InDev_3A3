/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Event;
import Services.EventCrud;
import Utils.MyDBcon;
import Entities.Sessions;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FXMLafficheEventController implements Initializable {

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
    private TableColumn<Event, Integer> ide;
    @FXML
    private JFXTextField Nom1;
    @FXML
    private JFXTextField Type1;
    @FXML
    private JFXTextField Lieu1;
    @FXML
    private JFXTextField num1;
    @FXML
    private JFXTextField Pays1;
    @FXML
    private JFXTextField Descr1;
    @FXML
    private JFXTextField Frais1;
    @FXML
    private JFXTextField nbr1;
    @FXML
    private DatePicker dated1;
    @FXML
    private DatePicker datef1;
    @FXML
    private Button Valider;
    
    @FXML
    private JFXTextField ide1;
    
      @FXML
    private JFXButton back;
    @FXML
    private Button Delete;
    private static int id = Sessions.getCurrentSession();
    EventCrud ps = new EventCrud();
    ArrayList<Event> ev = new ArrayList<>();

    /* public static GridPane gridPaneEvent = null;
     EventCrud ss = new EventCrud();
     public static List<Event> p = null;
     @FXML
     private ScrollPane scrollEvent;

     public void addToGrid(List<Event> list) {
     int totalItems = list.size();
     int nbrItems = gridPaneEvent.getChildren().size();
     int nbrRows = (nbrItems % 2 == 0) ? nbrItems / 2 : (nbrItems + 1) / 2;
     List<Parent> parents = new ArrayList<>();
        
     for (int i = 0; i < list.size(); i++) {
     try {
     System.out.println(list.size());
     // EventMain.index = i;
     FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXMLafficheEvent.fxml"));
     Parent root = loader.load();
     parents.add(root);
     } catch (IOException ex) {
     Logger.getLogger(FXMLafficheEventController.class.getName()).log(Level.SEVERE, null, ex);
     }
     }

     if (nbrItems % 2 == 1) {// impaire
     if (parents.size() > 0) {
     gridPaneEvent.add(parents.get(0), 1, nbrRows - 1);
     parents.remove(0);
     }
     }
     //paire 
     for (int ligne = nbrRows; ligne < nbrRows + totalItems; ligne++) {
     for (int col = 0; col < 2; col++) {
     if (parents.size() > 0) {
     gridPaneEvent.add(parents.get(0), col, ligne);
     parents.remove(0);
     } else {
     return;
     }
     }
     }
     }
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*try {
         p = ss.getAllEvents();
        
         } catch (SQLException ex) {
         Logger.getLogger(FXMLafficheEventController.class.getName()).log(Level.SEVERE, null, ex);
         }

         gridPaneEvent = new GridPane();

         addToGrid(p);
         gridPaneEvent.setHgap(25);
         gridPaneEvent.setVgap(25);
         scrollEvent.setPannable(true);
         scrollEvent.setContent(gridPaneEvent);*/
        afficher();
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    back.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/FXMLafficheAllEvents.fxml")));

                } catch (IOException ex) {
                    Logger.getLogger(FXMLafficheEventController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        table.setRowFactory(tv -> {
            TableRow<Event> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {

                    int myIndex = table.getSelectionModel().getSelectedIndex();
                    Event clickedRow = table.getItems().get(myIndex);

                    printRow(clickedRow);
                    

                }
            });
            return row;
        });

    }

    private void printRow(Event item) {
        try {
            Connection cnx;
            cnx = MyDBcon.getInstance().getCon();

            String req = "SELECT  Id_event,`Nom`, `Type`, `Lieu`, `Num_villa`, `Pays`, `Date_debut`, `Date_fin`, `Description`, `nbr_place`, `frais` FROM `evenement` where `Id_event`='" + item.getId() + "' ";

            PreparedStatement pstm = cnx.prepareStatement(req);

            ResultSet rs = pstm.executeQuery(req);

            while (rs.next()) {
                item.setId((rs.getInt(1)));
                item.setNom(rs.getString(2));
                item.setType(rs.getString(3));
                item.setLieu(rs.getString(4));
                item.setNum_villa(rs.getInt(5));
                item.setPays(rs.getString(6));
                item.setDate_debut(rs.getString(7));
                item.setDate_fin(rs.getString(8));
                item.setDescr(rs.getString(9));
                item.setNbr_place(rs.getInt(10));
                item.setFrais(rs.getFloat(11));
                ide1.setText(String.valueOf(item.getId()));
                Nom1.setText(item.getNom());
                Type1.setText(item.getType());
                Lieu1.setText(item.getLieu());
                num1.setText(String.valueOf(item.getNum_villa()));
                Pays1.setText(item.getPays());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate d1 = LocalDate.parse(item.getDate_debut(), formatter);
                dated1.setValue(d1);

                LocalDate d2 = LocalDate.parse(item.getDate_fin(), formatter);
                datef1.setValue(d2);
                Descr1.setText(item.getDescr());
                nbr1.setText(String.valueOf(item.getNbr_place()));
                Frais1.setText(String.valueOf(item.getFrais()));

            }
            pstm.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(FXMLafficheEventController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Valider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (controleDeSaisi()) {
                    if (Nom1.getText().isEmpty()) {
                        Nom1.setText("");
                    }
                    if (Type1.getText().isEmpty()) {
                        Type1.setText("");
                    }
                    if (Lieu1.getText().isEmpty()) {
                        Lieu1.setText("");
                    }
                    if (Pays1.getText().isEmpty()) {
                        Pays1.setText("");
                    }
                    if (Frais1.getText().isEmpty()) {
                        Frais1.setText("");
                    }
                    if (Descr1.getText().isEmpty()) {
                        Descr1.setText("");
                    }
                    if (nbr1.getText().isEmpty()) {
                        nbr1.setText("");
                    }
                    if (num1.getText().isEmpty()) {
                        num1.setText("");
                    }
                }
                testDate();

            }
        });

        Delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (controleDeSaisi()) {
                    if (Nom1.getText().isEmpty()) {
                        Nom1.setText("");
                    }
                    if (Type1.getText().isEmpty()) {
                        Type1.setText("");
                    }
                    if (Lieu1.getText().isEmpty()) {
                        Lieu1.setText("");
                    }
                    if (Pays1.getText().isEmpty()) {
                        Pays1.setText("");
                    }
                    if (Frais1.getText().isEmpty()) {
                        Frais1.setText("");
                    }
                    if (Descr1.getText().isEmpty()) {
                        Descr1.setText("");
                    }
                    if (nbr1.getText().isEmpty()) {
                        nbr1.setText("");
                    }
                    if (num1.getText().isEmpty()) {
                        num1.setText("");
                    }
                }

                Alert dialogC = new Alert(AlertType.CONFIRMATION);
                dialogC.setTitle("A confirmation dialog-box");
                dialogC.setHeaderText(null);
                dialogC.setContentText("Voulez vous vraiment annuler votre reservation ?");
                Optional<ButtonType> answer = dialogC.showAndWait();
                if (answer.get() == ButtonType.OK) {
                    ps.deleteEvent(Integer.parseInt(num1.getText()));
                    initChamps();
                    Notifications.create()
                            .title("Amiticia")
                            .text("Evenement supprimer avec success !!").darkStyle().hideAfter(Duration.seconds(5)).position(Pos.BOTTOM_RIGHT)
                            .showInformation();
                } else {
                    System.out.println("User chose Cancel or closed the dialog-box");
                }
                afficher();

            }
        });
    }

    private boolean controleDeSaisi() {

        if (Nom1.getText().isEmpty() || Type1.getText().isEmpty() || Lieu1.getText().isEmpty()
                || Pays1.getText().isEmpty() || Descr1.getText().isEmpty() || Frais1.getText().isEmpty() || num1.getText().isEmpty() || nbr1.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
            return false;
        } else {

            if (!Pattern.matches("[0-9]*", nbr1.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le libelle du produit ! ");
                nbr1.requestFocus();
                nbr1.selectEnd();
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

    public void afficher() {
        try {
            ev = (ArrayList<Event>) ps.getEvents(id);
            
           

        } catch (SQLException ex) {
            Logger.getLogger(FXMLafficheEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Event> obsl = FXCollections.observableArrayList(ev);

        table.setItems(obsl);
        ide.setCellValueFactory(new PropertyValueFactory<>("Id_event"));
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
        
    }

    public void testDate() {
        int test = dated1.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US)).compareTo(datef1.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US)));

        if (test > 0) {
            Notifications.create().title("Error").text("Veillez saisir une date de fin > a la date de début").darkStyle().position(Pos.BOTTOM_RIGHT).showError();
        } else {
            EventCrud ps = new EventCrud();
            
            ps.updateEvent(Integer.parseInt(ide1.getText()), Nom1.getText(), Type1.getText(), Lieu1.getText(), Integer.parseInt(num1.getText()), Pays1.getText(), dated1.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US)), datef1.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US)), Descr1.getText(), Integer.parseInt(nbr1.getText()), Float.parseFloat(Frais1.getText()));
             afficher();
            initChamps();

            Notifications.create()
                    .title("Amiticia")
                    .text("Evenement modifier avec success !!").darkStyle().hideAfter(Duration.seconds(5)).position(Pos.BOTTOM_RIGHT)
                    .showInformation();

           

            initChamps();

        }
    }

    public void initChamps() {
        Nom1.clear();
        Type1.clear();
        Lieu1.clear();
        Pays1.clear();
        Descr1.clear();
        Frais1.clear();
        num1.clear();
        nbr1.clear();
        dated1.getEditor().clear();
        datef1.getEditor().clear();
    }

}
