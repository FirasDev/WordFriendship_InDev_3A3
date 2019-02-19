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
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import jdk.nashorn.internal.objects.NativeArray;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class ExperienceCrudController implements Initializable {

    @FXML
    private AnchorPane ExperienceWindow;
    @FXML
    private TableColumn<Experience, Integer> id;
    @FXML
    private TableColumn<Experience, String> Titre_exp;
    @FXML
    private TableColumn<Experience, String> type;
    @FXML
    private TableColumn<Experience, String> desc;
    @FXML
    private TableColumn<Experience, Float> eval;
    @FXML
    private TableColumn<Experience, String> pays;
    @FXML
    private TableColumn<Experience, Date> date;
    @FXML
    private TableView<Experience> table;
    private List<Integer> id_list;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Experience> experiences = new ArrayList<>();
        try {
            experiences=(ArrayList<Experience>) ExperienceCrud.DisplayExperiences();
            Iterator<Experience> it = experiences.iterator();
            System.out.println(it.next());
        //  while(it.hasNext()){
                
           // id_list = ExperienceCrud.DisplayExperiences().stream().map(s -> s.getId_experience()).collect(Collectors.toList());
            
            System.out.println(experiences);
            id_list = experiences.stream().map(s->s.getId_experience()).collect(Collectors.toList());

            
        } catch (SQLException ex) {
            Logger.getLogger(ExperienceCrudController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Experience> ol = FXCollections.observableArrayList(experiences);
        table.setItems(ol);
        Titre_exp.setCellValueFactory(new PropertyValueFactory<>("Titre_exp"));
       
        type.setCellValueFactory(new PropertyValueFactory<>("type_exp"));
        desc.setCellValueFactory(new PropertyValueFactory<>("desc_exp"));
        date.setCellValueFactory(new PropertyValueFactory<>("date_exp"));
        eval.setCellValueFactory(new PropertyValueFactory<>("eval_exp"));
        
    }    

    
    @FXML
    private void Experienceinfo(MouseEvent event) throws IOException {
        
        AnchorPane AdviceWindow = FXMLLoader.load(getClass().getResource("/views/AddExperience.fxml"));
        ExperienceWindow.getChildren().setAll(AdviceWindow);
    }

    @FXML
    private void DeleteExperience(MouseEvent event) {
        System.out.println("aaaaaa");
        
                
                table.setRowFactory(tv -> {
            TableRow<Experience> row = new TableRow<>();
            row.setOnMouseClicked(eventt -> {
                if (!row.isEmpty() && eventt.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 1) {
                    
                    int myIndex = table.getSelectionModel().getSelectedIndex();
                    
//                    Experience clickedRow = table.getItems().get(myIndex);

                    
                    
                    
                }
            });
            return row; 
        });
                
                
    }

    @FXML
    private void UpdateExperience(MouseEvent event) {
    }
    
}
