/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Experience;
import Services.ExperienceCrud;
import Services.PaysCrud;
import Services.UserCrud;
import Utils.MyDBcon;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class ExperienceCrudAdminController implements Initializable {

    @FXML
    private JFXButton del;
    @FXML
    private JFXTextField SearchField;
    @FXML
    private TableView<Experience> table;
    private List<Integer> id_list;
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
    private TableColumn<Experience, Date> date;
    @FXML
    private TableColumn<Experience, String> pays;
    @FXML
    private TableColumn<Experience, String> userfield;
    @FXML
    private AnchorPane ExperienceWindow;
    private HashMap<String, Integer> map1;
    private TreeMap<String, Integer> map2;
    private TreeMap<String, Integer> map3;
    private List<String > ExperienceList;

      
      public void display() throws SQLException {
        Titre_exp.setCellValueFactory(new PropertyValueFactory<>("Titre_exp"));
        type.setCellValueFactory(new PropertyValueFactory<>("type_exp"));
        desc.setCellValueFactory(new PropertyValueFactory<>("desc_exp"));
        date.setCellValueFactory(new PropertyValueFactory<>("date_exp"));
        eval.setCellValueFactory(new PropertyValueFactory<>("eval_exp"));
        pays.setCellValueFactory(new PropertyValueFactory<>("name_country"));
        userfield.setCellValueFactory(new PropertyValueFactory<>("username"));
	ObservableList<Experience> OL = FXCollections.observableList(ExperienceCrud.DisplayExperiences());
	table.setItems(OL);
	}

	void update() throws SQLException {
		map1 = ExperienceCrud.GetNameIdMap();
		ExperienceList = ExperienceCrud.GetNamelist() ;
		map2 = PaysCrud.GetNameIdMap();
                map3 = UserCrud.GetNameIdMap();
		//old -- ObservableList<String> teams = FXCollections.observableArrayList(map1.keySet());
		ObservableList<String> teams = FXCollections.observableArrayList(ExperienceList);
		System.out.println(map2);
		ObservableList<String> country = FXCollections.observableArrayList(map2.keySet());
                System.out.println(country);
                ObservableList<String> users = FXCollections.observableArrayList(map3.keySet());
                System.out.println(users);
		Titre_exp.setCellFactory(TextFieldTableCell.<Experience>forTableColumn());
		type.setCellFactory(TextFieldTableCell.<Experience>forTableColumn());
		desc.setCellFactory(TextFieldTableCell.<Experience>forTableColumn());
//		image.setCellFactory(TextFieldTableCell.<Experience>forTableColumn());
		// ------ //
		Titre_exp.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Experience, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Experience, String> t) {
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setTitle("Fenêtre de confirmation");
				alert.setContentText("Êtes-vous sûr de la modification ?");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					if (t.getNewValue().matches(""))
				{
						alert("Result format not valid");
                                            try {
                                                refresh();
                                            } catch (SQLException ex) {
                                                Logger.getLogger(ExperienceCrudController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
				}
					else 
					{
						((Experience) t.getTableView().getItems().get(t.getTablePosition().getRow())).setTitre_exp(t.getNewValue());
                                            try {
                                                ExperienceCrud.update("titre_exp", t.getNewValue(), ((Experience) t.getTableView().getItems().get(t.getTablePosition().getRow())).getId_experience());
                                            } catch (SQLException ex) {
                                                Logger.getLogger(ExperienceCrudController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
					}
				} else {
                                    try {
                                        refresh();
                                    } catch (SQLException ex) {
                                        Logger.getLogger(ExperienceCrudController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
					alert.close();
				}

			}
		});
		type.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Experience, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Experience, String> t) {
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setTitle("Fenêtre de confirmation");
				alert.setContentText("Êtes-vous sûr de la modification ?");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					if (t.getNewValue().matches(""))
				{
						alert("Le champ est vide !");
                                            try {
                                                refresh();
                                            } catch (SQLException ex) {
                                                Logger.getLogger(ExperienceCrudController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
				}
					else 
					{
						((Experience) t.getTableView().getItems().get(t.getTablePosition().getRow())).setType_exp(t.getNewValue());
                                            try {
                                                ExperienceCrud.update("type_exp", t.getNewValue(), ((Experience) t.getTableView().getItems().get(t.getTablePosition().getRow())).getId_experience());
                                            } catch (SQLException ex) {
                                                Logger.getLogger(ExperienceCrudController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
					}
				} else {
                                    try {
                                        refresh();
                                    } catch (SQLException ex) {
                                        Logger.getLogger(ExperienceCrudController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
					alert.close();
				}

			}
		});

		desc.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Experience, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Experience, String> t) {
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setTitle("Fenêtre de confirmation");
				alert.setContentText("Êtes-vous sûr de la modification ?");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					if (t.getNewValue().matches(""))
				{
						alert("Le champ est vide !");
                                            try {
                                                refresh();
                                            } catch (SQLException ex) {
                                                Logger.getLogger(ExperienceCrudController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
				}
					else 
					{
						((Experience) t.getTableView().getItems().get(t.getTablePosition().getRow())).setDesc_exp(t.getNewValue());
                                            try {
                                                ExperienceCrud.update("desc_exp", t.getNewValue(), ((Experience) t.getTableView().getItems().get(t.getTablePosition().getRow())).getId_experience());
                                            } catch (SQLException ex) {
                                                Logger.getLogger(ExperienceCrudController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
					}
				} else {
                                    try {
                                        refresh();
                                    } catch (SQLException ex) {
                                        Logger.getLogger(ExperienceCrudController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
					alert.close();
				}

			}
		});

                pays.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Experience, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Experience, String> t) {
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setTitle("Fenêtre de confirmation");
				alert.setContentText("Êtes-vous sûr de la modification ?");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {

                                    try {
                                        ((Experience) t.getTableView().getItems().get(t.getTablePosition().getRow())).setId_pays(ExperienceCrud.SearchPays(t.getNewValue()));
                                        ExperienceCrud.update("id_pays", map2.get(t.getNewValue()), ((Experience) t.getTableView().getItems().get(t.getTablePosition().getRow())).getId_experience());
                                    } catch (SQLException ex) {
                                        Logger.getLogger(ExperienceCrudController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
				} else {
                                    try {
                                        refresh();
                                    } catch (SQLException ex) {
                                        Logger.getLogger(ExperienceCrudController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
					alert.close();
				}
			}
		});

                
//		image.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Experience, String>>() {
//			@Override
//			public void handle(TableColumn.CellEditEvent<Expperience, String> t) throws SQLException {
//				Alert alert = new Alert(AlertType.CONFIRMATION);
//				alert.setTitle("Confirmation Dialog");
//				alert.setContentText("Are you sure of your edition?");
//
//				Optional<ButtonType> result = alert.showAndWait();
//				if (result.get() == ButtonType.OK) {
//
//					((Experience) t.getTableView().getItems().get(t.getTablePosition().getRow())).setimage(t.getNewValue());
//					ExperienceCrud.update("image", t.getNewValue(), ((Experience) t.getTableView().getItems().get(t.getTablePosition().getRow())).getId());
//				} else {
//					refresh();
//					alert.close();
//				}
//			}
//		});

	}
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table.setEditable(true);
        try {
                    ArrayList<Experience> experiences = new ArrayList<>();
        try {
            experiences=(ArrayList<Experience>) ExperienceCrud.DisplayExperiences();
            Iterator<Experience> it = experiences.iterator();
            System.out.println(it.next());
            
            id_list = ExperienceCrud.DisplayExperiences().stream().map(s -> s.getId_experience()).collect(Collectors.toList());
            
            System.out.println(experiences);
            id_list = experiences.stream().map(s->s.getId_experience()).collect(Collectors.toList());

            
        } catch (SQLException ex) {
            Logger.getLogger(ExperienceCrudController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Experience> ol = FXCollections.observableArrayList(experiences);
        table.setItems(ol);
//        id.setCellValueFactory(new PropertyValueFactory<>("id_experience"));
        Titre_exp.setCellValueFactory(new PropertyValueFactory<>("Titre_exp"));
        type.setCellValueFactory(new PropertyValueFactory<>("type_exp"));
        desc.setCellValueFactory(new PropertyValueFactory<>("desc_exp"));
        date.setCellValueFactory(new PropertyValueFactory<>("date_exp"));
        eval.setCellValueFactory(new PropertyValueFactory<>("eval_exp"));


        
        FilteredList<Experience> filterData = new FilteredList<Experience>(ol, e-> true) ;
         SearchField.setOnKeyReleased(e -> {
             SearchField.textProperty().addListener((observableValue, oldValue,newValue) -> {
                 filterData.setPredicate((Predicate<? super Experience>) Experience ->{
                     if(newValue == null || newValue.isEmpty()){
                         return true ;
                     }
                     String lowerCaseFilter = newValue.toLowerCase() ;
                     if(Experience.getTitre_exp().toLowerCase().contains(newValue)){
                         return true ;
                     }else if(Experience.getDesc_exp().toLowerCase().contains(newValue)){
                         return true ;
                     }else if(Experience.getType_exp().toLowerCase().contains(newValue)){
                         return true ;
                     }else if((Float.toString(Experience.getEval_exp())).contains(newValue)){
                         return true ;
                     }else if(Experience.getDate_exp().toString().contains(newValue)){
                         return true ;
                     }
                     return false ;
                 });
             });
             
         SortedList<Experience> sortedData = new SortedList<>(filterData) ;
         sortedData.comparatorProperty().bind(table.comparatorProperty());
         table.setItems(sortedData);
         
         });
           table.setRowFactory(tv -> {
            TableRow<Experience> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 1) {
                    
                    int myIndex = table.getSelectionModel().getSelectedIndex();
                    Experience clickedRow = table.getItems().get(myIndex);
                    
                    try {
                        printRow(clickedRow);
                    } catch (SQLException ex) {
                        Logger.getLogger(ExperienceCrudController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            });
            return row;
        });
        display();
        update();
        } catch (SQLException ex ) {
            Logger.getLogger(ExperienceCrudController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
       
    public void RedirectToList() throws IOException{
                    Pane newpane = FXMLLoader.load(getClass().getResource("/views/ExperienceCrud.fxml"));
                    ExperienceWindow.getChildren().setAll(newpane);
    }

    
    @FXML
    private void Experienceinfo(MouseEvent event) throws IOException {
        
        AnchorPane AdviceWindow = FXMLLoader.load(getClass().getResource("/views/AddExperience.fxml"));
        ExperienceWindow.getChildren().setAll(AdviceWindow);
    }


    private void printRow(Experience item) throws SQLException {
        
            Connection cnx;
            cnx = MyDBcon.getInstance().getCon();
               
             del.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try { 
                    ExperienceCrud.RemoveExperience(item.getId_experience());
//                    int selectedindex = table.getSelectionModel().getSelectedIndex();
//                    table.getItems().remove(selectedindex);
                    RedirectToList();
                    Notifications.create()
              .title("Amiticia")
              .text("Experience supprimé avec succés!").darkStyle().hideAfter(Duration.seconds(5)).position(Pos.BOTTOM_RIGHT)
              .showInformation();
                } catch (SQLException | IOException ex1) {
                    Logger.getLogger(ExperienceCrudController.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
});
                     }
    
    	private void refresh() throws SQLException {
		display();
	}
        	void alert(String text) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error");
		alert.setContentText(text);

		alert.showAndWait();
	}
    
    @FXML
    private void DeleteExperience(MouseEvent event) {
    }

    @FXML
    private void UpdateExperience(ActionEvent event) {
       
        Experience selectedExperience = table.getSelectionModel().getSelectedItem();
    if (selectedExperience != null) {
        
        if (true) {
            
        }

    } else {
        // Nothing selected.
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Selection");
        alert.setHeaderText("No Person Selected");
        alert.setContentText("Please select a person in the table.");

        alert.showAndWait();
    }
}
     
    
}
