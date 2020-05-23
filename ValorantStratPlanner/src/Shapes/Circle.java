package Shapes;

import StratElements.TwoPointStratElement;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends ElementDecorator {
    private double radius;

    public Circle(double radius, Color color, double alpha, Type type) {
        this.radius = radius;
        this.color = color;
        this.alpha = alpha;
        this.type = type;
    }

    public void draw(GraphicsContext gc){
        if(x2 != 0 || y2 != 0) {
            double oldAlpha = gc.getGlobalAlpha();
            gc.setGlobalAlpha(alpha);
            gc.setFill(color);
            gc.fillOval(x2 - radius / 2, y2 - radius / 2, radius, radius);
            gc.setGlobalAlpha(oldAlpha);
        }
    }
}
