package StratElements;

public abstract class OnePointStratElement extends StratElement{
    protected double x, y;

    /**
     * default constructor, does nothing
     */
    public OnePointStratElement(){}

    /**
     * sets the coordinates of this object to the given (x,y) pair
     * @param x - the new x coordinate
     * @param y - the new y coordinate
     */
    public void setCoords(double x, double y){
        this.x = x;
        this.y = y;
    }
}
