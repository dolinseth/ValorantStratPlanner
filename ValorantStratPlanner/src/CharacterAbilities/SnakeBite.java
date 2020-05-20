package CharacterAbilities;

import DataLayer.DataController;
import StratElements.OnePointStratElement;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SnakeBite extends OnePointStratElement {
    private final double radius = 20;

    public void draw(GraphicsContext gc){
        SimpleOnePointAbility.drawIcon(gc, Color.DARKGRAY, x, y, radius, DataController.Ability.SNAKE_BITE);
    }
}
