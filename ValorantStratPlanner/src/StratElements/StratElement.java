package StratElements;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public abstract class StratElement {
    public abstract void draw(GraphicsContext gc);
    public abstract void setColor(Color c);
}
