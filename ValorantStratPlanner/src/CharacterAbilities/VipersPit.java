package CharacterAbilities;

import DataLayer.DataController;
import StratElements.OnePointStratElement;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class VipersPit extends OnePointStratElement {
    private final double radius = 60;

    public void draw(GraphicsContext gc){
        SimpleOnePointAbility.drawIcon(gc, Color.DARKGRAY, x, y, radius, DataController.Ability.VIPERS_PIT);
    }
}
