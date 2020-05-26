package StratElements;

import DataLayer.DataController;
import Shapes.AbilityIcon;
import Shapes.ElementDecorator;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class CharacterAbility extends TwoPointStratElement{
    private AbilityIcon icon;
    private Color color = Color.YELLOW;
    private String ability;
    private ArrayList<ElementDecorator> decorators;
    public static final double visionBlockRadius = 47;
    public static final double areaDenialRadius = 47;

    /**
     * default constructor
     * @param color - the color of the circle/arrow associated with this ability
     * @param ability - the name of the ability
     */
    public CharacterAbility(Color color, String ability){
        icon = new AbilityIcon(ability, ElementDecorator.Type.START_POINT);
        icon.setParent(this);
        this.color = color;
        decorators = new ArrayList<>();
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
     * adds a new decorator to this ability
     * @param elementDecorator - the decorator to add
     */
    public void addDecorator(ElementDecorator elementDecorator){
        elementDecorator.setParent(this);
        decorators.add(elementDecorator);
    }

    /**
     * implementation of the draw method defined in the abstract class TwoPointStratElement
     * draws the character ability in the given GraphicsContext
     * @param gc - the GraphicsContext in which to draw the character ability
     */
    public void draw(GraphicsContext gc){
        decorators.stream().forEach(ElementDecorator::setCoordsFromParent);
        icon.setCoords(x1, y1);
        icon.draw(gc);
        gc.setStroke(color);
        gc.strokeLine(x1, y1, x2, y2);
        decorators.stream().forEach(s -> s.draw(gc));
    }

    /*
    GETTERS AND SETTERS
     */

    public String getAbility() {
        return ability;
    }
}
