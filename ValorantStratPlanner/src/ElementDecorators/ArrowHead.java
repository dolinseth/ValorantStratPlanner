package ElementDecorators;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.json.JSONObject;

public class ArrowHead extends ElementDecorator {
    private final double lineLength = 10;
    private final double theta = Math.PI/6;

    /**
     * default constructor, does nothing
     */
    public ArrowHead(){
        color = Color.YELLOW;
        type = Type.START_TO_END;
    }

    /**
     * default constructor
     * @param x1 - x coordinate of the start point of the arrow
     * @param y1 - y coordinate of the start point of the arrow
     * @param x2 - x coordinate of the end point of the arrow
     * @param y2 - y coordinate of the end point of the arrow
     * @param color - the color of the arrow
     */
    public ArrowHead(double x1, double y1, double x2, double y2, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        color = color;
        type = Type.START_TO_END;
    }

    /**
     * alternate constructor that builds the object from a JSONObject
     * @param root - the JSONObject to get properties from
     */
    public ArrowHead(JSONObject root){
        importFromJSON(root);
    }

    /**
     * converts this object to a JSON string
     * implementation of method defined in StratElement
     * @return - the JSONObject representing the object
     */
    public JSONObject toJSON(){
        JSONObject root = new JSONObject();
        root.put("type", "ArrowHead");
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

    /**
     * implementation of draw method as defined in the abstract class StratElement
     * draws the arrowhead in the given GraphicsContext
     * @param gc - the GraphicsContext in which to draw the arrowhead
     */
    public void draw(GraphicsContext gc){
        double initAngle = Math.atan2((y1 - y2), (x1 - x2));
        gc.setStroke(color);
        gc.strokeLine(x2, y2, x2 + Math.cos(initAngle - theta)* lineLength, y2 + Math.sin(initAngle - theta)* lineLength);
        gc.strokeLine(x2, y2, x2 + Math.cos(initAngle + theta)* lineLength, y2 + Math.sin(initAngle + theta)* lineLength);
    }
}
