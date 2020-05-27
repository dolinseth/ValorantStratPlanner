package StratElements;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.json.JSONObject;

public abstract class StratElement {
    /**
     * draws the StratElement in the given graphics context
     * to be implemented by any classes extending this one
     * @param gc - the GraphicsContext in which to draw the element
     */
    public abstract void draw(GraphicsContext gc);

    /**
     * serializes the object into a JSON string for saving and restoring
     * to be implemented by any classes extending this one
     * @return - the JSON string representing the object
     */
    public abstract JSONObject toJSON();

    /**
     * Deserializes the object and imports all values from a JSON string for saving and restoring
     * to be implemented by any classes extending this one
     * @param str - the JSON string representing the object
     */
    public abstract void importFromJSON(JSONObject root);
}
