package StratEditor;

import DataLayer.DataController;
import ElementDecorators.*;
import Records.Point;
import StratElements.*;
import StrategySaveLoadScreen.StrategySaveLoadScreen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.util.ArrayList;

import AppController.AppController;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import static StratElements.CharacterAbility.areaDenialRadius;
import static StratElements.CharacterAbility.visionBlockRadius;

public class StratEditor {
    //FXML defined buttons and panes
    @FXML
    private GridPane toolSelector;
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
    @FXML
    private Slider zoomSlider;
    @FXML
    private javafx.scene.shape.Rectangle zoomControlBackground;

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
    public final double pixelsPerInGameUnit = 0.10444;
    public double scale = 1.0;
    private double canvasXOffset = 0;
    private double canvasYOffset = 0;

    /**
     * To be called when this scene is initialized to setup scene objects at runtime
     */
    public void setup(){
        createToolSelectorButtons();
        updateCanvas();
        canvas.getGraphicsContext2D().scale(scale, scale);

        //set background image
        Image bgImage = new Image(getClass().getResource("/BackgroundImages/Blueprint-background.png").toString(), 1920, 1080, true, false);
        BackgroundImage bg = new BackgroundImage(bgImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        mainPane.setBackground(new Background(bg));

        //set scale function for zoom slider;
        zoomSlider.valueProperty().addListener((source, oldVal, newVal) -> {
            scale = newVal.doubleValue() / 100.0;
            updateCanvas();
        });
        zoomControlBackground.setArcHeight(20);
        zoomControlBackground.setArcWidth(20);
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
            case ASCENT:
                mapImageSize = 970;
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
        makeCharacterButton("Brimstone");
        makeAbilityButton(DataController.Ability.SKY_SMOKE, e -> skySmokeButtonHandler());
        makeAbilityButton(DataController.Ability.ORBITAL_STRIKE, e -> orbitalStrikeButtonHandler());
        makeAbilityButton(DataController.Ability.STIM_BEACON, e -> stimBeaconButtonHandler());
        makeAbilityButton(DataController.Ability.INCENDIARY, e -> incendiaryButtonHandler());
        makeCharacterButton("Raze");
        makeAbilityButton(DataController.Ability.PAINT_SHELLS, e -> paintShellsButtonHandler());
        makeAbilityButton(DataController.Ability.SHOWSTOPPER, e -> showstopperButtonHandler());
        makeAbilityButton(DataController.Ability.BOOM_BOT, e -> boomBotButtonHandler());
        makeAbilityButton(DataController.Ability.BLAST_PACK, e -> blastPackButtonHandler());
        makeCharacterButton("Cypher");
        makeAbilityButton(DataController.Ability.SPYCAM, e -> spycamButtonHandler());
        makeAbilityButton(DataController.Ability.NEURAL_THEFT, e -> neuralTheftButtonHandler());
        makeAbilityButton(DataController.Ability.TRIPWIRE, e -> tripWireButtonHandler());
        makeAbilityButton(DataController.Ability.CYBER_CAGE, e -> cyberCageButtonHandler());
        makeCharacterButton("Jett");
        makeAbilityButton(DataController.Ability.TAILWIND, e -> tailWindButtonHandler());
        makeAbilityButton(DataController.Ability.BLADESTORM, e -> bladestormButtonHandler());
        makeAbilityButton(DataController.Ability.CLOUDBURST, e -> cloudBurstButtonHandler());
        makeAbilityButton(DataController.Ability.UPDRAFT, e -> updraftButtonHandler());
        makeCharacterButton("Omen");
        makeAbilityButton(DataController.Ability.DARK_COVER, e -> darkCoverButtonHandler());
        makeAbilityButton(DataController.Ability.FROM_THE_SHADOWS, e -> fromTheShadowsButtonHandler());
        makeAbilityButton(DataController.Ability.SHROUDED_STEP, e -> shroudedStepButtonHandler());
        makeAbilityButton(DataController.Ability.PARANOIA, e -> paranoiaButtonHandler());
        makeCharacterButton("Breach");
        makeAbilityButton(DataController.Ability.FAULT_LINE, e -> faultLineButtonHandler());
        makeAbilityButton(DataController.Ability.ROLLING_THUNDER, e -> rollingThunderButtonHandler());
        makeAbilityButton(DataController.Ability.AFTERSHOCK, e -> afterShockButtonHandler());
        makeAbilityButton(DataController.Ability.FLASHPOINT, e -> flashpointButtonHandler());
        makeCharacterButton("Viper");
        makeAbilityButton(DataController.Ability.TOXIC_SCREEN, e -> toxicScreenButtonHandler());
        makeAbilityButton(DataController.Ability.VIPERS_PIT, e -> vipersPitButtonHandler());
        makeAbilityButton(DataController.Ability.SNAKE_BITE, e -> snakeBiteButtonHandler());
        makeAbilityButton(DataController.Ability.POISON_CLOUD, e -> poisonCloudButtonHandler());
        makeCharacterButton("Phoenix");
        makeAbilityButton(DataController.Ability.HOT_HANDS, e -> hotHandsButtonHandler());
        makeAbilityButton(DataController.Ability.RUN_IT_BACK, e -> runItBackButtonHandler());
        makeAbilityButton(DataController.Ability.BLAZE, e -> blazeButtonHandler());
        makeAbilityButton(DataController.Ability.CURVEBALL, e -> curveballButtonHandler());
        makeCharacterButton("Sage");
        makeAbilityButton(DataController.Ability.HEALING_ORB, e -> healingOrbButtonHandler());
        makeAbilityButton(DataController.Ability.RESURRECTION, e -> resurrectionButtonHandler());
        makeAbilityButton(DataController.Ability.BARRIER_ORB, e -> barrierOrbButtonHandler());
        makeAbilityButton(DataController.Ability.SLOW_ORB, e -> slowOrbButtonHandler());
        makeCharacterButton("Sova");
        makeAbilityButton(DataController.Ability.RECON_BOLT, e -> reconBoltButtonHandler());
        makeAbilityButton(DataController.Ability.HUNTERS_FURY, e -> huntersFuryButtonHandler());
        makeAbilityButton(DataController.Ability.OWL_DRONE, e -> owlDroneButtonHandler());
        makeAbilityButton(DataController.Ability.SHOCK_BOLT, e -> shockBoltButtonHandler());
        makeCharacterButton("Reyna");
        makeAbilityButton(DataController.Ability.LEER, e -> leerButtonHandler());
        makeAbilityButton(DataController.Ability.DEVOUR, e -> devourButtonHandler());
        makeAbilityButton(DataController.Ability.DISMISS, e -> dismissButtonHandler());
        makeAbilityButton(DataController.Ability.EMPRESS, e -> empressButtonHandler());

        //create tool buttons
        makeToolButton("Pan", e -> panButtonHandler());
        makeToolButton("Watch", e -> watchHereButtonHandler());
        makeToolButton("Go Here", e -> goHereButtonHandler());
        makeToolButton("Measure", e -> measuringTapeButtonHandler());
        makeToolButton("Free Measure", e -> freeformMeasureButtonHandler());
        makeToolButton("Draw", e -> drawButtonHandler());


        //a test button for messing around with new decorators, etc
        if(appController.getDebugModeEnabled()) {
            makeToolButton("Test button", e -> testButtonHandler());
        }

        //format the buttons
        toolButtons.forEach(this::formatToolButton);

        //add all buttons to the tool selector panel
        for(int i = 0; i < toolButtons.size(); i++){
            toolSelector.add(toolButtons.get(i), i % toolSelector.getColumnCount(), i / toolSelector.getColumnCount());
        }
    }

    /**
     * helper method to create a button that places the given character
     * @param charName - the name of the character to place
     */
    private void makeCharacterButton(String charName){
        ImageView charIcon = new ImageView(appController.getData().getCharacterIcon(charName));
        charIcon.setFitWidth(30);
        charIcon.setPreserveRatio(true);
        Button ret = new Button(charName, charIcon);
        ret.setOnAction(e -> characterButtonHandler(charName));
        toolButtons.add(ret);
    }

    /**
     * button handler for characters since placing them is identical
     * @param charName - the name of the character
     */
    private void characterButtonHandler(String charName){
        CharacterTool tool = new CharacterTool(charName);
        tool.addDecorator(new ArrowHead());
        twoPointDraggableElementHandler(tool, () -> characterButtonHandler(charName));
    }

    /**
     * button handler for the test button that is used for debugging
     * modify as needed to test various features
     */
    private void testButtonHandler(){
        TwoPointTool testTool = new TwoPointTool("test");
        twoPointDraggableElementHandler(testTool, this::testButtonHandler);
    }

    /**
     * helper method for creating a button that draws the given ability
     * @param ability - the ability that the button should draw
     * @param eventHandler - the handler method to call when this button is pressed
     */
    private void makeAbilityButton(DataController.Ability ability, EventHandler<ActionEvent> eventHandler){
        ImageView abilityIcon = new ImageView(appController.getData().getAbilityImage(ability));
        abilityIcon.setFitWidth(30);
        abilityIcon.setPreserveRatio(true);
        Button ret = new Button(appController.getData().getAbilityName(ability), abilityIcon);
        ret.setOnAction(eventHandler);
        toolButtons.add(ret);
    }

    /**
     * helper method for creating a generic tool button with the given label and event handler
     * @param label - the label of the button
     * @param eventHandler - the handler to call when the button is pressed
     */
    private void makeToolButton(String label, EventHandler<ActionEvent> eventHandler){
        Image toolImage = appController.getData().getToolImage(label.replaceAll(" ", ""));
        if(toolImage != null) {
            ImageView toolIcon = new ImageView(toolImage);
            toolIcon.setFitWidth(30);
            toolIcon.setPreserveRatio(true);
            Button ret = new Button(label, toolIcon);
            ret.setOnAction(eventHandler);
            toolButtons.add(ret);
        }
        else {
            Button ret = new Button(label);
            ret.setOnAction(eventHandler);
            toolButtons.add(ret);
        }
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
        tt.setShowDelay(new Duration(0.25));
        tt.setWrapText(true);
        tt.setPrefWidth(300);
        tt.setStyle("-fx-font-size:15");
        b.setTooltip(tt);
        b.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(10), null)));
        b.setTextFill(Color.WHITE);
    }

    /*
    TOOL BUTTON HANDLERS
     */

    /**
     * Handler for the indicator to watch a certain locatian
     */
    private void watchHereButtonHandler(){
        TwoPointTool tool = new TwoPointTool("Watch");
        tool.addDecorator(new ArrowHead());
        twoPointDraggableElementHandler(tool, this::watchHereButtonHandler);
    }

    /**
     * handler for the measuring tape tool
     */
    private void measuringTapeButtonHandler(){
        TwoPointTool tool = new TwoPointTool("Measure");
        addMeasureTextDecorator(tool);
        twoPointDraggableElementHandler(tool, this::measuringTapeButtonHandler);
    }

    /**
     * helper method to unify the measurement tool decoration between freeform and twopoint
     * @param tool - the freeform measure or two point measure tool to add the decorators to
     */
    private void addMeasureTextDecorator(TwoPointStratElement tool){
        TextBox tb = new TextBox((appController.getDebugModeEnabled() ? "Distance: %,.2f px\n" : "") + "Time to run: %,.2f s\nTime to walk: %,.2f s", ElementDecorator.Type.END_POINT);
        tb.setUpdater(() -> {
            double length = tb.getParent().getLength();
            if(appController.getDebugModeEnabled()) {
                tb.updateText(length, length / pixelsRunPerSecond, length / pixelsWalkedPerSecond);
            }
            else{
                tb.updateText(length / pixelsRunPerSecond, length / pixelsWalkedPerSecond);
            }
        });
        tool.addDecorator(tb);
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
        addMeasureTextDecorator(tool);
        freeformElementHandler(tool, this::freeformMeasureButtonHandler);
    }

    private void panButtonHandler(){
        canvas.setOnMousePressed(e -> {
            Point p = new Point(e.getX(), e.getY());
            canvas.setOnMouseDragged(e2 -> {
                canvasXOffset += e2.getX() - p.x;
                canvasYOffset += e2.getY() - p.y;
                p.x = e2.getX();
                p.y = e2.getY();
                updateCanvas();
            });
            canvas.setOnMouseReleased(e2 -> {
                clearCanvasHandlers();
                panButtonHandler();
            });
        });
    }

    /**
     * generic handler for a freeform element
     * @param ft - the freeform element to handle
     * @param handler - the button handler to call to start a new freeform element at the end
     */
    private void freeformElementHandler(FreeformTool ft, ButtonHandler handler){
        clearCanvasHandlers();
        canvas.setOnMousePressed(e -> {
            if(e.getButton() == MouseButton.PRIMARY) {
                curElement = ft;
                ft.addPoint(scaleX(e.getX()), scaleY(e.getY()));
                updateCanvas();
                canvas.setOnMouseDragged(e2 -> {
                    if(e.getButton() == MouseButton.PRIMARY) {
                        ft.addPoint(scaleX(e2.getX()), scaleY(e2.getY()));
                        updateCanvas();
                    }
                });
                canvas.setOnMouseReleased(e2 -> {
                    elements.add(ft);
                    curElement = null;
                    updateCanvas();
                    canvas.setOnMouseDragged(e3 -> { /* INTENTIONALLY NOTHING */});
                    canvas.setOnMousePressed(e3 -> { /* INTENTIONALLY NOTHING */ });
                    handler.handle();
                });
            }
        });
    }

    /**
     * generic handler for a strat element that consists of two points and updates as the mouse is dragged
     * @param el - the element to draw/update
     * @param handler - the handler to call to initialize the element
     */
    private void twoPointDraggableElementHandler(TwoPointStratElement el, ButtonHandler handler){
        clearCanvasHandlers();
        canvas.setOnMouseClicked(e -> {
            if(e.getButton() == MouseButton.PRIMARY){
                el.setStart(scaleX(e.getX()), scaleY(e.getY()));
                el.setEnd(scaleX(e.getX()), scaleY(e.getY()));
                curElement = el;
                updateCanvas();
                canvas.setOnMouseClicked(e2 -> {
                    el.setEnd(scaleX(e2.getX()), scaleY(e2.getY()));
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
                    el.setEnd(scaleX(e3.getX()), scaleY(e3.getY()));
                    updateCanvas();
                });
            }
       });
    }

    /**
     * helper method that sets all the canvas handlers to do nothing
     */
    private void clearCanvasHandlers(){
        canvas.setOnMousePressed(e -> {});
        canvas.setOnMouseDragged(e -> {});
        canvas.setOnMouseMoved(e -> {});
        canvas.setOnMouseReleased(e -> {});
        canvas.setOnMouseClicked(e -> {});
    }

   /**
     * helper method for rendering that scales x coordinates
     * @param originalCoord - the original coordinate
     * @return - the scaled coordinate
     */
    private double scaleX(double originalCoord){
        return (originalCoord - canvasXOffset) / scale;
    }

    /**
     * helper method for rendering that scales y coordinates
     * @param originalCoord - the original coordinate
     * @return - the scaled coordinate
     */
    private double scaleY(double originalCoord){
        return (originalCoord - canvasYOffset) / scale;
    }

    /**
     * helper method for drawing, called when the canvas needs to be redrawn
     * i.e. in the event of a new element being added/removed
     */
    public void updateCanvas(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setTransform(1, 0, 0, 1, 0, 0);
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setTransform(scale, 0, 0, scale, canvasXOffset, canvasYOffset);
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

    /**
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
        ab.addDecorator(new Circle(areaDenialRadius, Color.ORANGE, 0.3, ElementDecorator.Type.END_POINT));
        twoPointDraggableElementHandler(ab, this::paintShellsButtonHandler);
    }

    private void showstopperButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.SHOWSTOPPER);
        twoPointDraggableElementHandler(ab, this::showstopperButtonHandler);
    }

    private void boomBotButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.BOOM_BOT);
        ab.addDecorator(new Rectangle(10, Color.LIGHTGRAY, 0.3, ElementDecorator.Type.START_TO_END));
        ab.setShowLine(false);
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
            camFOV.setEnd(scaleX(e.getX()), scaleY(e.getY()));
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
        //create the tripwire line indicator
        Line l = new Line(Color.WHITE, ElementDecorator.Type.END_EXTENDER);
        l.setAdditionalPointHandler(e -> {
            l.setEnd(scaleX(e.getX()), scaleY(e.getY()));
            l.setVisible(true);
            l.setDecoratorVisibility(true);
        });
        l.setVisible(false);
        l.setDecoratorVisibility(false);

        //add the circles at the ends of the tripwire line
        Circle a = new Circle(15, Color.WHITE, 0.5, ElementDecorator.Type.START_POINT);
        Circle b = new Circle(15, Color.WHITE, 0.5, ElementDecorator.Type.END_POINT);
        a.setFill(false);
        b.setFill(false);
        l.addDecorator(a);
        l.addDecorator(b);

        l.setMaxLength(60);
        ab.setAdditionalPointHandler(ab::passAdditionalPointsToDecorators);
        ab.addDecorator(l);
        twoPointDraggableElementHandler(ab, this::tripWireButtonHandler);
    }

    private void cyberCageButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.CYBER_CAGE);
        ab.addDecorator(new Circle(44, Color.WHITE, 0.3, ElementDecorator.Type.END_POINT));
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
        ab.addDecorator(new Circle(51, Color.PURPLE, 0.3, ElementDecorator.Type.END_POINT));
        twoPointDraggableElementHandler(ab, this::darkCoverButtonHandler);
    }

    private void fromTheShadowsButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.FROM_THE_SHADOWS);
        twoPointDraggableElementHandler(ab, this::fromTheShadowsButtonHandler);
    }

    private void shroudedStepButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.SHROUDED_STEP);
        //set max length, need screenshots though
        twoPointDraggableElementHandler(ab, this::shroudedStepButtonHandler);
    }

    private void paranoiaButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.PARANOIA);
        Rectangle aoe = new Rectangle(47, Color.PURPLE, 0.5, ElementDecorator.Type.START_TO_END);
        aoe.setMaxLength(189);
        aoe.setMinLength(189);
        ab.addDecorator(aoe);
        twoPointDraggableElementHandler(ab, this::paranoiaButtonHandler);
    }

    private void faultLineButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.FAULT_LINE);
        ab.setShowLine(false);
        Rectangle aoe = new Rectangle(30, Color.CYAN, 0.3, ElementDecorator.Type.START_TO_END);
        aoe.setMaxLength(298);
        ab.addDecorator(aoe);
        twoPointDraggableElementHandler(ab, this::faultLineButtonHandler);
    }

    private void rollingThunderButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.ROLLING_THUNDER);
        TieredTrapezoid aoe = new TieredTrapezoid(8, 54, 19, 5, 17, Color.RED, 0.3, ElementDecorator.Type.START_TO_END);
        ab.addDecorator(aoe);
        ab.setShowLine(false);
        twoPointDraggableElementHandler(ab, this::rollingThunderButtonHandler);
    }

    private void afterShockButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.AFTERSHOCK);
        //arc class could be good here too, but for now just a red rectangle
        Rectangle aoe = new Rectangle(38, Color.RED, 0.3, ElementDecorator.Type.END_EXTENDER);
        aoe.setUpdater(() -> {
            double dx = ab.getX2() - ab.getX1();
            double dy = ab.getY2() - ab.getY1();
            double theta = Math.atan2(dy, dx);
            aoe.setEnd(aoe.getX1() + Math.cos(theta), aoe.getY1() + Math.sin(theta));
        });
        aoe.setMaxLength(42);
        aoe.setMinLength(42);
        ab.addDecorator(aoe);
        twoPointDraggableElementHandler(ab, this::afterShockButtonHandler);
    }

    private void flashpointButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.FLASHPOINT);
        twoPointDraggableElementHandler(ab, this::flashpointButtonHandler);
    }

    private void toxicScreenButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.TOXIC_SCREEN);
        ab.addDecorator(new Rectangle(10, Color.GREEN, 0.5, ElementDecorator.Type.START_TO_END));
        ab.setShowLine(false);
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
        ab.addDecorator(new Circle(56, Color.GREEN, 0.3, ElementDecorator.Type.END_POINT));
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
        Rectangle rect = new Rectangle(10, Color.ORANGERED, 0.3, ElementDecorator.Type.START_TO_END);
        rect.setMaxLength(125);
        ab.addDecorator(rect);
        ab.setShowLine(false);
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
        Rectangle rect = new Rectangle(70, Color.CYAN, 0.5, ElementDecorator.Type.END_EXTENDER);
        rect.setAdditionalPointHandler(e -> {
            rect.setEnd(scaleX(e.getX()), scaleY(e.getY()));
            rect.setVisible(true);
        });
        rect.setVisible(false);
        rect.setMinLength(14);
        rect.setMaxLength(14);
        ab.setAdditionalPointHandler(ab::passAdditionalPointsToDecorators);
        ab.addDecorator(rect);
        twoPointDraggableElementHandler(ab, this::barrierOrbButtonHandler);
    }

    private void slowOrbButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.SLOW_ORB);
        ab.addDecorator(new Circle(62, Color.CYAN, 0.3, ElementDecorator.Type.END_POINT));
        twoPointDraggableElementHandler(ab, this::slowOrbButtonHandler);
    }

    private void reconBoltButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.RECON_BOLT);
        Circle circle = new Circle(322, Color.YELLOW, 1, ElementDecorator.Type.END_POINT);
        circle.setFill(false);
        ab.addDecorator(circle);
        twoPointDraggableElementHandler(ab, this::reconBoltButtonHandler);
    }

    private void huntersFuryButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.HUNTERS_FURY);
        Rectangle rect = new Rectangle(20, Color.CYAN, 0.3, ElementDecorator.Type.START_TO_END);
        rect.setMaxLength(393);
        rect.setMinLength(393);
        ab.addDecorator(rect);
        ab.setShowLine(false);
        twoPointDraggableElementHandler(ab, this::huntersFuryButtonHandler);
    }

    private void owlDroneButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.OWL_DRONE);
        twoPointDraggableElementHandler(ab, this::owlDroneButtonHandler);
    }

    private void shockBoltButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.SHOCK_BOLT);
        ab.addDecorator(new Circle(15, Color.CYAN, 0.3, ElementDecorator.Type.END_POINT));
        twoPointDraggableElementHandler(ab, this::shockBoltButtonHandler);
    }

    private void leerButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.LEER);
        ab.setMaxLength(74);
        Circle circle = new Circle(390, Color.YELLOW, 1, ElementDecorator.Type.END_POINT);
        circle.setFill(false);
        ab.addDecorator(circle);
        twoPointDraggableElementHandler(ab, this::leerButtonHandler);
    }

    private void devourButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.DEVOUR);
        twoPointDraggableElementHandler(ab, this::devourButtonHandler);
    }

    private void dismissButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.DISMISS);
        twoPointDraggableElementHandler(ab, this::dismissButtonHandler);
    }

    private void empressButtonHandler(){
        CharacterAbility ab = new CharacterAbility(DataController.Ability.EMPRESS);
        twoPointDraggableElementHandler(ab, this::empressButtonHandler);
    }
}
