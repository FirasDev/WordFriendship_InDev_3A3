package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Entities.Experience;
import Services.ExperienceCrud;
import Utils.DateUtil;
import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Date;

/**
 * 
 *
 * @author Firas
 */
public class PersonEditDialogController {

    @FXML
    private TextField titre;
    @FXML
    private TextField type;
    @FXML
    private TextField desc;
    @FXML
    private TextField dates;
    @FXML
    private TextField eval;


    private Stage dialogStage;
    private Experience experience;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     *
     * @param person
     */
    public void setPerson(Experience experience) {
        this.experience = experience;

        titre.setText(experience.getTitre_exp());
        type.setText(experience.getType_exp());
        desc.setText(experience.getDesc_exp());
        dates.setText(DateUtil.format(experience.getDate_exp().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
        eval.setText(Float.toString(experience.getEval_exp()));
        
        dates.setPromptText("dd.mm.yyyy");
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            experience.setTitre_exp(titre.getText());
            experience.setType_exp(type.getText());
            experience.setDesc_exp(desc.getText());
            LocalDate localdate = DateUtil.parse(dates.getText());
            java.util.Date date = java.sql.Date.from(localdate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            java.sql.Date sd = new java.sql.Date(date.getTime());
            experience.setDate_exp(sd);
            experience.setEval_exp(Float.parseFloat(eval.getText()));
            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (titre.getText() == null || titre.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (type.getText() == null || type.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (desc.getText() == null || desc.getText().length() == 0) {
            errorMessage += "No valid street!\n";
        }

        if (eval.getText() == null || eval.getText().length() == 0) {
            errorMessage += "No valid postal code!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Float.parseFloat(eval.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an integer)!\n";
            }
        }

        if (dates.getText() == null || dates.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
        } else {
            if (!DateUtil.validDate(dates.getText())) {
                errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}