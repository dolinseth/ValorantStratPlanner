package CharacterAbilities;

import DataLayer.DataController;
import Main.AppController;
import StratElements.Arrow;
import StratElements.TwoPointStratElement;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class SimpleTwoPointAbility extends TwoPointStratElement {
    protected Arrow arrow;

    public static void drawIcon(GraphicsContext gc, Color color, double x1, double y1, double x2, double y2, double radius, DataController.Ability ability){
        gc.setFill(color);
        gc.fillOval(x2 - radius / 2, y2 - radius / 2, radius, radius);
        gc.drawImage(AppController.getInstance().getData().getAbilityImage(ability), x2, y2, radius, radius);
        Arrow arrow = new Arrow(x1, y1, x2, y2, Color.YELLOW);
        arrow.draw(gc);
    }

}
