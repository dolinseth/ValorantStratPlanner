package CharacterAbilities;

import DataLayer.DataController;
import StratElements.OnePointStratElement;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class NeuralTheft extends OnePointStratElement {
    public final double radius = 10;

    public void draw(GraphicsContext gc){
        SimpleOnePointAbility.drawIcon(gc, Color.LIGHTGRAY, x, y, radius, DataController.Ability.NEURAL_THEFT);
    }
}
