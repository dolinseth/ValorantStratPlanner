package CharacterAbilities;

import DataLayer.DataController;
import Main.AppController;
import StratElements.OnePointStratElement;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class OrbitalStrike extends OnePointStratElement {
    public final double radius = 50;

    public void draw(GraphicsContext gc){
        SimpleOnePointAbility.drawIcon(gc, Color.ORANGERED, x, y, radius, DataController.Ability.ORBITAL_STRIKE);
    }
}
