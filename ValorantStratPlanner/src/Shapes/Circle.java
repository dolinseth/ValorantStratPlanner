package Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle {
    public static void draw(GraphicsContext gc, double x, double y, double radius, Color color){
        gc.setStroke(color);
        gc.strokeOval(x - radius / 2, y - radius / 2, radius, radius);
    }
}
