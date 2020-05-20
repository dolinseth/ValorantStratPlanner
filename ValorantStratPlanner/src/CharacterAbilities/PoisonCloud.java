package CharacterAbilities;

import DataLayer.DataController;
import StratElements.OnePointStratElement;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PoisonCloud extends OnePointStratElement {
    private final double radius = 60;

    public void draw(GraphicsContext gc){
        SimpleOnePointAbility.drawIcon(gc, Color.DARKGRAY, x, y, radius, DataController.Ability.POISON_CLOUD);
    }
}
