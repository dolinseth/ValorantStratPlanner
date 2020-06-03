package ElementDecorators;

import Records.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FilledArc extends ElementDecorator{
    private double innerRadius, outerRadius;
    private double arcWidth; // measured in radians for compatibility with Math package trig functions

    /**
     * default constructor
     * @param innerRadius - the inner radius of the arc
     * @param outerRadius - the outer radius of the arc
     * @param arcWidth - the width of the arc, in radians
     * @param color - the color to draw the arc with
     * @param alpha - the alpha value to use when drawing the arc
     * @param type - the ElementDecorator.Type of this object
     */
    public FilledArc(double innerRadius, double outerRadius, double arcWidth, Color color, double alpha, Type type){
        this.innerRadius = innerRadius;
        this.outerRadius = outerRadius;
        this.arcWidth = arcWidth;
        this.color = color;
        this.alpha = alpha;
        this.type = type;
    }

    /**
     * alternate constructor that builds this object from a JSONObject
     * @param root - the JSONObject to build this one from
     */
    public FilledArc(JSONObject root){
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
        root.put("type", "FilledArc");
        root.put("innerRadius", innerRadius);
        root.put("outerRadius", outerRadius);
        root.put("arcWidth", arcWidth);
        return root;
    }

    /**
     * imports the properties of this object from a JSON string
     * implementation of method defined in StratElement
     * @param root - the JSONObject representing the object
     */
    public void importFromJSON(JSONObject root){
        setPropertiesFromJSON(root);
        innerRadius = root.getDouble("innerRadius");
        outerRadius = root.getDouble("outerRadius");
        arcWidth = root.getDouble("arcWidth");
    }

    /**
     * implementation of the draw method defined in the abstract class ElementDecorator
     * draws the arc using the given GraphicsContext
     * @param gc - the GraphicsContext in which to draw the circle
     */
    public void draw(GraphicsContext gc){
        List<Point> arcPoints = getArcPoints();
        Point inStart = arcPoints.get(0);
        Point inEnd = arcPoints.get(1);
        Point outEnd = arcPoints.get(2);
        Point outStart = arcPoints.get(3);
        String path = String.format("M%f,%f A%f %f 0 0 0 %f %f L%f %f A%f %f 0 0 1 %f %f L %f %f", inStart.x, inStart.y, innerRadius, innerRadius, inEnd.x, inEnd.y, outEnd.x, outEnd.y, outerRadius, outerRadius, outStart.x, outStart.y, inStart.x, inStart.y);

        gc.beginPath();
        gc.appendSVGPath(path);
        gc.closePath();
        gc.setFill(color);
        gc.fill();
    }

    /**
     * returns the four points used to define the arc in the following order:
     * inner start, inner end, outer end, outer start
     * @return - the list of points used to define the arc
     */
    private List<Point> getArcPoints(){
        ArrayList<Point> points = new ArrayList<Point>();
        double sAng = Math.atan2(y2 - y1, x2 - x1) + (arcWidth / 2);
        double eAng = Math.atan2(y2 - y1, x2 - x1) - (arcWidth / 2);
        points.add(new Point(x1 + Math.cos(sAng)*innerRadius, y1 + Math.sin(sAng)*innerRadius));
        points.add(new Point(x1 + Math.cos(eAng)*innerRadius, y1 + Math.sin(eAng)*innerRadius));
        points.add(new Point(x1 + Math.cos(eAng)*outerRadius, y1 + Math.sin(eAng)*outerRadius));
        points.add(new Point(x1 + Math.cos(sAng)*outerRadius, y1 + Math.sin(sAng)*outerRadius));
        return points;
    }
}
