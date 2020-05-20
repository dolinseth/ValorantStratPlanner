package Shapes;

import DataLayer.DataController;
import Main.AppController;
import javafx.scene.canvas.GraphicsContext;

public class AbilityIcon {
    public static void draw(GraphicsContext gc, DataController.Ability ability, double x, double y){
        gc.drawImage(AppController.getInstance().getData().getAbilityImage(ability), x, y, 15, 15);
    }

    public static void draw(GraphicsContext gc, String ability, double x, double y){
        gc.drawImage(AppController.getInstance().getData().getAbilityImage(ability), x, y, 15, 15);
    }
}
