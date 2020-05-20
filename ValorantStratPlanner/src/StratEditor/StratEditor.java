package StratEditor;

import StratElements.*;
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
    @FXML
    private Button mainMenuButton;

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
        //create list to store the buttons in
        ArrayList<Button> toolButtons = new ArrayList<Button>();

        //create line drawing button
        Button line = new Button("Line");
        formatToolButton(line);
        line.setOnAction(e -> lineButtonHandler());
        toolButtons.add(line);

        //create generic smoke button
        Button smoke = new Button("Smoke");
        formatToolButton(smoke);
        smoke.setOnAction(e -> brimSmokeButtonHandler());
        toolButtons.add(smoke);

        //create watch here indicator
        Button watchHere = new Button("Watch angle");
        formatToolButton(watchHere);
        watchHere.setOnAction(e -> watchHereButtonHandler());
        toolButtons.add(watchHere);

        int numColumns = toolSelector.getColumnCount();
        for(int i = 0; i < toolButtons.size(); i++){
            toolSelector.add(toolButtons.get(i), i % numColumns, i / numColumns);
        }
    }

    /*
    TOOL BUTTON HANDLERS
     */

    private void watchHereButtonHandler(){
        TwoPointElementBuilder<WatchHere> eb = new TwoPointElementBuilder<WatchHere>();
        WatchHere wh = new WatchHere();
        canvas.setOnMousePressed(e -> {
            eb.startClick(e);
            canvas.setOnMousePressed(e2 ->{
                eb.endClick(e2);
                eb.formatElement(wh);
                elements.add(wh);
                updateCanvas();
                watchHereButtonHandler();
            });
        });
    }

    private void brimSmokeButtonHandler(){
        OnePointElementBuilder<BrimstoneSmoke> eb = new OnePointElementBuilder<BrimstoneSmoke>();
        BrimstoneSmoke smoke = new BrimstoneSmoke();
        canvas.setOnMousePressed(e -> {
            eb.click(e);
            eb.formatElement(smoke);
            elements.add(smoke);
            updateCanvas();
            brimSmokeButtonHandler();
        });
    }

    private void lineButtonHandler(){
        TwoPointElementBuilder<Line> eb = new TwoPointElementBuilder<Line>();
        Line l = new Line();
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

    private void formatToolButton(Button b){
        b.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        b.setPrefSize(1000, 1000);
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

    @FXML
    private void goToMainMenu(){
        clearStrat();
        appController.getStage().setScene(appController.getMenuScreenScene());
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
