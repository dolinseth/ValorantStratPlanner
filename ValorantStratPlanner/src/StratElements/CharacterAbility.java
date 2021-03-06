package StratElements;

import DataLayer.DataController;
import ElementDecorators.AbilityIcon;
import ElementDecorators.ElementDecorator;
import Records.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.json.JSONObject;

import java.util.ArrayList;

public class CharacterAbility extends TwoPointStratElement{
    private String ability;
    public static final double visionBlockRadius = 47;
    public static final double areaDenialRadius = 47;
    protected boolean showLine = true;

    /**
     * default constructor
     * @param color - the color of the circle/arrow associated with this ability
     * @param ability - the name of the ability
     */
    public CharacterAbility(Color color, String ability){
        decorators = new ArrayList<>();
        AbilityIcon icon = new AbilityIcon(ability);
        icon.setParent(this);
        decorators.add(icon);
        this.ability = ability.toLowerCase();
        this.color = color;
    }

    /**
     * alternate constructor that builds the object from a JSONObject
     * @param root - the JSONObject to get properties from
     */
    public CharacterAbility(JSONObject root){
        importFromJSON(root);
    }

    /**
     * alternate constructor that uses a default color
     * @param ability - the name of the ability
     */
    public CharacterAbility(String ability){
        this(Color.YELLOW, ability);
    }

    /**
     * alternate constructor that uses the enumerated Ability type instead of a string
     * as well as a default color
     * @param ability - the name of the ability
     */
    public CharacterAbility(DataController.Ability ability){
        this(Color.YELLOW, ability.toString());
    }

    /**
     * implementation of the draw method defined in the abstract class TwoPointStratElement
     * draws the character ability in the given GraphicsContext
     * @param gc - the GraphicsContext in which to draw the character ability
     */
    public void draw(GraphicsContext gc){
        gc.setStroke(color);
        if(showLine) {
            Point offsetStart = getStartOffsetByRadius(AbilityIcon.size);
            gc.strokeLine(offsetStart.x, offsetStart.y, x2, y2);
        }
        drawDecorators(gc);
    }

    /**
     * converts this object to a JSON string
     * implementation of method defined in StratElement
     * @return - the JSONObject representing the object
     */
    public JSONObject toJSON(){
        JSONObject root = new JSONObject();
        root.put("type", "CharacterAbility");
        root.put("showLine", showLine);
        root.put("ability", ability);
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
        showLine = root.getBoolean("showLine");
        ability = root.getString("ability");
    }

    /*
    GETTERS AND SETTERS
     */

    public String getAbility() {
        return ability;
    }

    public void setShowLine(boolean showLine){
        this.showLine = showLine;
    }
}
