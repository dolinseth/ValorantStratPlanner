package StratElements;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class WatchHere extends TwoPointStratElement {
    private double x1, y1, x2, y2;
    private Color color = Color.YELLOW;
    private final double radius = 20;

    public WatchHere(){}

    public void draw(GraphicsContext gc){
        gc.setStroke(color);
        gc.strokeOval(x1 - radius / 2, y1 - radius / 2, radius, radius);
        gc.strokeLine(x1, y1, x2, y2);
        ArrowHead arrowHead = new ArrowHead(x1, y2, x2, y2, color);
        arrowHead.draw(gc);
    }
}
