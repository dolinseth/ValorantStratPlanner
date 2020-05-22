package Shapes;

import DataLayer.DataController;
import Main.AppController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class AbilityIcon extends Shape{
    private double x, y;
    private String ability;
    private final double size = 20;

    public AbilityIcon(String ability) {
        this.ability = ability;
    }

    public AbilityIcon(DataController.Ability ability) {
        this.ability = ability.toString();
    }

    public void setCoords(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void draw(GraphicsContext gc){
        gc.drawImage(AppController.getInstance().getData().getAbilityImage(ability), x - size/2, y - size/2, size, size);
        gc.setStroke(Color.YELLOW);
        gc.strokeOval(x - size / 2, y - size / 2, size, size);
    }
}
