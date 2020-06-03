package ElementDecorators;

import Main.AppController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.json.JSONObject;

public class ToolIcon extends Icon{
    /**
     * default constructor
     * @param toolName - the name of the tool
     * @param type - the ElementDecorator.Type of this icon
     */
    public ToolIcon(String toolName, Type type){
        this.name = toolName;
        this.type = type;
        color = Color.YELLOW;
    }

    /**
     * alternate constructor that defaults to ElementDecorator.Type.START_POINT
     * @param toolName - the name of the tool
     */
    public ToolIcon(String toolName){
        this(toolName, Type.START_POINT);
    }

    /**
     * alternate constructor that builds the object from a JSONObject
     * @param root - the JSONObject to get properties from
     */
    public ToolIcon(JSONObject root){
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
        root.put("tool", name);
        root.put("type", "ToolIcon");
        return root;
    }

    /**
     * imports the properties of this object from a JSON string
     * implementation of method defined in StratElement
     * @param root - the JSONObject representing the object
     */
    public void importFromJSON(JSONObject root){
        setPropertiesFromJSON(root);
        name = root.getString("tool");
    }

    /**
     * implementation of fetchImage defined in Icon class
     * used to retrieve the image the first time
     */
    public void fetchImage(){
        iconImage = AppController.getInstance().getData().getToolImage(name);
    }
}

