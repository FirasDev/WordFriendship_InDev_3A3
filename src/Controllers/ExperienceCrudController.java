/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Experience;
import Services.ExperienceCrud;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class ExperienceCrudController implements Initializable {

    @FXML
    private AnchorPane ExperienceWindow;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> titre;
    @FXML
    private TableColumn<?, ?> type;
    @FXML
    private TableColumn<?, ?> desc;
    @FXML
    private TableColumn<?, ?> eval;
    @FXML
    private TableColumn<?, ?> pays;
    @FXML
    private TableColumn<?, ?> user;
    @FXML
    private TableColumn<?, ?> dadte;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
//        id.setCellValueFactory(
//            new PropertyValueFactory<>("id_experience"));
//        titre.setCellValueFactory(
//            new PropertyValueFactory<>("Titre_exp"));
//        type.setCellValueFactory(
//            new PropertyValueFactory<>("type_exp"));
//        desc.setCellValueFactory(
//            new PropertyValueFactory<>("desc_exp"));
//        date.setCellValueFactory(
//            new PropertyValueFactory<>("date_exp"));
//        eval.setCellValueFactory(
//            new PropertyValueFactory<>("eval_exp"));
//        pays.setCellValueFactory(
//            new PropertyValueFactory<>("id_pays"));
//        user.setCellValueFactory(
//            new PropertyValueFactory<>("id_user"));
//        ObservableList<Experience> OL = FXCollections.observableList(ExperienceCrud.DisplayExperiences());
//		System.out.println(OL);
//		table.setItems(OL);
        
    }    

    
    @FXML
    private void Experienceinfo(MouseEvent event) throws IOException {
        
        AnchorPane AdviceWindow = FXMLLoader.load(getClass().getResource("/views/AddExperience.fxml"));
        ExperienceWindow.getChildren().setAll(AdviceWindow);
    }

    @FXML
    private void DeleteExperience(MouseEvent event) {
    }

    @FXML
    private void UpdateExperience(MouseEvent event) {
    }
    
}
