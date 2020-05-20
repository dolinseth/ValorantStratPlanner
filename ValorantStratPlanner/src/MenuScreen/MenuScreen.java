package MenuScreen;

import Main.AppController;
import StratEditor.StratEditor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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
        appController.getStratEditor().setMapImage(StratEditor.Map.BIND);
        appController.getStage().setScene(appController.getStratEditorScene());
    }

    @FXML
    private void setMapToSplit(){
        appController.getStratEditor().setMapImage(StratEditor.Map.SPLIT);
        appController.getStage().setScene(appController.getStratEditorScene());
    }

    @FXML
    private void setMapToHaven(){
        appController.getStratEditor().setMapImage(StratEditor.Map.HAVEN);
        appController.getStage().setScene(appController.getStratEditorScene());
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
