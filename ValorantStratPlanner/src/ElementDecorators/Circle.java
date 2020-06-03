package ElementDecorators;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.json.JSONObject;

public class Circle extends ElementDecorator {
    private double radius;

    /**
     * default constructor
     * @param radius - the radius of the circle
     * @param color - the color to fill the circle with
     * @param alpha - the alpha value to use when drawing the circle
     * @param type - the ElementDecorator.Type of this decorator
     */
    public Circle(double radius, Color color, double alpha, Type type) {
        this.radius = radius;
        color = color;
        this.alpha = alpha;
        this.type = type;
    }

    /**
     * alternate constructor that builds the object from a JSONObject
     * @param root - the JSONObject to get properties from
     */
    public Circle(JSONObject root){
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
        root.put("type", "Circle");
        root.put("radius", radius);
        return root;
    }

    /**
     * imports the properties of this object from a JSON string
     * implementation of method defined in StratElement
     * @param root - the JSONObject representing the object
     */
    public void importFromJSON(JSONObject root){
        setPropertiesFromJSON(root);
        radius = root.getDouble("radius");
    }

    /**
     * implementation of the draw method defined in the abstract class ElementDecorator
     * draws the circle using the given GraphicsContext
     * @param gc - the GraphicsContext in which to draw the circle
     */
    public void draw(GraphicsContext gc){
        if(x1 != 0 || y1 != 0) {
            double oldAlpha = gc.getGlobalAlpha();
            gc.setGlobalAlpha(alpha);
            gc.setFill(color);
            gc.fillOval(x1 - radius / 2, y1 - radius / 2, radius, radius);
            gc.setGlobalAlpha(oldAlpha);
        }
    }
}
