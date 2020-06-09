package StratElements;

import ElementDecorators.CharacterIcon;
import org.json.JSONObject;

public class CharacterTool extends TwoPointTool{
    /**
     * default constructor
     * @param character - the name of the character
     */
    public CharacterTool(String character){
        super(character);
    }

    /**
     * alternate constructor that builds this object from a JSONObject
     * @param root - the JSONObject describing this one
     */
    public CharacterTool(JSONObject root){
        super(root);
    }

    /**
     * helper method to setup the icon decorator
     * Overridden from TwoPointTool
     */
    @Override
    protected void setupIcon(){
        CharacterIcon icon = new CharacterIcon(tool);
        addDecorator(icon);
    }

    /**
     * converts this object to a JSON string
     * implementation of method defined in StratElement
     * @return - the JSONObject representing this object
     * Overridden from TwoPointTool
     */
    @Override
    public JSONObject toJSON(){
        JSONObject root = new JSONObject();
        root.put("type", "CharacterTool");
        root.put("tool", tool);
        root.put("showLine", showLine);
        insertProperties(root);
        return root;
    }
}
