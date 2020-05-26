package StratElements;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public abstract class StratElement {
    /**
     * draws the StratElement in the given graphics context
     * to be implemented by any classes extending this one
     * @param gc - the GraphicsContext in which to draw the element
     */
    public abstract void draw(GraphicsContext gc);
}
