package StratElements;

import javafx.scene.canvas.GraphicsContext;

public abstract class TwoPointStratElement extends StratElement{
    protected double x1, y1, x2, y2;

    public void setCoords(double x1, double y1, double x2, double y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public void setStart(double x, double y){
        x1 = x;
        y1 = y;
    }

    public void setEnd(double x, double y){
        x2 = x;
        y2 = y;
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
