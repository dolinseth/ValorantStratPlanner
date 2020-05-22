package Shapes;

import com.sun.prism.Graphics;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Shape{
    //draws a rectangle that has the given width and runs the length of the vector between the endpoints
    private double width;

    public Rectangle(double width, Color color, double alpha){
        this.width = width;
        this.color = color;
        this.alpha = alpha;
    }

    public void draw(GraphicsContext gc){
        double rectX1, rectY1, rectX2, rectY2;
        double theta = Math.atan2(y2 -y1, x2 - x1);
        theta += Math.PI / 4.0;
        rectY1 = Math.sin(theta)*width;
        rectY2 = -Math.sin(theta)*width;
        rectX1 = Math.cos(theta)*width;
        rectX2 = -Math.cos(theta)*width;
        gc.setFill(color);
        gc.fillRect(rectX1, rectX2, rectY1, rectY2);
    }
}
