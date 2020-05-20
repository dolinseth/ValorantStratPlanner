package CharacterAbilities;

import DataLayer.DataController;
import Main.AppController;
import StratElements.OnePointStratElement;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SkySmoke extends OnePointStratElement {
    private final double radius = 30;

    public void draw(GraphicsContext gc){
        SimpleOnePointAbility.drawIcon(gc, Color.LIGHTGRAY, x, y, radius, DataController.Ability.SKY_SMOKE);
    }
}
