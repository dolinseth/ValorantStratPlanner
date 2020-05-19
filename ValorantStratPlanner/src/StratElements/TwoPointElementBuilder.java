package StratElements;

import StratElements.TwoPointStratElement;
import javafx.scene.input.MouseEvent;

public class TwoPointElementBuilder<T extends TwoPointStratElement> {
    private double x1, y1, x2, y2;

    public void startClick(MouseEvent startEvent){
        x1 = startEvent.getX();
        y1 = startEvent.getY();
    }

    public void endClick(MouseEvent endEvent){
        x2 = endEvent.getX();
        y2 = endEvent.getY();
    }

    public void formatElement(T element){
        element.setCoords(x1, y1, x2, y2);
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
