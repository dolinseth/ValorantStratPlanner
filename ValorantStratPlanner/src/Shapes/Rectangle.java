package Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends ElementDecorator {
    //draws a rectangle that has the given width and runs the length of the vector between the endpoints
    private double width;

    /**
     * default constructor
     * @param width - the width of the rectangle
     * @param color - the color of the rectangle
     * @param alpha - the alpha value to use when drawing the rectangle
     * @param type - the ElementDecorator.Type of this decorator
     */
    public Rectangle(double width, Color color, double alpha, Type type){
        this.width = width;
        this.color = color;
        this.alpha = alpha;
        this.type = type;
    }

    /**
     * implementation of the draw method defined in the abstract class ElementDecorator
     * draws the rectangle using the given GraphicsContext
     * @param gc - the GraphicsContext in which to draw the rectangle
     */
    public void draw(GraphicsContext gc){
        double oldWidth = gc.getLineWidth();
        gc.setLineWidth(width);
        gc.setStroke(color);
        gc.strokeLine(x1, y1, x2, y2);
        gc.setLineWidth(oldWidth);
    }
}
