package CharacterAbilities;

import DataLayer.DataController;
import StratElements.TwoPointStratElement;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Cloudburst extends TwoPointStratElement {
    private final double radius = 15;

    public void draw(GraphicsContext gc){
        SimpleTwoPointAbility.drawIcon(gc, Color.LIGHTGRAY, x1, y1, x2, y2, radius, DataController.Ability.CLOUDBURST);
    }

}
