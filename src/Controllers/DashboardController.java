/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class DashboardController implements Initializable {
    
    @FXML
    private Pane MainWindow;
    @FXML
    private Pane SideWindow;
    @FXML
    private VBox Menus;
    @FXML
    private AnchorPane ExperienceWindow;
    
    AnchorPane Experience;
    
//    public void setMainWindowNode(Node node){
//        MainWindow.getChildren().clear();
//        MainWindow.getChildren().add((Node) node);
//        
//    }
//    
//    public void setMenusNode(Node node){
//        Menus.getChildren().clear();
//        Menus.getChildren().add((Node) node);
//    }
//    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void UsersClicked(MouseEvent event) {
    }

    @FXML
    private void ExperienceClicked(MouseEvent event) throws IOException {
        Pane newpane = FXMLLoader.load(getClass().getResource("/views/ExperienceCrud.fxml"));
        MainWindow.getChildren().setAll(newpane);
    }

    @FXML
    private void EventsClicked(MouseEvent event) {
    }

    @FXML
    private void TravelBuddyClicked(MouseEvent event) {
    }

    @FXML
    private void BlogClicked(MouseEvent event) {
    }

    @FXML
    private void LogementsClicked(MouseEvent event) {
    }
    
    private void LoadWindow(String ui){
        
        Parent root = null;
        
        try{
            root = FXMLLoader.load(getClass().getResource(ui+".fxml"));
            
        }catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
