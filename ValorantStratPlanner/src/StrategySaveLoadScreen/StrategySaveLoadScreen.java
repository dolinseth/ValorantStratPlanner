package StrategySaveLoadScreen;

import Main.AppController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class StrategySaveLoadScreen {
    //FXML defined buttons and panes
    @FXML
    private Button openButton;
    @FXML
    private Button cancelButton;
    @FXML
    private ChoiceBox<String> stratSelector;
    @FXML
    private TextField stratNameField;
    @FXML
    private Button deleteButton;
    @FXML
    private Label stratNameFieldLabel;

    //non-FXML fields
    public enum State {SAVE, LOAD};
    private State state;
    private AppController appController;
    public Stage stage;

    public void setup(State newState){
        state = newState;
        if(state == State.SAVE){
            stratSelector.setVisible(false);
            stratNameField.setVisible(true);
            deleteButton.setVisible(false);
            stratNameFieldLabel.setVisible(true);
            openButton.setText("Save");
        }
        else if(state == State.LOAD){
            stratSelector.setVisible(true);
            stratNameField.setVisible(false);
            deleteButton.setVisible(true);
            stratNameFieldLabel.setVisible(false);
            openButton.setText("Load");
        }
        List<String> strategies = appController.getData().getStrategies();
        stratSelector.getItems().clear();
        strategies.forEach(s -> {
            stratSelector.getItems().add(s);
        });
    }

    @FXML
    private void openButtonHandler(){
        if(state == State.SAVE){
            appController.getData().saveStrat(appController.getStratEditor().getCurrentStrategy(), stratNameField.getText());
            stage.close();
        }
        else if(state == State.LOAD){
            String stratName = stratSelector.getValue();
            appController.getStratEditor().setCurrentStrategy(appController.getData().getStrat(stratName));
            stage.close();
        }
    }

    @FXML
    private void cancelButtonHandler(){
        stage.close();
    }

    @FXML
    private void deleteButtonHandler(){

    }

    /*
    GETTERS AND SETTERS
     */

    public void setAppController(AppController appController){
        this.appController = appController;
    }
}
