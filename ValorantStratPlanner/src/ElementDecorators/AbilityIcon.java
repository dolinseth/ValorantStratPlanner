package ElementDecorators;

import Main.AppController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.json.JSONObject;

public class AbilityIcon extends ElementDecorator {
    private String ability;
    public static final double size = 30;

    /**
     * default constructor
     * @param ability - string containing the name of the ability
     * @param type - the ElementDecorator.Type of this decorator
     */
    public AbilityIcon(String ability, Type type) {
        this.ability = ability;
        this.type = type;
        color = Color.YELLOW;
        alpha = 1.0;
    }

    /**
     * alternate constructor that builds the object from a JSONObject
     * @param root - the JSONObject to get properties from
     */
    public AbilityIcon(JSONObject root){
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
        root.put("ability", ability);
        root.put("type", "AbilityIcon");
        return root;
    }

    /**
     * imports the properties of this object from a JSON string
     * implementation of method defined in StratElement
     * @param root - the JSONObject representing the object
     */
    public void importFromJSON(JSONObject root){
        setPropertiesFromJSON(root);
        ability = root.getString("ability");
    }

    /**
     * implementation of the draw method defined in the abstract class ElementDecorator
     * draws the icon using the given GraphicsContext
     * @param gc - the GraphicsContext in which to draw the icon
     */
    public void draw(GraphicsContext gc){
        gc.drawImage(AppController.getInstance().getData().getAbilityImage(ability), x1 - size/2, y1 - size/2, size, size);
        gc.setStroke(color);
        gc.strokeOval(x1 - size / 2, y1 - size / 2, size, size);
    }
}
