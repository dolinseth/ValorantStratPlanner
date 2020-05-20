package StratElements;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class WatchHere extends TwoPointStratElement {
    private double x1, y1, x2, y2;
    private Color color = Color.YELLOW;
    private final double radius = 30;
    private final double arrowHeadLength = 5;

    public WatchHere(){}

    public void setCoords(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public void draw(GraphicsContext gc){
        gc.setStroke(color);
        gc.strokeOval(x1 - radius / 2, y1 - radius / 2, radius, radius);
        gc.strokeLine(x1, y1, x2, y2);
        ArrowHead arrowHead = new ArrowHead(x1, y2, x2, y2, color);
        arrowHead.draw(gc);
    }
}
