package Main;

import DataLayer.AbilityImageStore;
import DataLayer.DataController;
import MenuScreen.MenuScreen;
import StratEditor.StratEditor;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class AppController {
    private static AppController appController;
    private Stage stage;
    private StratEditor stratEditor;
    private Scene stratEditorScene;
    private MenuScreen menuScreen;
    private Scene menuScreenScene;
    private DataController data;

    //default constructor
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

    private void maximizeWindow(){
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        Stage stage = AppController.getInstance().getStage();

        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
    }

    public void setSceneToMenu(){
        stage.setWidth(600);
        stage.setHeight(310);
        stage.setScene(menuScreenScene);
    }

    public void setSceneToStratEditor(){
        maximizeWindow();
        stage.setScene(stratEditorScene);
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
}
