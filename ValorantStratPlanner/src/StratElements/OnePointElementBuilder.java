package StratElements;

import javafx.scene.input.MouseEvent;

public class OnePointElementBuilder<T extends OnePointStratElement> {
    private double x, y;

    public void click(MouseEvent e){
        x = e.getX();
        y = e.getY();
    }

    public void formatElement(T element){
        element.setCoords(x, y);
    }
}
