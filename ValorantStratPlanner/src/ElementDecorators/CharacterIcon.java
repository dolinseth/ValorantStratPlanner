package ElementDecorators;

import Main.AppController;
import javafx.scene.paint.Color;
import org.json.JSONObject;

public class CharacterIcon extends Icon {
    /**
     * default constructor
     * @param character - string containing the name of the character
     * @param type - the ElementDecorator.Type of this decorator
     */
    public CharacterIcon(String character, Type type){
        this.name = character;
        this.type = type;
        color = Color.YELLOW;
        alpha = 1.0;
    }

    /**
     * alternate constructor that defaults to ElementDecorator.Type.START_POINT
     * @param character - string containing the name of the character
     */
    public CharacterIcon(String character){
        this(character, Type.START_POINT);
    }

    /**
     * alternate constructor that builds the object from a JSONObject
     * @param root - the JSONObject to get properties from
     */
    public CharacterIcon(JSONObject root){
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
        root.put("character", name);
        root.put("type", "CharacterIcon");
        return root;
    }

    /**
     * imports the properties of this object from a JSON string
     * implementation of method defined in StratElement
     * @param root - the JSONObject representing the object
     */
    public void importFromJSON(JSONObject root){
        setPropertiesFromJSON(root);
        name = root.getString("character");
    }

    /**
     * implementation of fetchImage defined in Icon class
     * used to retrieve the image the first time
     */
    public void fetchImage(){
        iconImage = AppController.getInstance().getData().getCharacterIcon(name);
    }
}
