package StratElements;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ArrowHead extends StratElement{
    private double x1, y1, x2, y2;
    private Color color;
    private final double lineLength = 10;

    public ArrowHead(double x1, double y1, double x2, double y2, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }

    public void draw(GraphicsContext gc){
        double initAngle = Math.atan2((y2 - y1), (x2 - x1));
        gc.setStroke(color);
        gc.strokeLine(x2, y2, x2 + Math.cos(initAngle - Math.PI/12)* lineLength, y2 + Math.sin(initAngle - Math.PI/12)* lineLength);
        gc.strokeLine(x2, y2, x2 + Math.cos(initAngle + Math.PI/12)* lineLength, y2 + Math.sin(initAngle + Math.PI/12)* lineLength);
    }
}
