package MenuScreen;

import DataLayer.DataController;
import Main.AppController;
import StratEditor.StratEditor;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MenuScreen {
    //FXML defined buttons and panes
    @FXML
    private Button bindButton;
    @FXML
    private Button splitButton;
    @FXML
    private Button havenButton;
    @FXML
    private Button quitButton;

    //non-FXML fields
    private AppController appController;

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
    private void quit(){
        appController.getStage().close();
    }

    /*
    GETTERS AND SETTERS
     */

    public AppController getAppController() {
        return appController;
    }

    public void setAppController(AppController appController) {
        this.appController = appController;
    }
}
