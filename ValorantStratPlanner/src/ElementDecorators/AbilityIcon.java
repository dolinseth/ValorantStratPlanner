package ElementDecorators;

import AppController.AppController;
import javafx.scene.paint.Color;
import org.json.JSONObject;

public class AbilityIcon extends Icon{
    /**
     * default constructor
     * @param ability - string containing the name of the ability
     * @param type - the ElementDecorator.Type of this decorator
     */
    public AbilityIcon(String ability, Type type) {
        this.name = ability;
        this.type = type;
        color = Color.YELLOW;
        alpha = 1.0;
    }

    /**
     * alternate constructor that defaults to ElementDecorator.Type.START_POINT
     * @param ability - the name of the ability
     */
    public AbilityIcon(String ability){
        this(ability, Type.START_POINT);
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
        root.put("ability", name);
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
        name = root.getString("ability");
    }

    /**
     * implementation of fetchImage defined in Icon class
     * used to retrieve the image the first time
     */
    public void fetchImage(){
        iconImage = AppController.getInstance().getData().getAbilityImage(name);
    }
}
