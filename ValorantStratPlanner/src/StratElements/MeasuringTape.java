package StratElements;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.json.JSONObject;

public class MeasuringTape extends Line{
    private Color color = Color.YELLOW;
    private final double pixelsRunPerSecond = 41.505;
    private final double pixelsWalkedPerSecond = 24.615;


    /**
     * default constructor, does nothing
     */
    public MeasuringTape(){}

    /**
     * alternate constructor that builds the object from a JSONObject
     * @param root - the JSONObject to get properties from
     */
    public MeasuringTape(JSONObject root){
        super(root);
    }

    public void draw(GraphicsContext gc){
        gc.setStroke(color);
        gc.strokeLine(x1, y1, x2, y2);
        gc.strokeText("Distance: " + getLength() + "\nTime to run: " + (getLength() / pixelsRunPerSecond) + "\nTime to walk: " + (getLength() / pixelsWalkedPerSecond), x2 + 10, y2 - 10);
    }

    @Override
    public JSONObject toJSON(){
        JSONObject root = new JSONObject();
        root.put("type", "MeasuringTape");
        root.put("color", color.toString());
        insertProperties(root);
        return root;
    }

    private double getLength(){
        double dx = x2 - x1;
        double dy = y2 - y1;
        //using dx*dx instead of Math.pow(dx, 2) because it's faster
        return Math.sqrt(dx*dx + dy*dy);
    }
}
