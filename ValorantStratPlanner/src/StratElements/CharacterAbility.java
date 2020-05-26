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
     * NOTE TO SELF
     * Try to implement a system for adding shapes with additional points
     * would allow things like setting the direction that a cypher cam is looking/shooting
     * could work by having a list of objects that contain [a point, a shape/list of shapes to draw, and a maximum distance]
     * could probably actually be an extension of the CharacterAbility class so we get color and shit
     * and then add additional shapes and give it no icon,
     *
     *
     * also give the option for the arrow to have a max distance that can be be set so that the line
     * only extends that far, like for demonstrating how far you can get with tailwind, or stun with breach
     *
     */





    public CharacterAbility(Color color, String ability){
        icon = new AbilityIcon(ability, ElementDecorator.Type.START_POINT);
        icon.setParent(this);
        this.color = color;
        decorators = new ArrayList<>();
    }

    public CharacterAbility(String ability){
        this(Color.YELLOW, ability);
    }

    public CharacterAbility(DataController.Ability ability){
        this(Color.YELLOW, ability.toString());
    }

    public void addDecorator(ElementDecorator elementDecorator){
        elementDecorator.setParent(this);
        decorators.add(elementDecorator);
    }

    public void draw(GraphicsContext gc){
        decorators.stream().forEach(ElementDecorator::setCoordsFromParent);
        icon.setCoords(x1, y1);
        icon.draw(gc);
        gc.setStroke(color);
        gc.strokeLine(x1, y1, x2, y2);
        decorators.stream().forEach(s -> s.draw(gc));
    }

    public String getAbility() {
        return ability;
    }
}
