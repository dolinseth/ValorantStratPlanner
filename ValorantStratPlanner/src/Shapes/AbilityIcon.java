package Shapes;

import DataLayer.DataController;
import Main.AppController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class AbilityIcon extends ElementDecorator {
    private String ability;
    private final double size = 30;

    public AbilityIcon(String ability, Type type) {
        this.ability = ability;
        this.type = type;
        color = Color.YELLOW;
    }

    public AbilityIcon(DataController.Ability ability) {
        this.ability = ability.toString();
    }

    public void draw(GraphicsContext gc){
        gc.drawImage(AppController.getInstance().getData().getAbilityImage(ability), x1 - size/2, y1 - size/2, size, size);
        gc.setStroke(color);
        gc.strokeOval(x1 - size / 2, y1 - size / 2, size, size);
    }
}
