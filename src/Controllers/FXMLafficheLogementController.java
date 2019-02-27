/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Cite;
import Entities.Logement;
import Services.CiteCrud;
import Services.LogementCrud;
import Utils.MyDBcon;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import java.util.regex.Pattern;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import Entities.Sessions;

/**
 *
 * @author DongFeng
 */
public class FXMLafficheLogementController implements Initializable {

    @FXML
    private TableColumn<Logement, Integer> nomlog;
    @FXML
    private TableColumn<Logement, String> desc;
    @FXML
    private TableColumn<Logement, String> prix;
    @FXML
    private TableColumn<Logement, String> nbr_place;
    @FXML
    private TableColumn<Logement, String> id_log1;
    @FXML
    private TableView<Logement> affichelog;
    @FXML
    private Button Valider;
    @FXML
    private Button retour;
    @FXML
    private Button Delete;
    @FXML
    private TextField nom_log;
    @FXML
    private TextArea desc1;
    @FXML
    private TextField nbr_place1;
    @FXML
    private TextField prix1;
    @FXML
    private TextField nom_cite1;
    @FXML
    private TextField numero2;
    @FXML
    private TextField nom_log2;
    @FXML
    private TextField rami;
    @FXML
    private TextField id_log;
    
    private static int id=Sessions.getCurrentSession();
    
    //  private static int id=1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        afficher(); ///affichage table  view 

