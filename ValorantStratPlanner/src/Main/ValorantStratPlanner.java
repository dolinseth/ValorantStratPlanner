import StratEditor.StratEditor;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class ValorantStratPlanner {
    private Stage stage;
    private AppController appController;

    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage stage){
        appController = AppController.getInstance();
        appController.setStage(stage);

        Parent stratEditorRoot = null;
        FXMLLoader stratEditorLoader = new FXMLLoader(StratEditor.class.getResource("StratEditor.fxml"));
        try{
            stratEditorRoot = stratEditorLoader.load();
            StratEditor editor = stratEditorLoader.getController();
        } catch (IOException e){
            System.out.println("Failed to load fxml");
            System.exit(1);
        }

        stage.setTitle("Valorant Strat Planner v0.1");
    }
}
