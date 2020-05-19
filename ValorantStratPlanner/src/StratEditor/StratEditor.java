package StratEditor;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;
import Main.AppController;

public class StratEditor {
    //FXML defined buttons and panes
    @FXML
    private GridPane toolSelector;
    @FXML
    private Pane mapViewer;
    @FXML
    GridPane menuButtons;
    @FXML
    Button saveButton;
    @FXML
    Button clearButton;
    @FXML
    Button loadButton;
    @FXML
    BorderPane rootPane;

    //non-FXML fields
    private AppController appController;

    public enum Map {BIND, SPLIT, HAVEN};
    private Map curMap;

    public void setMapImage(Map map){
        this.curMap = map;
        switch (map){
            case BIND:
                setMapViewerImage("Bind.PNG");
                break;
            case SPLIT:
                setMapViewerImage("Split.PNG");
                break;
            case HAVEN:
                setMapViewerImage("Haven.PNG");
                break;
        }
    }

    private void setMapViewerImage(String imageFileName){
        String absoluteURL = getClass().getResource("/" + imageFileName).toString();
        BackgroundImage mapImage = new BackgroundImage(new Image(absoluteURL), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, null);
        mapViewer.setBackground(new Background(mapImage));
    }

    //FXML defined button handlers
    @FXML
    private void saveStrat(){

    }

    @FXML
    private void loadStrat(){

    }

    @FXML
    private void clearStrat(){

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
