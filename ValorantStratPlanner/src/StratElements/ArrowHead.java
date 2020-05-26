package StratElements;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ArrowHead extends StratElement{
    private double x1, y1, x2, y2;
    private Color color;
    private final double lineLength = 10;

    /**
     * default constructor
     * @param x1 - x coordinate of the start point of the arrow
     * @param y1 - y coordinate of the start point of the arrow
     * @param x2 - x coordinate of the end point of the arrow
     * @param y2 - y coordinate of the end point of the arrow
     * @param color - the color of the arrow
     */
    public ArrowHead(double x1, double y1, double x2, double y2, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }

    /**
     * implementation of draw method as defined in the abstract class StratElement
     * draws the arrowhead in the given GraphicsContext
     * @param gc - the GraphicsContext in which to draw the arrowhead
     */
    public void draw(GraphicsContext gc){
        double initAngle = Math.atan2((y2 - y1), (x2 - x1));
        gc.setStroke(color);
        gc.strokeLine(x2, y2, x2 + Math.cos(initAngle - Math.PI/12)* lineLength, y2 + Math.sin(initAngle - Math.PI/12)* lineLength);
        gc.strokeLine(x2, y2, x2 + Math.cos(initAngle + Math.PI/12)* lineLength, y2 + Math.sin(initAngle + Math.PI/12)* lineLength);
    }
}
