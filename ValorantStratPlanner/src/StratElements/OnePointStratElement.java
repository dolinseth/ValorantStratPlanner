package StratElements;

public abstract class OnePointStratElement extends StratElement{
    protected double x, y;

    public OnePointStratElement(){}

    public void setCoords(double x, double y){
        this.x = x;
        this.y = y;
    }
}
