package StratElements;

import DataLayer.DataController;
import Main.AppController;
import Shapes.AbilityIcon;
import Shapes.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class AbilityImageStratElement extends OnePointStratElement{
    AbilityIcon icon;
    double size;
    ArrayList<Shape> additionalElements = new ArrayList<Shape>();

    public AbilityImageStratElement(String abilityName){
        this.icon = new AbilityIcon(abilityName);
    }

    public void setCoords(double x, double y){
        this.x = x;
        this.y = y;
        icon.setCoords(x, y);
    }

    public void draw(GraphicsContext gc){
        double oldOpacity = gc.getGlobalAlpha();
        gc.setGlobalAlpha(0.3);
        icon.draw(gc);
        gc.setGlobalAlpha(oldOpacity);
        gc.setStroke(Color.YELLOW);
        gc.strokeOval(x, y, size, size);
    }
}
