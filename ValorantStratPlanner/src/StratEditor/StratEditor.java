package StratEditor;

import StratElements.TwoPointElementBuilder;
import StratElements.Line;
import StratElements.StratElement;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.ArrayList;

import Main.AppController;
import javafx.scene.paint.Color;

public class StratEditor {
    //FXML defined buttons and panes
    @FXML
    private GridPane toolSelector;
    @FXML
    private Pane mapViewer;
    @FXML
    private Canvas canvas;
    @FXML
    private GridPane menuButtons;
    @FXML
    private Button saveButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button loadButton;
    @FXML
    private Button undoButton;

    //non-FXML fields
    private AppController appController;
    public enum Map {BIND, SPLIT, HAVEN};
    private Map curMap;
    private ArrayList<StratElement> elements = new ArrayList<StratElement>();

    public void setup(){
        createToolSelectorButtons();
    }

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

    public void createToolSelectorButtons(){
        ArrayList<Button> toolButtons = new ArrayList<Button>();
        Button line = new Button("Line");
        line.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        line.setPrefSize(1000, 1000);
        line.setOnAction(e -> lineButtonHandler());
        toolSelector.add(line, 0, 0);
        toolButtons.add(new Button("Line"));
    }

    private void lineButtonHandler(){
        TwoPointElementBuilder<Line> eb = new TwoPointElementBuilder<Line>();
        Line l = new Line(0,0,0,0, Color.YELLOW);
        canvas.setOnMousePressed(e -> {
            eb.startClick(e);
            canvas.setOnMousePressed(e2 ->{
                eb.endClick(e2);
                eb.formatElement(l);
                elements.add(l);
                updateCanvas();
                lineButtonHandler();
            });
        });
    }

    public void updateCanvas(){
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        elements.stream().forEach(e ->{
            e.draw(canvas.getGraphicsContext2D());
        });
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
        elements.clear();
        updateCanvas();
    }

    @FXML
    private void undo(){
        if(elements.size() != 0) {
            elements.remove(elements.size() - 1);
            updateCanvas();
        }
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
