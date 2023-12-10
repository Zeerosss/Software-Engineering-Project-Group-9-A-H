package it.unisa.diem.SEGroup9;

import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;

import it.unisa.diem.actions.AbstractActionController;
import it.unisa.diem.actions.Action;
import it.unisa.diem.rules.Rule;
import it.unisa.diem.rules.RuleListToJavaFX;
import it.unisa.diem.triggers.AbstractTriggerController;
import it.unisa.diem.triggers.Trigger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

import javafx.scene.layout.AnchorPane;

public class CreateSetViewController implements Initializable {
    // Getting the RuleService Instance to set the elements into the ObservableList
    private static RuleListToJavaFX ruleCollection = RuleListToJavaFX.getInstance();

    @FXML
    private Button confirmRuleButton;

    private Alert alert;

    private AbstractTriggerController triggerController;

    private AbstractActionController actionController;

    @FXML
    private AnchorPane actionInputPane;

    @FXML
    private AnchorPane creationPage;

    @FXML
    private ListView<Rule> alreadyAdd;

    @FXML
    private ChoiceBox<String> actionBox;

    @FXML
    private AnchorPane triggerInputPane;

    @FXML
    private ChoiceBox<String> triggerBox;

    @FXML
    private TextField ruleNameLabel;

    @FXML
    private RadioButton onlyOnceRadio;

    @FXML
    private RadioButton sleepingTimeRadio;

    @FXML
    private Spinner<Integer> hourSpinner;

    @FXML
    private Spinner<Integer> minuteSpinner;

    @FXML
    private Spinner<Integer> daySpinner;

    @FXML
    private Label daysLabel;

    @FXML
    private Label hoursLabel;

    @FXML
    private Label minutesLabel;

    @FXML
    private void confirmRule() throws IOException {
        String ruleName = ruleNameLabel.getText();
        Duration sleepingTime;

        // Checking if necessary fields are filled
        if (triggerBox.getValue() == null || ruleName.isEmpty() || actionBox.getValue() == null) {
            alert.setTitle("Warning");
            alert.setHeaderText("WARNING!");
            alert.setContentText("Fill all the fields!");
            alert.showAndWait();
        } else {
            Trigger trigger = triggerController.createTrigger();
            Action action = actionController.createAction();

            // Checking if action or trigger objects are null
            if ((action == null) || (trigger == null)) {
                alert.setTitle("Warning");
                alert.setHeaderText("WARNING!");
                alert.setContentText("Error in action or trigger fields");
                alert.showAndWait();
            } else {
                try {
                    // Creating Duration object for sleeping time
                    sleepingTime = Duration.ofDays(daySpinner.getValue()).plusHours(hourSpinner.getValue())
                            .plusMinutes(minuteSpinner.getValue());

                    // Adding the rule to the collection
                    ruleCollection.ruleAdd(true, ruleName, trigger, action, onlyOnceRadio.isSelected(),
                            sleepingTime);

                    // Resetting input fields
                    triggerBox.setValue(null);
                    actionBox.setValue(null);
                    ruleNameLabel.clear();
                    hourSpinner.getValueFactory().setValue(0);
                    daySpinner.getValueFactory().setValue(0);
                    minuteSpinner.getValueFactory().setValue(0);
                    onlyOnceRadio.setSelected(true);
                    sleepingTimeRadio.setSelected(false);
                } catch (Exception e) {
                    System.err.println("Exception in rule creation");
                }
            }
        }
    }

    private AbstractTriggerController getTriggerController(String fxml) {
        // Method to load and return a TriggerController based on the selected trigger type
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml + ".fxml"));

        try {
            Parent root = fxmlLoader.load();
            triggerInputPane.getChildren().add(root);
            return fxmlLoader.getController();
        } catch (IOException e) {
            System.err.println("Error in FXMLLoader");
            return null;
        }
    }

    private AbstractActionController getActionController(String fxml) {
        // Method to load and return an ActionController based on the selected action type
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml + ".fxml"));

        try {
            Parent root = fxmlLoader.load();
            actionInputPane.getChildren().add(root);
            return fxmlLoader.getController();
        } catch (IOException e) {
            System.err.println("Error in FXMLLoader");
            return null;
        }
    }

    @FXML
    private void confirmSet(ActionEvent event) throws IOException {
        // Method to confirm and switch to the rule view scene
        if (alreadyAdd.getItems().isEmpty()) {
            alert.setTitle("Warning");
            alert.setHeaderText("WARNING!");
            alert.setContentText("Enter at least one rule");
            alert.showAndWait();
        } else
            App.setRoot("ruleview");
    }

    @Override
    /**
     * Initializes the SecondaryController. Loads specific triggers' FXML and connects this controller to the controller related to the UI part for specific triggers.
     */
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Initialization method for the SecondaryController
        alert = new Alert(Alert.AlertType.WARNING);

        // Adding available action and trigger types to the choice boxes
        actionBox.getItems().setAll("Play an audio file", "Display a message", "Append a message to a file",
                "Copy File", "Move File", "Delete File", "Execute External Program");
        triggerBox.getItems().setAll("Time of day Trigger", "Day of the week", "Day of the month", "Date",
                "File exists in a directory", "File size exceeds a value", "Exit status of a program");

        // Setting the items in the alreadyAdd ListView
        alreadyAdd.setItems(ruleCollection.getRules());

        // Listener for selecting a trigger type
        triggerBox.getSelectionModel().selectedIndexProperty().addListener((odd, oldValue, newValue) -> {
            triggerInputPane.getChildren().clear();
            if (newValue.intValue() != -1) {
                triggerController = getTriggerController(TypeConstant.TRIGGERTYPES_CONSTANTS.get(newValue.intValue()));
            }
        });

        // Listener for selecting an action type
        actionBox.getSelectionModel().selectedIndexProperty().addListener((odd, oldValue, newValue) -> {
            actionInputPane.getChildren().clear();
            if (newValue.intValue() != -1) {
                actionController = getActionController(TypeConstant.ACTIONTYPES_CONSTANTS.get(newValue.intValue()));
            }
        });

        // Setting default values and bindings for sleeping time inputs
        onlyOnceRadio.setSelected(true);
        hourSpinner.visibleProperty().bind(sleepingTimeRadio.selectedProperty());
        minuteSpinner.visibleProperty().bind(sleepingTimeRadio.selectedProperty());
        daySpinner.visibleProperty().bind(sleepingTimeRadio.selectedProperty());
        hoursLabel.visibleProperty().bind(sleepingTimeRadio.selectedProperty());
        minutesLabel.visibleProperty().bind(sleepingTimeRadio.selectedProperty());
        daysLabel.visibleProperty().bind(sleepingTimeRadio.selectedProperty());
        hourSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23));
        minuteSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
        daySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100000));

        // Listener for resetting sleeping time inputs when switching from sleeping time to only once
        sleepingTimeRadio.selectedProperty().addListener((observable, oldValue, newValue) -> {
            hourSpinner.getValueFactory().setValue(0);
            minuteSpinner.getValueFactory().setValue(0);
            daySpinner.getValueFactory().setValue(0);
        });
    }
}