        /////////////////////////////////////////////////////////////////////
        affichelog.setRowFactory(tv -> {
            TableRow<Logement> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {
                    int myIndex = affichelog.getSelectionModel().getSelectedIndex();
                    Logement clickedRow = affichelog.getItems().get(myIndex);
                    printRow(clickedRow);
                }
            });
            return row;
        }); 
        
      //////// appeeeeeel bouttton retouuur   
       
        retour.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {                
        try {
             retour.getScene().setRoot(FXMLLoader.load(getClass().getResource("../Views/Dashboard.fxml")));
            
        }       catch (IOException ex) {
                    Logger.getLogger(FXMLaffichetousController.class.getName()).log(Level.SEVERE, null, ex);
                }       
    }       
        }); 
        
    }/// fiiiiiin initiaaliizeeeeeeee 
    
    

    private void printRow(Logement item) {
        try {
            Connection cnx; ///recuperaatioon des chaampss clikéééé et liaaison avec BD
            cnx = MyDBcon.getInstance().getCon();
            String req = "SELECT logement.nom, `description`, `prix`, `nbr_place`, logement.id_cite ,cite.nom, `num_villa` ,logement.id  FROM `logement` inner join cite where logement.id='" + item.getId() + "' and logement.id_cite=cite.id_cite ";
            PreparedStatement pstm = cnx.prepareStatement(req);
            ResultSet rs = pstm.executeQuery(req);
            while (rs.next()) {
                item.setNom(rs.getString(1));
                nom_log.setText(item.getNom());
                nom_log2.setText(item.getNom());
                System.out.println(rs.getString(7));
                desc1.setText(rs.getString(2));
                nbr_place1.setText(String.valueOf(rs.getInt(4)));
                prix1.setText(String.valueOf(rs.getInt(3)));
                nom_cite1.setText(rs.getString(6));
                numero2.setText(String.valueOf(rs.getInt(7)));
                rami.setText(String.valueOf(rs.getInt(5)));
                id_log.setText(String.valueOf(rs.getInt(8)));
            }
            pstm.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLafficheLogementController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Valider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
         if (controleDeSaisi()) {  ////////appeeel pour le controle saisie 
              
            if (nom_log.getText().isEmpty()) {
                nom_log.setText("");
            }
            if (desc1.getText().isEmpty()) {
                desc1.setText("");
            }
            if (nbr_place1.getText().isEmpty()) {
                nbr_place1.setText("");
            }
            if (prix1.getText().isEmpty()) {
                prix1.setText("");
            }
            if (nom_cite1.getText().isEmpty()) {
                nom_cite1.setText("");
            }
            if (numero2.getText().isEmpty()) {
                numero2.setText("");
            }             
               }               
                LogementCrud ps;
                CiteCrud ct;
                try {   ///appel methode updateeeee
                    ps = new LogementCrud(); 
                    ct = new CiteCrud();
                    ps.Updatelog(nom_log.getText(), desc1.getText(), Integer.parseInt(prix1.getText()), Integer.parseInt(nbr_place1.getText()), nom_log2.getText(),nom_cite1.getText(),Integer.parseInt(numero2   .getText()));
                     ct.Modifier_cite(Integer.parseInt(numero2.getText()),nom_cite1.getText(), Integer.parseInt(rami.getText()));
                   Notifications.create().darkStyle().title("Amiticia").text("Modification Effectue").position(Pos.BOTTOM_RIGHT).hideAfter(Duration.seconds(3)).showInformation();
                    nom_log.clear();
                    desc1.clear();
                    prix1.clear();
                    nbr_place1.clear();
                    nom_cite1.clear();
                    numero2.clear();
                    afficher();
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLafficheLogementController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
        Delete.setOnAction(new EventHandler<ActionEvent>() {      ///////Deleeteee aveeec pop up de confirmation
         @Override
         public void handle(ActionEvent event) {               
         CiteCrud cc;
         Alert dialogC = new Alert(AlertType.CONFIRMATION);
         dialogC.setTitle("A confirmation dialog-box"); 
         dialogC.setHeaderText(null);
         dialogC.setContentText("Voulez vous vraiment Supprimer ce logement ?");
         Optional<ButtonType> answer = dialogC.showAndWait();
         
         if (answer.get() == ButtonType.OK) {
             System.out.println("User chose OK");
             LogementCrud.Supprimer_log(Integer.parseInt(id_log.getText()));
             Notifications.create().darkStyle().title("Amiticia").text("Logement Supprimer avec Succes").position(Pos.BOTTOM_RIGHT).hideAfter(Duration.seconds(3)).showInformation();
             nom_log.clear();
             desc1.clear();
             prix1.clear();
             nbr_place1.clear();
             nom_cite1.clear();
             numero2.clear();
             afficher();
         }
         else {
             System.out.println("User chose Cancel or closed the dialog-box");
         }      
                
         }   //////fin deleteee
         });
    }
       
           ////controle saisieee de tous les champ lappel mtee3ou lfouu9 fou9 lajout 
    private boolean controleDeSaisi() {  

        if (nom_log.getText().isEmpty() || desc1.getText().isEmpty() || nbr_place1.getText().isEmpty()
                || prix1.getText().isEmpty()|| nom_cite1.getText().isEmpty()|| numero2.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
            return false;
        } else {

            if (!Pattern.matches("[0-9]*", prix1.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez la reference ! ");
                prix1.requestFocus();
                prix1.selectEnd();
                return false;
            }

           if (!Pattern.matches("[0-9]*", nbr_place1.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le libelle du produit ! ");
                nbr_place1.requestFocus();
                nbr_place1.selectEnd();
                return false;
            }          
        }
        return true;
    }
    public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type); /////methodee pour le message de validation des champ  (fabrication) 
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();
    }
    
    public void afficher () //fonction afficher la table view pour reduire le code
    {   
    HashMap<Logement, Cite> logements = new HashMap<>();
        try {
            LogementCrud lc = new LogementCrud();
            logements = (HashMap<Logement, Cite>) lc.Afficheuser(id); ///seulement les logements mte3ou
        } catch (SQLException ex) {
            Logger.getLogger(FXMLafficheLogementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Logement> log = new ArrayList<>(logements.keySet());
        ObservableList<Logement> obs = FXCollections.observableArrayList(log);
        affichelog.setItems(obs);
        nomlog.setCellValueFactory(new PropertyValueFactory<>("nom"));
        desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        nbr_place.setCellValueFactory(new PropertyValueFactory<>("nbr_place")); 
        id_log1.setCellValueFactory(new PropertyValueFactory<>("id")); 
    }
    
    

}
