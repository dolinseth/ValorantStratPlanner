package CharacterAbilities;

import DataLayer.DataController;
import StratElements.OnePointStratElement;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BarrierOrb extends OnePointStratElement {
    private final double radius = 15;

    public void draw(GraphicsContext gc){
        SimpleOnePointAbility.drawIcon(gc, Color.LIGHTYELLOW, x, y, radius, DataController.Ability.BARRIER_ORB);
    }
}
