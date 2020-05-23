package MenuScreen;

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

    private void maximizeWindow(){
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        Stage stage = AppController.getInstance().getStage();

        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
    }

    //FXML defined button handlers
    @FXML
    private void setMapToBind(){
        appController.getStratEditor().setMapImage(StratEditor.Map.BIND);
        appController.getStage().setScene(appController.getStratEditorScene());
        maximizeWindow();
    }

    @FXML
    private void setMapToSplit(){
        appController.getStratEditor().setMapImage(StratEditor.Map.SPLIT);
        appController.getStage().setScene(appController.getStratEditorScene());
        maximizeWindow();
    }

    @FXML
    private void setMapToHaven(){
        appController.getStratEditor().setMapImage(StratEditor.Map.HAVEN);
        appController.getStage().setScene(appController.getStratEditorScene());
        maximizeWindow();
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
