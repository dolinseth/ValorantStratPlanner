package StratEditor;

import DataLayer.DataController;
import Shapes.Circle;
import Shapes.ElementDecorator;
import Shapes.Rectangle;
import StratElements.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.ArrayList;

import Main.AppController;
import javafx.scene.paint.Color;

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

    //non-FXML fields
    private AppController appController;
    public enum Map {BIND, SPLIT, HAVEN};
    private Map curMap;
    private ArrayList<StratElement> elements = new ArrayList<StratElement>();
    private TwoPointStratElement curElement;
    private ArrayList<Button> toolButtons;

    /**
     * To be called when this scene is initialized to setup scene objects at runtime
     */
    public void setup(){
        createToolSelectorButtons();
//        drawingController = DrawingController.getInstance();
    }

    /**
     * Sets the map image that is displayed on the canvas beneath the strats
     * @param map - a StratEditor.Map typed object describing the map to use
     */
    public void setMapImage(Map map){
        this.curMap = map;
        switch (map){
            case BIND:
                setMapViewerImage("MapImages/Bind.PNG");
                break;
            case SPLIT:
                setMapViewerImage("MapImages/Split.PNG");
                break;
            case HAVEN:
                setMapViewerImage("MapImages/Haven.PNG");
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


        //create line drawing button
        Button line = new Button("Line");
        formatToolButton(line);
        line.setOnAction(e -> lineButtonHandler());
        toolButtons.add(line);

        //create watch here indicator
        Button watchHere = new Button("Watch");
        formatToolButton(watchHere);
        watchHere.setOnAction(e -> watchHereButtonHandler());
        toolButtons.add(watchHere);

        //add all buttons to the tool selector panel
        int numColumns = toolSelector.getColumnCount();
        for(int i = 0; i < toolButtons.size(); i++){
            toolSelector.add(toolButtons.get(i), i % (numColumns - 1) + 1, i / (numColumns - 1));
        }

        String[] characterNames = {"Brimstone", "Raze", "Cypher", "Jett", "Omen", "Breach", "Viper", "Phoenix", "Sage", "Sova"};
        for(int i = 0; i < characterNames.length; i++){
            ImageView iconHolder = new ImageView(appController.getData().getCharacterIcon(characterNames[i]));
            toolSelector.getChildren().add(iconHolder);
            GridPane.setColumnIndex(iconHolder, 0);
            GridPane.setRowIndex(iconHolder, i);
        }
    }

    private void makeAbilityButton(DataController.Ability ability, EventHandler<ActionEvent> eventHandler){
        Button ret = new Button(appController.getData().getAbilityName(ability));
        formatToolButton(ret);
        ret.setOnAction(eventHandler);
        toolButtons.add(ret);
    }

    /*
    TOOL BUTTON HANDLERS
     */

    /**
     * Handler for strat elements that just consist of an image representing the ability
     * that is placed on the map
     * @param abilityName - string containing the name of the ability
     */
//    private void abilityImageButtonHandler(String abilityName){
//        AbilityImageStratElement el = new AbilityImageStratElement(abilityName, appController.getData());
//        onePointElementHandler(el);
//    }

    /**
     * Handler for the indicator to watch a certain locatian
     */
    private void watchHereButtonHandler(){
        twoPointDraggableElementHandler(new WatchHere(), this::watchHereButtonHandler);
    }

    /**
     * Handler for the line drawing tool
     */
    private void lineButtonHandler(){
        twoPointDraggableElementHandler(new Line(), this::lineButtonHandler);
    }


    /**
     *  button handlers for character abilities
     *
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
        ab.addDecorator(new Rectangle(10, Color.RED, 0.3, ElementDecorator.Type.END_POINT));
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

    private void twoPointDraggableElementHandler(TwoPointStratElement el, ButtonHandler handler){
        TwoPointElementBuilder<TwoPointStratElement> eb = new TwoPointElementBuilder<>();
        canvas.setOnMousePressed(e -> {
            eb.startClick(e);
            curElement = el;
            canvas.setOnMouseReleased(e2 -> {
                eb.endClick(e2);
                eb.formatElement(el);
                elements.add(el);
                curElement = null;
                updateCanvas();
                canvas.setOnMouseMoved(e4 -> { /* INTENTIONALLY NOTHING */});
                handler.handle();
            });
            canvas.setOnMouseDragged(e3 -> {
                eb.endClick(e3);
                eb.formatElement(el);
                updateCanvas();
                el.draw(canvas.getGraphicsContext2D());
            });
        });
    }

    /**
     * generic handler for an element that consists of two points
     * @param el - the two point element to place on the canvas
     */
    private void twoPointElementHandler(TwoPointStratElement el, ButtonHandler handler){
        TwoPointElementBuilder<TwoPointStratElement> eb = new TwoPointElementBuilder<>();
        System.out.println("elements: " + elements.size());
        canvas.setOnMousePressed(e ->{
            eb.startClick(e);
            canvas.setOnMousePressed(e2 ->{
                eb.endClick(e2);
                eb.formatElement(el);
                elements.add(el);
                updateCanvas();
                handler.handle();
//                twoPointElementHandler(el, handler);
            });
        });
    }

    /**
     * generic handler for an element consisting of one point
     * @param el - the one point element to place on the canvas
     */
    private void onePointElementHandler(OnePointStratElement el, ButtonHandler handler){
        OnePointElementBuilder<OnePointStratElement> eb = new OnePointElementBuilder<>();
        canvas.setOnMousePressed(e -> {
            eb.click(e);
            eb.formatElement(el);
            elements.add(el);
            updateCanvas();
            onePointElementHandler(el, handler);
        });
    }

    /**
     * helper method that formats all the tool buttons to be identical
     * @param b - the button to format
     */
    private void formatToolButton(Button b){
//        b.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        b.setPrefSize(1000, 1000);
    }

    /**
     * helper method for drawing, called when the canvas needs to be redrawn
     * i.e. in the event of a new element being added/removed
     */
    public void updateCanvas(){
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        if(curElement != null){
            curElement.draw(canvas.getGraphicsContext2D());
        }
//        DrawingController.getInstance().getElements().stream().forEach(e ->{
//            e.draw(canvas.getGraphicsContext2D());
//        });
        elements.stream().forEach(e ->{
            e.draw(canvas.getGraphicsContext2D());
        });
    }

    /**
     * helper method for setMapImage that takes in the actual filepath instead of the enumerated map type
     * @param imageFileName
     */
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
