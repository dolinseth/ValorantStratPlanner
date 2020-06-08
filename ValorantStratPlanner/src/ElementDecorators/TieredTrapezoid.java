package ElementDecorators;

import Records.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.json.JSONObject;

public class TieredTrapezoid extends ElementDecorator {
    private int numTiers;
    private double firstTierWidth; // the dimension that runs perpendicular to the stacking of the tiers
    private double tierLength; // the dimension that runs parallel to the stacking of the tiers
    private double deltaTierWidth;
    private double startOffset;

    /**
     * default constructor
     * @param numTiers - the number of tiers (rectangles) that will be used to construct the trapezoid
     * @param firstTierWidth - the width (perpendicular to the height of the trapezoid) of the first rectangle
     * @param tierLength - the length (parallel to the height of the trapezoid) of each rectangle
     * @param deltaTierWidth - the amount that the width should increase by on each tier
     * @param startOffset - the distance offset from the start point to the first rectangle
     * @param color - the color to draw the rectangles with
     * @param alpha - the alpha value to use when drawing the rectangles
     * @param type - the ElementDecorator.Type of this object
     */
    public TieredTrapezoid(int numTiers, double firstTierWidth, double tierLength, double deltaTierWidth, double startOffset, Color color, double alpha, Type type) {
        this.numTiers = numTiers;
        this.firstTierWidth = firstTierWidth;
        this.tierLength = tierLength;
        this.deltaTierWidth = deltaTierWidth;
        this.startOffset = startOffset;
        this.color = color;
        this.alpha = alpha;
        this.type = type;
    }

    /**
     * alternate constructor that builds this object from a JSONObject
     * @param root - the JSONObject describing this one
     */
    public TieredTrapezoid(JSONObject root){
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
        root.put("numTiers", numTiers);
        root.put("firstTierWidth", firstTierWidth);
        root.put("tierLength", tierLength);
        root.put("deltaTierWidth", deltaTierWidth);
        root.put("startOffset", startOffset);
        return root;
    }

    /**
     * imports the properties of this object from a JSON string
     * implementation of method defined in StratElement
     * @param root - the JSONObject representing the object
     */
    public void importFromJSON(JSONObject root){
        setPropertiesFromJSON(root);
        numTiers = root.getInt("numTiers");
        firstTierWidth = root.getDouble("firstTierWidth");
        tierLength = root.getDouble("tierLength");
        deltaTierWidth = root.getDouble("deltaTierWidth");
        startOffset = root.getDouble("startOffset");
    }

    /**
     * implementation of the draw method defined in the abstract class ElementDecorator
     * draws the tiered trapezoid using the given GraphicsContext
     * @param gc - the GraphicsContext in which to draw the tiered trapezoid
     */
    public void draw(GraphicsContext gc){
        double parallelAngle = Math.atan2(y2 - y1, x2 - x1);
        double perpendicularAngle = Math.PI/2 + parallelAngle;
        //calculate these only once per frame to improve performance
        double cosPar = Math.cos(parallelAngle);
        double sinPar = Math.sin(parallelAngle);
        double cosPer = Math.cos(perpendicularAngle);
        double sinPer = Math.sin(perpendicularAngle);

        Point start = new Point(x1 + cosPar*startOffset, y1 + sinPar*startOffset);
        StringBuilder sb = new StringBuilder(); // because it makes string concatenation waaaaay faster and this needs to happen 60 times a second
                                                // for every one of these that is being drawn, as well as all the other stuff that's being drawn

        //build the SVG path
        sb.append(String.format("M%f %f l%f %f l%f %f", start.x, start.y, cosPer*(firstTierWidth / 2), sinPer*(firstTierWidth/2), cosPar*tierLength, sinPar*tierLength));
        for(int i = 0; i < numTiers - 1; i++){
            sb.append(String.format("l%f %f l%f %f", cosPer*(deltaTierWidth / 2), sinPer*(deltaTierWidth / 2), cosPar*tierLength, sinPar*tierLength));
        }
        sb.append(String.format("l%f %f", -cosPer*(firstTierWidth + deltaTierWidth*(numTiers - 1)), -sinPer*(firstTierWidth + deltaTierWidth*(numTiers - 1))));
        for(int i = 0; i < numTiers - 1; i++){
            sb.append(String.format("l%f %f l%f %f", -cosPar*tierLength, -sinPar*tierLength, cosPer*(deltaTierWidth / 2), sinPer*(deltaTierWidth / 2)));
        }
        sb.append(String.format("l%f %f", -cosPar*tierLength, -sinPar*tierLength));

        //draw the path
        gc.beginPath();
        gc.appendSVGPath(sb.toString());
        gc.closePath();
        gc.setFill(color);
        double oldAlpha = gc.getGlobalAlpha();
        gc.setGlobalAlpha(alpha);
        gc.fill();
        gc.setGlobalAlpha(oldAlpha);

        drawDecorators(gc);
    }
}
