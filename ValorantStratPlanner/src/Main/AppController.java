package Main;

import MenuScreen.MenuScreen;
import StratEditor.StratEditor;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppController {
    private static AppController appController;
    private Stage stage;
    private StratEditor stratEditor;
    private Scene stratEditorScene;
    private MenuScreen menuScreen;
    private Scene menuScreenScene;

    private AppController(){}

    public static AppController getInstance(){
        if(appController == null){
            appController = new AppController();
        }
        return appController;
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

    public void setMenuScreenScene(Scene menuScreenScene) {
        this.menuScreenScene = menuScreenScene;
    }
}
