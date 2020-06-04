package ElementDecorators;

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
        deltaTierWidth = root.getDouble("deltaTierWidth");
        startOffset = root.getDouble("startOffset");
    }

    /**
     * implementation of the draw method defined in the abstract class ElementDecorator
     * draws the tiered trapezoid using the given GraphicsContext
     * @param gc - the GraphicsContext in which to draw the tiered trapezoid
     */
    public void draw(GraphicsContext gc){
        // TODO implement this shit
    }
}
