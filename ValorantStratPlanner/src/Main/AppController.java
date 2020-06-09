package Main;

import DataLayer.AbilityImageStore;
import DataLayer.DataController;
import MenuScreen.MenuScreen;
import StratEditor.StratEditor;
import StrategySaveLoadScreen.StrategySaveLoadScreen;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class AppController {
    private static AppController appController;
    private Stage stage;
    private StratEditor stratEditor;
    private Scene stratEditorScene;
    private MenuScreen menuScreen;
    private Scene menuScreenScene;
    private StrategySaveLoadScreen strategySaveLoadScreen;
    private Scene strategySaveLoadScreenScene;
    private DataController data;
    private String versionString = "v0.31";

    /**
     * default constructor, does nothing
     */
    private AppController(){}

    /**
     * Gets a reference to the single AppController object shared by all classes in the project
     * @return An AppController that is shared between all classes
     */
    public static AppController getInstance(){
        if(appController == null){
            appController = new AppController();
        }
        return appController;
    }

    /**
     * helper method to maximize the window size by setting the stage bounds to the screen bounds
     */
    private void maximizeWindow(){
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        Stage stage = AppController.getInstance().getStage();

        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
    }

    /**
     * helper method to set the scene to the menu
     */
    public void setSceneToMenu(){
        stage.setWidth(600);
        stage.setHeight(310);
        stage.setScene(menuScreenScene);
    }

    /**
     * helper method to set the scene to the strat editor
     */
    public void setSceneToStratEditor(){
        maximizeWindow();
        stage.setScene(stratEditorScene);
        stratEditor.updateCanvas();
    }

    /**
     * helper method to open the save/load strategy screen
     */
    public void openSaveLoadScreen(StrategySaveLoadScreen.State state){
        strategySaveLoadScreen.stage = new Stage();
        strategySaveLoadScreen.stage.initModality(Modality.APPLICATION_MODAL);
        strategySaveLoadScreen.stage.setWidth(600);
        strategySaveLoadScreen.stage.setHeight(310);
        strategySaveLoadScreen.setup(state);
        strategySaveLoadScreen.stage.setScene(strategySaveLoadScreenScene);
        strategySaveLoadScreen.stage.show();
    }

    /**
     * GETTERS AND SETTERS
     */

    public Scene getStratEditorScene() {
        return stratEditorScene;
    }

    public void setStratEditorScene(Scene stratEditorScene) {
        this.stratEditorScene = stratEditorScene;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void setStratEditor(StratEditor stratEditor){
        this.stratEditor = stratEditor;
    }

    public Stage getStage() {
        return stage;
    }

    public StratEditor getStratEditor() {
        return stratEditor;
    }

    public MenuScreen getMenuScreen() {
        return menuScreen;
    }

    public void setMenuScreen(MenuScreen menuScreen) {
        this.menuScreen = menuScreen;
    }

    public Scene getMenuScreenScene() {
        return menuScreenScene;
    }

    public DataController getData() {
        return data;
    }

    public void setData(DataController data) {
        this.data = data;
    }

    public void setMenuScreenScene(Scene menuScreenScene) {
        this.menuScreenScene = menuScreenScene;
    }

    public StrategySaveLoadScreen getStrategySaveLoadScreen() {
        return strategySaveLoadScreen;
    }

    public void setStrategySaveLoadScreen(StrategySaveLoadScreen strategySaveLoadScreen) {
        this.strategySaveLoadScreen = strategySaveLoadScreen;
    }

    public Scene getStrategySaveLoadScreenScene() {
        return strategySaveLoadScreenScene;
    }

    public void setStrategySaveLoadScreenScene(Scene strategySaveLoadScreenScene) {
        this.strategySaveLoadScreenScene = strategySaveLoadScreenScene;
    }

    public String getVersionString(){
        return versionString;
    }
}
