package ElementDecorators;

import StratElements.TwoPointStratElement;
import javafx.scene.paint.Color;
import org.json.JSONObject;

public abstract class ElementDecorator extends TwoPointStratElement {
    public enum Type {END_POINT, START_POINT, END_EXTENDER, START_EXTENDER, START_TO_END};
    protected Type type;
    TwoPointStratElement parent;
    protected double alpha;
    protected Color color;

    /**
     * helper method for deserialization, sets the ElementDecorator.Type property based on JSONObject
     * @param root - the JSONObject that describes the ElementDecorator.Type of this object
     */
    public void setPropertiesFromJSON(JSONObject root){
        String typeStr = root.getString("eldtype");
        switch(typeStr){
            case "end_point":
                type = Type.END_POINT;
                break;
            case "start_point":
                type = Type.START_POINT;
                break;
            case "start_extender":
                type = Type.START_EXTENDER;
                break;
            case "end_extender":
                type = Type.END_EXTENDER;
                break;
            case "start_to_end":
                type = Type.START_TO_END;
                break;
        }
        alpha = root.getDouble("alpha");
        setCoordsFromJSON(root);
    }

    public void insertProperties(JSONObject root){
        insertCoords(root);
        root.put("alpha", alpha);
        switch(type){
            case END_POINT:
                root.put("type", "end_point");
                break;
            case START_POINT:
                root.put("type", "start_point");
                break;
            case START_EXTENDER:
                root.put("type", "start_extender");
                break;
            case END_EXTENDER:
                root.put("type", "end_extender");
                break;
            case START_TO_END:
                root.put("type", "start_to_end");
                break;
        }
    }

    /**
     * sets the element decorators coordinates, one end is set to the given (x,y) pair
     * other end is set based on the parent's coordinates and the type
     * @param x - the x coordinate to set
     * @param y - the y coordinate to set
     */
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

    /**
     * sets the coordinates based on the parent object and the type
     */
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

    /*
    GETTERS AND SETTERS
     */

    public void setParent(TwoPointStratElement parent){
        this.parent = parent;
    }

    public TwoPointStratElement getParent(){
        return parent;
    }
}
