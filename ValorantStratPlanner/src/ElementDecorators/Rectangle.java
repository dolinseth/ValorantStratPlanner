package ElementDecorators;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.json.JSONObject;

public class Rectangle extends ElementDecorator {
    //draws a rectangle that has the given width and runs the length of the vector between the endpoints
    private double width;

    /**
     * default constructor
     * @param width - the width of the rectangle
     * @param color - the color of the rectangle
     * @param alpha - the alpha value to use when drawing the rectangle
     * @param type - the ElementDecorator.Type of this decorator
     */
    public Rectangle(double width, Color color, double alpha, Type type){
        this.width = width;
        this.color = color;
        this.alpha = alpha;
        this.type = type;
    }

    /**
     * alternate constructor that builds the object from a JSONObject
     * @param root - the JSONObject to get properties from
     */
    public Rectangle(JSONObject root){
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
        root.put("width", width);
        root.put("type", "Rectangle");
        return root;
    }

    /**
     * imports the properties of this object from a JSON string
     * implementation of method defined in StratElement
     * @param root - the JSONObject representing the object
     */
    public void importFromJSON(JSONObject root){
        setPropertiesFromJSON(root);
        width = root.getDouble("width");
    }

    /**
     * implementation of the draw method defined in the abstract class ElementDecorator
     * draws the rectangle using the given GraphicsContext
     * @param gc - the GraphicsContext in which to draw the rectangle
     */
    public void draw(GraphicsContext gc){
        if(isVisible) {
            double parallelAngle = Math.atan2(y2 - y1, x2 - x1);
            double perpendicularAngle = parallelAngle + Math.PI/2;
            //calculate these only once per frame
            double cosPar = Math.cos(parallelAngle);
            double sinPar = Math.sin(parallelAngle);
            double cosPer = Math.cos(perpendicularAngle);
            double sinPer = Math.sin(perpendicularAngle);
            double length = getLength();

            //create the SVG string depending on type
            String path = "";
            if(type == Type.START_TO_END || type == Type.END_EXTENDER || type == Type.START_EXTENDER){
                path = String.format("M%f %f l%f %f l%f %f l%f %f l%f %f", x1, y1, cosPer*(width / 2), sinPer*(width / 2), cosPar*length, sinPar*length, -cosPer*width, -sinPer*width, -cosPar*length, -sinPar*length);
            }
            else{
                path = String.format("M%f %f m%f %f l%f %f l%f %f l%f %f l%f %f", x1, y1, -cosPar*(length / 2), -sinPar*(length / 2), cosPer*(width / 2), sinPer*(width / 2), cosPar*length, sinPar*length, -cosPer*width, -sinPer*width, -cosPar*length, -sinPar*length);
            }

            //draw the SVG path
            gc.beginPath();
            gc.appendSVGPath(path);
            gc.closePath();
            double oldAlpha = gc.getGlobalAlpha();
            gc.setGlobalAlpha(alpha);
            gc.setFill(color);
            gc.fill();
            gc.setGlobalAlpha(oldAlpha);

            drawDecorators(gc);
        }
    }
}
