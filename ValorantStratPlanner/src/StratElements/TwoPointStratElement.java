package StratElements;

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
}
