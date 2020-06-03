package StratEditor;

import DataLayer.DataController;
import ElementDecorators.*;
import StratElements.*;
import StrategySaveLoadScreen.StrategySaveLoadScreen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;

import java.util.ArrayList;

import Main.AppController;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import javax.sound.sampled.LineEvent;

import static StratElements.CharacterAbility.areaDenialRadius;
import static StratElements.CharacterAbility.visionBlockRadius;

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
    @FXML
    private BorderPane mainPane;

    //non-FXML fields
    private AppController appController;
    private DataController.Map curMap;
    private Image mapImage;
    private ArrayList<StratElement> elements = new ArrayList<StratElement>();
    private TwoPointStratElement curElement;
    private ArrayList<Button> toolButtons;
    private double mapImageSize;
    private final Color mapBackgroundColor = Color.web("#141C2F");
    public final double pixelsRunPerSecond = 41.505;
    public final double pixelsWalkedPerSecond = 24.615;

    /**
     * To be called when this scene is initialized to setup scene objects at runtime
     */
    public void setup(){
        createToolSelectorButtons();
        updateCanvas();

        //set background image
        Image bgImage = new Image(getClass().getResource("/BackgroundImages/Blueprint-background.png").toString(), 1920, 1080, true, false);
        BackgroundImage bg = new BackgroundImage(bgImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        if(bg == null){
            System.out.println("bg is null");
        }
        else if (mainPane == null){
            System.out.println("mainpane is null");
        }
        mainPane.setBackground(new Background(bg));
    }

    /**
     * Sets the map image that is displayed on the canvas beneath the strats
     * @param map - a StratEditor.Map typed object describing the map to use
     */
    public void setMapImage(DataController.Map map){
        this.curMap = map;
        mapImage = appController.getData().getMapImage(map);
        switch (map){
            case BIND:
                mapImageSize = 900;
                break;
            case SPLIT:
                mapImageSize = 770;
                break;
            case HAVEN:
                mapImageSize = 794;
                break;
        }
    }

    /**
     * Initializes all the tool selector buttons and sets up their handlers
     */
    public void createToolSelectorButtons(){
        //create list to store the buttons in
        toolButtons = new ArrayList<Button>();

        /*
        ABILITY BUTTON HANDLER INITIALIZATION
         */
        makeAbilityButton(DataController.Ability.SKY_SMOKE, e -> skySmokeButtonHandler());
        makeAbilityButton(DataController.Ability.ORBITAL_STRIKE, e -> orbitalStrikeButtonHandler());
        makeAbilityButton(DataController.Ability.STIM_BEACON, e -> stimBeaconButtonHandler());
        makeAbilityButton(DataController.Ability.INCENDIARY, e -> incendiaryButtonHandler());
        makeAbilityButton(DataController.Ability.PAINT_SHELLS, e -> paintShellsButtonHandler());
        makeAbilityButton(DataController.Ability.SHOWSTOPPER, e -> showstopperButtonHandler());
        makeAbilityButton(DataController.Ability.BOOM_BOT, e -> boomBotButtonHandler());
        makeAbilityButton(DataController.Ability.BLAST_PACK, e -> blastPackButtonHandler());
        makeAbilityButton(DataController.Ability.SPYCAM, e -> spycamButtonHandler());
        makeAbilityButton(DataController.Ability.NEURAL_THEFT, e -> neuralTheftButtonHandler());
        makeAbilityButton(DataController.Ability.TRIPWIRE, e -> tripWireButtonHandler());
        makeAbilityButton(DataController.Ability.CYBER_CAGE, e -> cyberCageButtonHandler());
        makeAbilityButton(DataController.Ability.TAILWIND, e -> tailWindButtonHandler());
        makeAbilityButton(DataController.Ability.BLADESTORM, e -> bladestormButtonHandler());
        makeAbilityButton(DataController.Ability.CLOUDBURST, e -> cloudBurstButtonHandler());
        makeAbilityButton(DataController.Ability.UPDRAFT, e -> updraftButtonHandler());
        makeAbilityButton(DataController.Ability.DARK_COVER, e -> darkCoverButtonHandler());
        makeAbilityButton(DataController.Ability.FROM_THE_SHADOWS, e -> fromTheShadowsButtonHandler());
        makeAbilityButton(DataController.Ability.SHROUDED_STEP, e -> shroudedStepButtonHandler());
        makeAbilityButton(DataController.Ability.PARANOIA, e -> paranoiaButtonHandler());
        makeAbilityButton(DataController.Ability.FAULT_LINE, e -> faultLineButtonHandler());
        makeAbilityButton(DataController.Ability.ROLLING_THUNDER, e -> rollingThunderButtonHandler());
        makeAbilityButton(DataController.Ability.AFTERSHOCK, e -> afterShockButtonHandler());
        makeAbilityButton(DataController.Ability.FLASHPOINT, e -> flashpointButtonHandler());
        makeAbilityButton(DataController.Ability.TOXIC_SCREEN, e -> toxicScreenButtonHandler());
        makeAbilityButton(DataController.Ability.VIPERS_PIT, e -> vipersPitButtonHandler());
        makeAbilityButton(DataController.Ability.SNAKE_BITE, e -> snakeBiteButtonHandler());
        makeAbilityButton(DataController.Ability.POISON_CLOUD, e -> poisonCloudButtonHandler());
        makeAbilityButton(DataController.Ability.HOT_HANDS, e -> hotHandsButtonHandler());
        makeAbilityButton(DataController.Ability.RUN_IT_BACK, e -> runItBackButtonHandler());
        makeAbilityButton(DataController.Ability.BLAZE, e -> blazeButtonHandler());
        makeAbilityButton(DataController.Ability.CURVEBALL, e -> curveballButtonHandler());
        makeAbilityButton(DataController.Ability.HEALING_ORB, e -> healingOrbButtonHandler());
        makeAbilityButton(DataController.Ability.RESURRECTION, e -> resurrectionButtonHandler());
        makeAbilityButton(DataController.Ability.BARRIER_ORB, e -> barrierOrbButtonHandler());
        makeAbilityButton(DataController.Ability.SLOW_ORB, e -> slowOrbButtonHandler());
        makeAbilityButton(DataController.Ability.RECON_BOLT, e -> reconBoltButtonHandler());
        makeAbilityButton(DataController.Ability.HUNTERS_FURY, e -> huntersFuryButtonHandler());
        makeAbilityButton(DataController.Ability.OWL_DRONE, e -> owlDroneButtonHandler());
        makeAbilityButton(DataController.Ability.SHOCK_BOLT, e -> shockBoltButtonHandler());

        //create tool buttons
        makeToolButton("Watch", e -> watchHereButtonHandler());
        makeToolButton("Measure", e -> measuringTapeButtonHandler());
        makeToolButton("Go Here", e -> goHereButtonHandler());
        makeToolButton("Draw", e -> drawButtonHandler());
        makeToolButton("Free Measure", e -> freeformMeasureButtonHandler());


        //a test button for messing around with new decorators, etc
        //make sure to comment this out when building release versions
        makeToolButton("Test button", e -> testButtonHandler());

        //format the buttons
        toolButtons.forEach(b -> formatToolButton(b));

        //add all buttons to the tool selector panel
        int numColumns = 5;
        for(int i = 0; i < toolButtons.size(); i++){
            toolSelector.add(toolButtons.get(i), i % (numColumns - 1) + 1, i / (numColumns - 1));
        }

        //add the character icons to the tool selector
        String[] characterNames = {"Brimstone", "Raze", "Cypher", "Jett", "Omen", "Breach", "Viper", "Phoenix", "Sage", "Sova"};
        for(int i = 0; i < characterNames.length; i++){
            ImageView iconHolder = new ImageView(appController.getData().getCharacterIcon(characterNames[i]));
            toolSelector.getChildren().add(iconHolder);
            GridPane.setColumnIndex(iconHolder, 0);
            GridPane.setRowIndex(iconHolder, i);
        }
    }

    private void testButtonHandler(){
        TwoPointTool testTool = new TwoPointTool("test");
        FilledArc fa = new FilledArc(20, 50, Math.PI/4, Color.GREEN, 0.5, ElementDecorator.Type.END_EXTENDER);
        fa.setAdditionalPointHandler(e -> {
            fa.setVisible(true);
            fa.setEnd(e.getX(), e.getY());
        });
        fa.setVisible(false);
        testTool.setAdditionalPointHandler(testTool::passAdditionalPointsToDecorators);
        testTool.addDecorator(fa);
        twoPointDraggableElementHandler(testTool, this::testButtonHandler);
    }

    /**
     * helper method for creating a button that draws the given ability
     * @param ability - the ability that the button should draw
     * @param eventHandler - the handler method to call when this button is pressed
     */
    private void makeAbilityButton(DataController.Ability ability, EventHandler<ActionEvent> eventHandler){
        Button ret = new Button(appController.getData().getAbilityName(ability));
        ret.setOnAction(eventHandler);
        toolButtons.add(ret);
    }

    /**
     * helper method for creating a generic tool button with the given label and event handler
     * @param label - the label of the button
     * @param eventHandler - the handler to call when the button is pressed
     */
    private void makeToolButton(String label, EventHandler<ActionEvent> eventHandler){
        Button ret = new Button(label);
        ret.setOnAction(eventHandler);
        toolButtons.add(ret);
    }

    /**
     * helper method that formats all the tool buttons to be identical
     * @param b - the button to format
     */
    private void formatToolButton(Button b){
        b.setPrefSize(1000, 1000);
        b.wrapTextProperty().setValue(true);
        b.setTextAlignment(TextAlignment.CENTER);
        Tooltip tt = new Tooltip(appController.getData().getToolTip(b.getText()));
//        tt.setShowDelay(new Duration(0.25));
        b.setTooltip(tt);
    }

    /*
    TOOL BUTTON HANDLERS
     */

    /**
     * Handler for the indicator to watch a certain locatian
     */
    private void watchHereButtonHandler(){
        TwoPointTool tool = new TwoPointTool("WatchHere");
        tool.addDecorator(new ArrowHead());
        twoPointDraggableElementHandler(tool, this::watchHereButtonHandler);
    }

    /**
     * handler for the measuring tape tool
     */
    private void measuringTapeButtonHandler(){
        TwoPointTool tool = new TwoPointTool("Measure");
        TextBox tb = new TextBox("Distance: %,.2f px\nTime to run: %,.2f s\nTime to walk: %,.2f s", ElementDecorator.Type.END_POINT);
        tb.setUpdater(() -> {
            double length = tb.getParent().getLength();
            tb.updateText(length, length / pixelsRunPerSecond, length / pixelsWalkedPerSecond);
        });
        tool.addDecorator(tb);
        twoPointDraggableElementHandler(tool, this::measuringTapeButtonHandler);
    }

    /**
     * handler for the indicator to go to a certain location
     */
    private void goHereButtonHandler(){
        TwoPointTool tool = new TwoPointTool("GoHere");
        tool.addDecorator(new ArrowHead());
        twoPointDraggableElementHandler(tool, this::goHereButtonHandler);
    }

    /**
     * handler for the freeform draw tool
     */
    private void drawButtonHandler(){
        FreeformTool tool = new FreeformTool("Draw");
        freeformElementHandler(tool, this::drawButtonHandler);
    }

    /**
     * handler for the freeform measuring tool
     */
    private void freeformMeasureButtonHandler(){
        FreeformTool tool = new FreeformTool(("Free Measure"));
        TextBox tb = new TextBox("Distance: %,.2f px\nTime to run: %,.2f s\nTime to walk: %,.2f s", ElementDecorator.Type.END_POINT);
        tb.setUpdater(() -> {
            double length = tb.getParent().getLength();
            tb.updateText(length, length / pixelsRunPerSecond, length / pixelsWalkedPerSecond);
        });
        tool.addDecorator(tb);
        freeformElementHandler(tool, this::freeformMeasureButtonHandler);
    }

    /**
     * generic handler for a freeform element
     * @param ft - the freeform element to handle
     * @param handler - the button handler to call to start a new freeform element at the end
     */
    private void freeformElementHandler(FreeformTool ft, ButtonHandler handler){
        canvas.setOnMousePressed(e -> {
            curElement = ft;
            ft.addPoint(e.getX(), e.getY());
            canvas.setOnMouseDragged(e2 -> {
                ft.addPoint(e2.getX(), e2.getY());
                updateCanvas();
            });
            canvas.setOnMouseReleased(e2 -> {
                elements.add(ft);
                curElement = null;
                updateCanvas();
                canvas.setOnMouseDragged(e3 -> { /* INTENTIONALLY NOTHING */});
                canvas.setOnMousePressed(e3 -> { /* INTENTIONALLY NOTHING */ });
                handler.handle();
            });
        });
    }

    /**
     * generic handler for a strat element that consists of two points and updates as the mouse is dragged
     * @param el - the element to draw/update
     * @param handler - the handler to call to initialize the element
     */
    private void twoPointDraggableElementHandler(TwoPointStratElement el, ButtonHandler handler){
        canvas.setOnMouseClicked(e -> {
            el.setStart(e.getX(), e.getY());
            curElement = el;
            canvas.setOnMouseClicked(e2 -> {
                el.setEnd(e2.getX(), e2.getY());
                updateCanvas();

                if(el.hasAdditionalPoints()) {
                    canvas.setOnMouseMoved(e3 -> {
                        el.handleAdditionalPoint(e3);
                        updateCanvas();
                    });
                    canvas.setOnMouseClicked(e3 -> {
                        el.handleAdditionalPoint(e3);
                        elements.add(el);
                        curElement = null;
                        canvas.setOnMouseMoved(e4 -> { /* INTENTIONALLY NOTHING */});
                        updateCanvas();
                        handler.handle();
                    });
                }
                else {
                    elements.add(el);
                    curElement = null;
                    canvas.setOnMouseMoved(e4 -> { /* INTENTIONALLY NOTHING */});
                    updateCanvas();
                    handler.handle();
                }
            });
            canvas.setOnMouseMoved(e3 -> {
                el.setEnd(e3.getX(), e3.getY());
                updateCanvas();
            });
        });



//        canvas.setOnMouseClicked(e -> {
//            el.setStart(e.getX(), e.getY());
//            curElement = el;
//            canvas.setOnMouseClicked(e2 -> {
//                if(e2.getButton().equals(MouseButton.PRIMARY)) {
//                    el.setEnd(e2.getX(), e2.getY());
//                    elements.add(el);
//                    curElement = null;
//                    updateCanvas();
//                    canvas.setOnMouseMoved(e4 -> { /* INTENTIONALLY NOTHING */});
//                    handler.handle();
//                }
//                else if(e2.getButton().equals(MouseButton.SECONDARY)){
//                    //whatever code is necessary to handle a right click
//                    //whenever that feature gets fully implemented
//                }
//            });
//            canvas.setOnMouseMoved(e3 -> {
//                el.setEnd(e3.getX(), e3.getY());
//                updateCanvas();
//            });
//        });
    }

    /**
     * helper method for rendering that saves the state of the canvas
     */
    private void saveCanvasState(){
        canvas.getGraphicsContext2D().save();
    }

    /**
     * helper method for rendering that restores a previously saved canvas state
     */
    private void restoreCanvasState(){
        canvas.getGraphicsContext2D().restore();
    }

    /**
     * helper method for drawing, called when the canvas needs to be redrawn
     * i.e. in the event of a new element being added/removed
     */
    public void updateCanvas(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.drawImage(mapImage, 0, 0, mapImageSize, mapImageSize);
        if(curElement != null){
            curElement.draw(gc);
        }
        elements.stream().forEach(e ->{
            e.draw(gc);
        });
    }

    //FXML defined button handlers
    @FXML
    private void saveStrat(){
        appController.openSaveLoadScreen(StrategySaveLoadScreen.State.SAVE);
    }

    @FXML
    private void loadStrat(){
        appController.openSaveLoadScreen(StrategySaveLoadScreen.State.LOAD);
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
        appController.setSceneToMenu();
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

    public Strategy getCurrentStrategy(){
        return new Strategy(elements);
    }

    public void setCurrentStrategy(Strategy strategy){
        elements = strategy.getElements();
        updateCanvas();
    }

    /**
     * button handlers for character abilities
     */

    private void skySmokeButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.SKY_SMOKE);
        ab.addDecorator(new Circle(visionBlockRadius, Color.DARKGRAY, 0.3, ElementDecorator.Type.END_POINT));
        twoPointDraggableElementHandler(ab, this::skySmokeButtonHandler);
    }

    private void orbitalStrikeButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.ORBITAL_STRIKE);
        ab.addDecorator(new Circle(100, Color.RED, 0.3, ElementDecorator.Type.END_POINT));
        twoPointDraggableElementHandler(ab, this::orbitalStrikeButtonHandler);
    }

    private void stimBeaconButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.STIM_BEACON);
        ab.addDecorator(new Circle(areaDenialRadius, Color.ORANGE, 0.3, ElementDecorator.Type.END_POINT));
        twoPointDraggableElementHandler(ab, this::stimBeaconButtonHandler);
    }

    private void incendiaryButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.INCENDIARY);
        ab.addDecorator(new Circle(areaDenialRadius, Color.RED, 0.3, ElementDecorator.Type.END_POINT));
        twoPointDraggableElementHandler(ab, this::incendiaryButtonHandler);
    }

    private void paintShellsButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.PAINT_SHELLS);
        twoPointDraggableElementHandler(ab, this::paintShellsButtonHandler);
    }

    private void showstopperButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.SHOWSTOPPER);
        twoPointDraggableElementHandler(ab, this::showstopperButtonHandler);
    }

    private void boomBotButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.BOOM_BOT);
        ab.addDecorator(new Rectangle(5, Color.LIGHTGRAY, 0.3, ElementDecorator.Type.START_TO_END));
        twoPointDraggableElementHandler(ab, this::boomBotButtonHandler);
    }

    private void blastPackButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.BLAST_PACK);
        twoPointDraggableElementHandler(ab, this::blastPackButtonHandler);
    }

    private void spycamButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.SPYCAM);
        FilledArc camFOV = new FilledArc(0, 80, Math.PI/2, Color.LIGHTGRAY, 0.3, ElementDecorator.Type.END_EXTENDER);
        camFOV.setVisible(false);
        camFOV.setAdditionalPointHandler(e -> {
            camFOV.setVisible(true);
            camFOV.setEnd(e.getX(), e.getY());
        });
        ab.setAdditionalPointHandler(ab::passAdditionalPointsToDecorators);
        ab.addDecorator(camFOV);
        twoPointDraggableElementHandler(ab, this::spycamButtonHandler);
    }

    private void neuralTheftButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.NEURAL_THEFT);
        twoPointDraggableElementHandler(ab, this::neuralTheftButtonHandler);
    }

    private void tripWireButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.TRIPWIRE);
        twoPointDraggableElementHandler(ab, this::tripWireButtonHandler);
    }

    private void cyberCageButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.CYBER_CAGE);
        ab.addDecorator(new Circle(visionBlockRadius, Color.WHITE, 0.3, ElementDecorator.Type.END_POINT));
        twoPointDraggableElementHandler(ab, this::cyberCageButtonHandler);
    }

    private void tailWindButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.TAILWIND);
        twoPointDraggableElementHandler(ab, this::tailWindButtonHandler);
    }

    private void bladestormButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.BLADESTORM);
        twoPointDraggableElementHandler(ab, this::bladestormButtonHandler);
    }

    private void cloudBurstButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.CLOUDBURST);
        ab.addDecorator(new Circle(visionBlockRadius, Color.LIGHTGRAY, 0.3, ElementDecorator.Type.END_POINT));
        twoPointDraggableElementHandler(ab, this::cloudBurstButtonHandler);
    }

    private void updraftButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.UPDRAFT);
        twoPointDraggableElementHandler(ab, this::updraftButtonHandler);
    }

    private void darkCoverButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.DARK_COVER);
        ab.addDecorator(new Circle(visionBlockRadius, Color.PURPLE, 0.3, ElementDecorator.Type.END_POINT));
        twoPointDraggableElementHandler(ab, this::darkCoverButtonHandler);
    }

    private void fromTheShadowsButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.FROM_THE_SHADOWS);
        twoPointDraggableElementHandler(ab, this::fromTheShadowsButtonHandler);
    }

    private void shroudedStepButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.SHROUDED_STEP);
        twoPointDraggableElementHandler(ab, this::shroudedStepButtonHandler);
    }

    private void paranoiaButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.PARANOIA);
        twoPointDraggableElementHandler(ab, this::paranoiaButtonHandler);
    }

    private void faultLineButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.FAULT_LINE);
        ab.addDecorator(new Rectangle(15, Color.BLUE, 0.3, ElementDecorator.Type.START_TO_END));
        twoPointDraggableElementHandler(ab, this::faultLineButtonHandler);
    }

    private void rollingThunderButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.ROLLING_THUNDER);
        //create and implement an arc/trapezoid class that can represent the area of effect of this ult
        twoPointDraggableElementHandler(ab, this::rollingThunderButtonHandler);
    }

    private void afterShockButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.AFTERSHOCK);
        //arc class could be good here too, but for now just a red rectangle
    ab.addDecorator(new Rectangle(10, Color.RED, 0.3, ElementDecorator.Type.START_TO_END));
        twoPointDraggableElementHandler(ab, this::afterShockButtonHandler);
    }

    private void flashpointButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.FLASHPOINT);
        twoPointDraggableElementHandler(ab, this::flashpointButtonHandler);
    }

    private void toxicScreenButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.TOXIC_SCREEN);
        ab.addDecorator(new Rectangle(10, Color.GREEN, 0.5, ElementDecorator.Type.START_TO_END));
        twoPointDraggableElementHandler(ab, this::toxicScreenButtonHandler);
    }

    private void vipersPitButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.VIPERS_PIT);
        twoPointDraggableElementHandler(ab, this::vipersPitButtonHandler);
    }

    private void snakeBiteButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.SNAKE_BITE);
        ab.addDecorator(new Circle(areaDenialRadius, Color.GREEN, 0.3, ElementDecorator.Type.END_POINT));
        twoPointDraggableElementHandler(ab, this::snakeBiteButtonHandler);
    }

    private void poisonCloudButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.POISON_CLOUD);
        ab.addDecorator(new Circle(visionBlockRadius, Color.GREEN, 0.3, ElementDecorator.Type.END_POINT));
        twoPointDraggableElementHandler(ab, this::poisonCloudButtonHandler);
    }

    private void hotHandsButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.HOT_HANDS);
        ab.addDecorator(new Circle(areaDenialRadius, Color.ORANGERED, 0.3, ElementDecorator.Type.END_POINT));
        twoPointDraggableElementHandler(ab, this::hotHandsButtonHandler);
    }

    private void runItBackButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.RUN_IT_BACK);
        twoPointDraggableElementHandler(ab, this::runItBackButtonHandler);
    }

    private void blazeButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.BLAZE);
        ab.addDecorator(new Rectangle(10, Color.ORANGERED, 0.3, ElementDecorator.Type.START_TO_END));
        twoPointDraggableElementHandler(ab, this::blazeButtonHandler);
    }

    private void curveballButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.CURVEBALL);
        //add a drawable arc class to represent the path of the curveball
        twoPointDraggableElementHandler(ab, this::curveballButtonHandler);
    }

    private void healingOrbButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.HEALING_ORB);
        twoPointDraggableElementHandler(ab, this::healingOrbButtonHandler);
    }

    private void resurrectionButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.RESURRECTION);
        twoPointDraggableElementHandler(ab, this::resurrectionButtonHandler);
    }

    private void barrierOrbButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.BARRIER_ORB);
        twoPointDraggableElementHandler(ab, this::barrierOrbButtonHandler);
    }

    private void slowOrbButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.SLOW_ORB);
        ab.addDecorator(new Circle(62, Color.CYAN, 0.3, ElementDecorator.Type.END_POINT));
        twoPointDraggableElementHandler(ab, this::slowOrbButtonHandler);
    }

    private void reconBoltButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.RECON_BOLT);
        //add a dashed circle or stroke circle to show scan area
        twoPointDraggableElementHandler(ab, this::reconBoltButtonHandler);
    }

    private void huntersFuryButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.HUNTERS_FURY);
        //add decorator rectangles to show where it gets shot once those have been implemented
        twoPointDraggableElementHandler(ab, this::huntersFuryButtonHandler);
    }

    private void owlDroneButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.OWL_DRONE);
        twoPointDraggableElementHandler(ab, this::owlDroneButtonHandler);
    }

    private void shockBoltButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.SHOCK_BOLT);
        ab.addDecorator(new Circle(20, Color.BLUE, 0.3, ElementDecorator.Type.END_POINT));
        twoPointDraggableElementHandler(ab, this::shockBoltButtonHandler);
    }
}
