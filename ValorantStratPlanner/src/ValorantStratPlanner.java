import Main.AppController;
import StratEditor.StratEditor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class ValorantStratPlanner extends Application {
    private Stage stage;
    private AppController appController;

    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage stage){
        //initialize Main.AppController object and set it's stage
        appController = AppController.getInstance();

        //load FXML for the strat editor window
        Parent stratEditorRoot = null;
        FXMLLoader stratEditorLoader = new FXMLLoader(this.getClass().getResource("fxml/StratEditor.fxml"));
        StratEditor stratEditor = null;
        try{
            stratEditorRoot = stratEditorLoader.load();
            stratEditor = stratEditorLoader.getController();
        } catch (IOException e){
            System.out.println(e.getMessage());
            System.out.println("Failed to load fxml");
            System.exit(1);
        }

        //create scene for StratEditor.StratEditor
        Scene stratEditorScene = new Scene(stratEditorRoot);

        //set the window title
        stage.setTitle("Valorant Strat Planner v0.1");

        //give stratEditor object a reference to the app controller
        stratEditor.setAppController(appController);

        //give appController references to scenes and such
        appController.setStratEditor(stratEditor);
        appController.setStratEditorScene(stratEditorScene);
        appController.setStage(stage);

        //set the initial scene
        stage.setScene(stratEditorScene);
        stratEditor.setMapImage(StratEditor.Map.BIND);
        stratEditor.setup();

        //must be final call in the function, tells JavaFX to start the app
        stage.show();
    }
}
