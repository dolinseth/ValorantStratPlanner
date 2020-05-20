package Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line {
    public static void draw(GraphicsContext gc, double x1, double y1, double x2, double y2, Color color){
        gc.setStroke(color);
        gc.strokeLine(x1, y1, x2, y2);
    }
}
