package StratElements;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Arrow extends TwoPointStratElement{
    private Line line;
    private ArrowHead arrowHead;
    private Color color;

    public Arrow(double x1, double y1, double x2, double y2, Color color){
        super.x1 = x1;
        super.y1 = y1;
        super.x2 = x2;
        super.y2 = y2;
        this.color = color;
    }

    public void draw(GraphicsContext gc){
        if(line == null){
            line = new Line(x1, y1, x2, y2, color);
        }
        if(arrowHead == null){
            arrowHead = new ArrowHead(x1, y1, x2, y2, color);
        }
        line.draw(gc);
        arrowHead.draw(gc);
    }
}
