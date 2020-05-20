package CharacterAbilities;

import DataLayer.DataController;
import StratElements.TwoPointStratElement;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ReconBolt extends TwoPointStratElement {
    private final double radius = 60;

    public void draw(GraphicsContext gc){
        SimpleTwoPointAbility.drawIcon(gc, Color.DARKGRAY, x1, y1, x2, y2, radius, DataController.Ability.RECON_BOLT);
    }
}
