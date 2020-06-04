package ElementDecorators;

import Records.Point;
import StratElements.TwoPointStratElement;
import javafx.scene.paint.Color;
import org.json.JSONObject;

public abstract class ElementDecorator extends TwoPointStratElement {
    public enum Type {END_POINT, START_POINT, END_EXTENDER, START_EXTENDER, START_TO_END};
    protected Type type;
    TwoPointStratElement parent;
    protected double alpha;
    protected DecoratorUpdater updater;
    protected boolean isVisible = true;

    /**
     * helper method for deserialization, sets the ElementDecorator.Type property based on JSONObject
     * @param root - the JSONObject that describes the ElementDecorator.Type of this object
     */
    @Override
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
        color = Color.web(root.getString("color"));
        setCoordsFromJSON(root);
    }

    @Override
    public void insertProperties(JSONObject root){
        insertCoords(root);
        root.put("alpha", alpha);
        switch(type){
            case END_POINT:
                root.put("eldtype", "end_point");
                break;
            case START_POINT:
                root.put("eldtype", "start_point");
                break;
            case START_EXTENDER:
                root.put("eldtype", "start_extender");
                break;
            case END_EXTENDER:
                root.put("eldtype", "end_extender");
                break;
            case START_TO_END:
                root.put("eldtype", "start_to_end");
                break;
        }
        root.put("color", color);
    }

    /**
     * sets the coordinates based on the parent object and the type
     */
    public void updateElement(){
        if(type == Type.END_POINT || type == Type.END_EXTENDER){
            setStart(parent.getEnd());
        }
        else if(type == Type.START_POINT || type == Type.START_EXTENDER){
            setStart(parent.getStart());
        }
        else if(type == Type.START_TO_END){
            setStart(parent.getStart());
            setEnd(parent.getEnd());
        }
        if(updater != null){
            updater.update();
        }
    }

    /*
    GETTERS AND SETTERS
     */

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public void setParent(TwoPointStratElement parent){
        this.parent = parent;
    }

    public TwoPointStratElement getParent(){
        return parent;
    }

    public void setUpdater(DecoratorUpdater updater) {
        this.updater = updater;
    }
}
