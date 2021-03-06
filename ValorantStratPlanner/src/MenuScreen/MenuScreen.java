package MenuScreen;

import DataLayer.DataController;
import AppController.AppController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MenuScreen {
    //FXML defined buttons and panes
    @FXML
    private Button bindButton;
    @FXML
    private Button splitButton;
    @FXML
    private Button havenButton;
    @FXML
    private Button ascentButton;
    @FXML
    private Button quitButton;
    @FXML
    private Label label;

    //non-FXML fields
    private AppController appController;

    /**
     * helper method to be called when this screen is initialized to setup various things
     */
    public void setup(){
        label.setText(label.getText().replace("__VERSION__", appController.getVersionString()));
    }

    //FXML defined button handlers
    @FXML
    private void setMapToBind(){
        appController.getStratEditor().setMapImage(DataController.Map.BIND);
        appController.setSceneToStratEditor();
    }

    @FXML
    private void setMapToSplit(){
        appController.getStratEditor().setMapImage(DataController.Map.SPLIT);
        appController.setSceneToStratEditor();
    }

    @FXML
    private void setMapToHaven(){
        appController.getStratEditor().setMapImage(DataController.Map.HAVEN);
        appController.setSceneToStratEditor();
    }

    @FXML
    private void setMapToAscent(){
        appController.getStratEditor().setMapImage(DataController.Map.ASCENT);
        appController.setSceneToStratEditor();
    }

    @FXML
    private void quit(){
        appController.getStage().close();
    }

    /*
    GETTERS AND SETTERS
     */

    public void setAppController(AppController appController) {
        this.appController = appController;
    }
}
