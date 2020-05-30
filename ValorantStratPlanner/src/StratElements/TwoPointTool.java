package StratElements;

import ElementDecorators.Icon;
import ElementDecorators.ToolIcon;
import Records.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.json.JSONObject;

import java.util.ArrayList;

public class TwoPointTool extends TwoPointStratElement{
    private Color color;
    private String tool;
    private final double size = 30;

    /**
     * default constructor
     * @param color - the color to use when drawing the shapes that make up this tool
     * @param tool - the name of the tool
     */
    public TwoPointTool(Color color, String tool){
        this.tool = tool;
        this.color = color;
        setupIcon();
    }

    /**
     * alternate constructor that defaults color to Color.YELLOW
     * @param tool - the name of the tool
     */
    public TwoPointTool(String tool){
        this(Color.YELLOW, tool);
    }

    /**
     * alternate constructor that builds the object from a JSONObject
     * @param root - the JSONObject describing this one
     */
    public TwoPointTool(JSONObject root){
        importFromJSON(root);
        setupIcon();
    }

    /**
     * helper method to setup the icon decorator
     */
    private void setupIcon(){
        ToolIcon icon = new ToolIcon(tool);
        icon.setParent(this);
        decorators.add(icon);
    }

    /**
     * converts this object to a JSON string
     * implementation of method defined in StratElement
     * @return - the JSONObject representing this object
     */
    public JSONObject toJSON(){
        JSONObject root = new JSONObject();
        root.put("type", "tool");
        root.put("color", color);
        root.put("tool", tool);
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
        color = Color.web(root.getString("color"));
        tool = root.getString("tool");
    }

    /**
     * implementation of the draw method defined in the abstract class TwoPointStratElement
     * draws the character ability in the given GraphicsContext
     * @param gc - the GraphicsContext in which to draw the character ability
     */
    public void draw(GraphicsContext gc){
        drawDecorators(gc);
        gc.setStroke(color);
        Point offsetStart = getStartOffsetByRadius(size);
        gc.strokeLine(offsetStart.x, offsetStart.y, x2, y2);
    }
}
