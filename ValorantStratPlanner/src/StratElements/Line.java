package StratElements;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Line extends TwoPointStratElement{
    private Color color = Color.YELLOW;

    /**
     * default constructor, does nothing
     */
    public Line(){}

    /**
     * default constructor
     * @param x1 - the x coordinate of the start point of the line
     * @param y1 - the y coordinate of the start point of the line
     * @param x2 - the x coordinate of the end point of the line
     * @param y2 - the y coordinate of the end point of the line
     * @param color - the color of the line
     */
    public Line(double x1, double y1, double x2, double y2, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }

    /**
     * implementation of the draw method defined in the abstract class TwoPointStratElement
     * draws the line in the given GraphicsContext
     * @param gc - the GraphicsContext in which to draw the line
     */
    public void draw(GraphicsContext gc){
        gc.setStroke(color);
        gc.strokeLine(x1, y1, x2, y2);
    }

    /*
    GETTERS AND SETTERS
     */

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getY1() {
        return y1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getY2() {
        return y2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }
}
