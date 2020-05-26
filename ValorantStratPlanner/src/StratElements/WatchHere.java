package StratElements;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class WatchHere extends TwoPointStratElement {
    private Color color = Color.YELLOW;
    private final double radius = 20;

    /**
     * implementation of the draw method defined in the abstract class TwoPointStratElement
     * draws the WatchHere in the given GraphicsContext
     * @param gc - the GraphicsContext in which to draw the WatchHere
     */
    public void draw(GraphicsContext gc){
        gc.setStroke(color);
        gc.strokeOval(x1 - radius / 2, y1 - radius / 2, radius, radius);
        gc.strokeLine(x1, y1, x2, y2);
        ArrowHead arrowHead = new ArrowHead(x1, y2, x2, y2, color);
        arrowHead.draw(gc);
    }
}
