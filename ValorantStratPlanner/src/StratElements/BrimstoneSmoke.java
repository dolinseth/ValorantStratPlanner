package StratElements;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BrimstoneSmoke extends OnePointStratElement {
    private double x, y;
    private Color color = Color.LIGHTGRAY;
    private final double radius = 50;

    public BrimstoneSmoke(){}

    public BrimstoneSmoke(double x, double y, Color color){
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void draw(GraphicsContext gc){
        double oldOpacity = gc.getGlobalAlpha();
        gc.setGlobalAlpha(0.3);
        gc.setFill(color);
        gc.fillOval(x - radius/2, y - radius /2, radius, radius);
        gc.setGlobalAlpha(oldOpacity);
    }

    public void setCoords(double x, double y){
        this.x = x;
        this.y = y;
    }
}
