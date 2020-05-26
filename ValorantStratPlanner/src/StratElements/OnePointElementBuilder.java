package StratElements;

import javafx.scene.input.MouseEvent;

/**
 * an object that constructs/formats a one point strat element
 * @param <T> - the type of the strat element to format/construct
 */
public class OnePointElementBuilder<T extends OnePointStratElement> {
    private double x, y;

    /**
     * called when the user clicks a point, sets the coordinates to that point
     * @param e - the MouseEvent passed by the JavaFX API
     */
    public void click(MouseEvent e){
        x = e.getX();
        y = e.getY();
    }

    /**
     * formats the element to have the properties contained in the element builder
     * @param element - the element to format
     */
    public void formatElement(T element){
        element.setCoords(x, y);
    }
}
