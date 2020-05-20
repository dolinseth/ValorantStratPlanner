package CharacterAbilities;

import DataLayer.DataController;
import StratElements.TwoPointStratElement;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DarkCover extends TwoPointStratElement {
    private final double radius = 20;

    public void draw(GraphicsContext gc){
        SimpleTwoPointAbility.drawIcon(gc, Color.DARKGRAY, x1, y1, x2, y2, radius, DataController.Ability.DARK_COVER);
    }
}
