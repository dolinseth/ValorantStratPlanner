package CharacterAbilities;

import DataLayer.DataController;
import Main.AppController;
import StratElements.OnePointStratElement;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class SimpleOnePointAbility extends OnePointStratElement {
    public static void drawIcon(GraphicsContext gc, Color color, double x, double y, double radius, DataController.Ability ability){
        gc.setFill(color);
        gc.fillOval(x - radius / 2, y - radius / 2, radius, radius);
        gc.drawImage(AppController.getInstance().getData().getAbilityImage(ability), x, y, radius, radius);
    }
}
