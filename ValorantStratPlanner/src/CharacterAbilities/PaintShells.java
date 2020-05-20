package CharacterAbilities;

import DataLayer.DataController;
import StratElements.Arrow;
import StratElements.TwoPointStratElement;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PaintShells extends TwoPointStratElement {
    public final double radius = 15;

    public void draw(GraphicsContext gc){
        SimpleTwoPointAbility.drawIcon(gc, Color.PURPLE, x1, y1, x2, y2, radius, DataController.Ability.PAINT_SHELLS);
    }
}
