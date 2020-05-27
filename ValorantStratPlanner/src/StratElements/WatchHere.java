package StratElements;

import ElementDecorators.ElementDecorator;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.json.JSONObject;

public class WatchHere extends TwoPointStratElement {
    private Color color = Color.YELLOW;
    private final double radius = 20;

    /**
     * default constructor, does nothing
     */
    public WatchHere(){}

    /**
     * alternate constructor that builds the object from a JSONObject
     * @param root - the JSONObject to get properties from
     */
    public WatchHere(JSONObject root){
        setPropertiesFromJSON(root);
    }

    /**
     * implementation of the draw method defined in the abstract class TwoPointStratElement
     * draws the WatchHere in the given GraphicsContext
     * @param gc - the GraphicsContext in which to draw the WatchHere
     */
    public void draw(GraphicsContext gc){
        gc.setStroke(color);
        gc.strokeOval(x1 - radius / 2, y1 - radius / 2, radius, radius);
        gc.strokeLine(x1, y1, x2, y2);
        decorators.forEach(ElementDecorator::setCoordsFromParent);
        decorators.forEach(d -> d.draw(gc));
    }

    /**
     * converts this object to a JSON string
     * implementation of method defined in StratElement
     * @return - the JSONObject representing the object
     */
    public JSONObject toJSON(){
        JSONObject root = new JSONObject();
        root.put("type", "WatchHere");
        root.put("color", color.toString());
        insertProperties(root);
        return root;
    }

    /**
     * imports the properties of this object from a JSON string
     * implementation of method defined in StratElement
     * @param root - the JSONObject representing the object
     */
    public void importFromJSON(JSONObject root){
        setPropertiesFromJSON(root);
    }
}
