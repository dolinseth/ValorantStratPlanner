package ElementDecorators;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.json.JSONObject;

public class TextBox extends ElementDecorator{
    String formatString;
    String curText;
    private final double xOffset = 10;
    private final double yOffset = 10;

    /**
     * default constructor
     * @param formatString - the string to use in the call to String.format when the text needs to be updated
     * @param type - the ElementDecorator.Type of this object
     */
    public TextBox(String formatString, Type type){
        this.formatString = formatString;
        this.type = type;
        color = Color.YELLOW;
    }

    /**
     * alternate constructor that builds the object from a JSONObject
     * @param root - the JSONObject describing this one
     */
    public TextBox(JSONObject root){
        importFromJSON(root);
    }

    /**
     * called when the text needs to have new variadic parameters passed to it
     * @param args - the arguments to be passed to String.format to update the text that is displayed
     */
    public void updateText(Object... args){
        curText = String.format(formatString, args);
    }

    /**
     * implementation of the abstract draw method defined in StratElement
     * @param gc - the GraphicsContext in which to draw the element
     */
    public void draw(GraphicsContext gc){
        gc.setStroke(color);
        gc.strokeText(curText, x1 + xOffset, y1 + yOffset);
    }

    /**
     * helper method for serialization, turns this object into a JSONObject containing all its properties
     * @return - the JSONObject describing this one
     */
    public JSONObject toJSON(){
        JSONObject root = new JSONObject();
        insertProperties(root);
        root.put("type", "TextBox");
        root.put("formatStr", formatString);
        root.put("curText", curText);
        return root;
    }

    /**
     * helper method for deserialization, sets the properties of this object from the given JSONObject
     * @param root - the JSONObject describing this one
     */
    public void importFromJSON(JSONObject root){
        setPropertiesFromJSON(root);
        formatString = root.getString("formatStr");
        curText = root.getString("curText");
    }
}
