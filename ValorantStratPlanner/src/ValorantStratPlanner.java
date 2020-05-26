import DataLayer.DataController;
import Main.AppController;
import MenuScreen.MenuScreen;
import StratEditor.StratEditor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;

import static javafx.application.Application.launch;

public class ValorantStratPlanner extends Application {
    private Stage stage;
    private AppController appController;

    public static void main(String[] args){
        launch(args);
    }

    /**
     * called by JavaFX API when the program starts
     * @param stage - the Stage passed by the JavaFX API
     */
    public void start(Stage stage){
        //initialize Main.AppController object and set it's stage
        appController = AppController.getInstance();

        //load FXML
        Parent stratEditorRoot = null;
        Parent menuScreenRoot = null;
        FXMLLoader stratEditorLoader = new FXMLLoader(this.getClass().getResource("fxml/StratEditor.fxml"));
        FXMLLoader menuScreenLoader = new FXMLLoader(this.getClass().getResource("fxml/MenuScreen.fxml"));
        StratEditor stratEditor = null;
        MenuScreen menuScreen = null;

        try{
            stratEditorRoot = stratEditorLoader.load();
            stratEditor = stratEditorLoader.getController();
            menuScreenRoot = menuScreenLoader.load();
            menuScreen = menuScreenLoader.getController();
        } catch (IOException e){
            System.out.println(e.getMessage());
            System.out.println("Failed to load fxml");
            System.exit(1);
        }

        //create scenes
        Scene stratEditorScene = new Scene(stratEditorRoot);
        Scene menuScreenScene = new Scene(menuScreenRoot);

        //set the window title
        stage.setTitle("Valorant Strat Planner v0.1");

        //give screen controllers a reference to the app controller
        stratEditor.setAppController(appController);
        menuScreen.setAppController(appController);

        //give appController references to scenes and such
        appController.setStratEditor(stratEditor);
        appController.setStratEditorScene(stratEditorScene);
        appController.setStage(stage);
        appController.setMenuScreen(menuScreen);
        appController.setMenuScreenScene(menuScreenScene);

        //initialize data controller
        DataController data = new DataController();
        appController.setData(data);

        //set the initial scene to the menu
        stage.setScene(menuScreenScene);

        //setup screen objects
        stratEditor.setup();

        //must be final call in the function, tells JavaFX to start the app
        stage.show();
    }
}
