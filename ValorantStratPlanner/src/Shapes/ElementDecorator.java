package Shapes;

import StratElements.TwoPointStratElement;
import javafx.scene.paint.Color;

public abstract class ElementDecorator extends TwoPointStratElement {
    public enum Type {END_POINT, START_POINT, END_EXTENDER, START_EXTENDER, START_TO_END};
    protected Type type;
    TwoPointStratElement parent;
    protected double alpha;
    protected Color color;

    public void setCoords(double x, double y){
        if(type == Type.END_POINT){
            x1 = parent.getX2();
            y1 = parent.getY2();
        }
        else if(type == Type.START_POINT){
            x1 = parent.getX1();
            y1 = parent.getY1();
        }
        else if(type == Type.START_EXTENDER){
            x1 = parent.getX1();
            y1 = parent.getY1();
            x2 = x;
            y2 = y;
        }
        else if(type == Type.END_EXTENDER){
            x1 = parent.getX2();
            y1 = parent.getY2();
            x2 = x;
            y2 = y;
        }
        else if(type == Type.START_TO_END){
            x1 = parent.getX1();
            y1 = parent.getY1();
            x2 = parent.getX2();
            y2 = parent.getY2();
        }
    }

    public void setCoordsFromParent(){
        if(type == Type.END_POINT || type == Type.END_EXTENDER){
            x1 = parent.getX2();
            y1 = parent.getY2();
        }
        else if(type == Type.START_POINT || type == Type.START_EXTENDER){
            x1 = parent.getX1();
            y1 = parent.getY1();
        }
        else if(type == Type.START_TO_END){
            x1 = parent.getX1();
            y1 = parent.getY1();
            x2 = parent.getX2();
            y2 = parent.getY2();
        }
    }

    public void setParent(TwoPointStratElement parent){
        this.parent = parent;
    }
}
