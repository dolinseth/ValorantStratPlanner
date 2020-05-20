package StratElements;

import DataLayer.DataController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class AbilityImageStratElement extends OnePointStratElement{
    String abilityName;
    Image image;
    DataController data;
    double x, y;
    double size;

    public AbilityImageStratElement(String abilityName, DataController data){
        this.abilityName = abilityName;
        this.data = data;
        this.image = data.getAbilityImage(abilityName);
    }

    public void setCoords(double x, double y){
        this.x = x;
        this.y = y;
    }


    public void draw(GraphicsContext gc){
        double oldOpacity = gc.getGlobalAlpha();
        gc.setGlobalAlpha(0.3);
        gc.drawImage(image, x, y, size, size);
        gc.setGlobalAlpha(oldOpacity);
        gc.setStroke(Color.YELLOW);
        gc.strokeOval(x, y, size, size);
    }
}
