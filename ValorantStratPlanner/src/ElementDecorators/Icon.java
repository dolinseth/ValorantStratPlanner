package ElementDecorators;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Icon extends ElementDecorator{
    protected String name;
    public static final double size = 30;
    protected Image iconImage;

    /**
     * generic implementation of the draw method defined in StratElement
     * @param gc - the GraphicsContext in which to draw the element
     */
    public void draw(GraphicsContext gc){
        if(isVisible) {
            if (iconImage == null) {
                fetchImage();
            }
            gc.drawImage(iconImage, x1 - size / 2, y1 - size / 2, size, size);
            gc.setStroke(color);
            gc.strokeOval(x1 - size / 2, y1 - size / 2, size, size);
        }
    }

    /**
     * helper method to fetch the iconImage and store it for later use
     * to be implemented by classes extending this one
     */
    protected abstract void fetchImage();
}
