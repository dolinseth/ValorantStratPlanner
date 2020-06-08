package ElementDecorators;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.json.JSONObject;

public class Line extends ElementDecorator{
    /**
     * default constructor
     * @param color - the color to use when drawing the line
     * @param type - the ElementDecorator.Type of this object
     */
    public Line(Color color, Type type){
        this.color = color;
        this.type = type;
    }

    /**
     * alternate constructor that builds the object from a JSONObject
     * @param root - the JSONObject to get properties from
     */
    public Line(JSONObject root){
        importFromJSON(root);
    }

    /**
     * converts this object to a JSON string
     * implementation of method defined in StratElement
     * @return - the JSONObject representing the object
     */
    public JSONObject toJSON(){
        JSONObject root = new JSONObject();
        insertProperties(root);
        root.put("type", "Line");

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
     * implementation of the draw method defined in the abstract class ElementDecorator
     * draws the line using the given GraphicsContext
     * @param gc - the GraphicsContext in which to draw the line
     */
    public void draw(GraphicsContext gc){
        if(isVisible) {
            gc.setStroke(color);
            gc.strokeLine(x1, y1, x2, y2);

            drawDecorators(gc);
        }
    }
}
