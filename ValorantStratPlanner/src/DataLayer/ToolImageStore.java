package DataLayer;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;

public class ToolImageStore extends ImageStore{
    /**
     * default constructor
     * @param imageFolderPath - the path to the folder that contains the character images
     */
    public ToolImageStore(String imageFolderPath){
        this.imageFolderPath = imageFolderPath;
        prefHeight = 32;
        prefWidth = 32;
    }

    /**
     * implementation of loadImages, defined in the abstract class ImageStore
     * fetches the images so that they don't have to be gotten from disk every time
     */
    public void loadImages(){
        if(images.size() == 0){
            ArrayList<String> toolNames = new ArrayList<String>(Arrays.asList("WatchHere", "Measure"));
            loadImageSet(toolNames, ".png");
        }
    }

    /**
     * gets the image for the tool with the given name
     * @param toolName - the name of the tool
     * @return - the image of the given tool
     */
    public Image getToolImage(String toolName){
        loadImages();
        if(images == null){
            System.out.println("shit");
        }
        if(toolName == null){
            System.out.println("double shit");
        }
        return images.getOrDefault(toolName.toLowerCase(), null);
    }
}
