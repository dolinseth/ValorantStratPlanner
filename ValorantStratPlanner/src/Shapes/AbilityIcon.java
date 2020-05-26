package Shapes;

import DataLayer.DataController;
import Main.AppController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class AbilityIcon extends ElementDecorator {
    private String ability;
    private final double size = 30;

    /**
     * default constructor
     * @param ability - string containing the name of the ability
     * @param type - the ElementDecorator.Type of this decorator
     */
    public AbilityIcon(String ability, Type type) {
        this.ability = ability;
        this.type = type;
        color = Color.YELLOW;
    }

    /**
     * implementation of the draw method defined in the abstract class ElementDecorator
     * draws the icon using the given GraphicsContext
     * @param gc - the GraphicsContext in which to draw the icon
     */
    public void draw(GraphicsContext gc){
        gc.drawImage(AppController.getInstance().getData().getAbilityImage(ability), x1 - size/2, y1 - size/2, size, size);
        gc.setStroke(color);
        gc.strokeOval(x1 - size / 2, y1 - size / 2, size, size);
    }
}
