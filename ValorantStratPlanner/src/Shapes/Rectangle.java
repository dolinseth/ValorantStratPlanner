package Shapes;

import com.sun.prism.Graphics;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Shape{
    //draws a rectangle that has the given width and runs the length of the vector between the endpoints
    private double width;

    public Rectangle(double width, Color color, double alpha){
        this.width = width;
        this.color = color;
        this.alpha = alpha;
    }

    public void draw(GraphicsContext gc){
        double oldWidth = gc.getLineWidth();
        gc.setLineWidth(width);
        gc.setStroke(color);
        gc.strokeLine(x1, y1, x2, y2);
        gc.setLineWidth(oldWidth);
    }
}
