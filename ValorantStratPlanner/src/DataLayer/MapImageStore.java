package DataLayer;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;

public class MapImageStore extends ImageStore {
    /**
     * default constructor
     * @param imageFolderPath - the path to the folder that contains the character images
     */
    public MapImageStore(String imageFolderPath){
        this.imageFolderPath = imageFolderPath;
        prefWidth = 1000;
        prefHeight = 800;
    }

    /**
     * implementation of loadImages, defined in the abstract class ImageStore
     * fetches the images so that they don't have to be gotten from disk every time
     */
    public void loadImages(){
        if(images.size() == 0) {
            ArrayList<String> mapNames = new ArrayList<>(Arrays.asList("Bind", "Split", "Haven"));
            loadImageSet(mapNames, ".png");
        }
    }

    /**
     * gets the image for the map with the given name
     * @param mapName - the name of the map
     * @return - the image of the given map
     */
    public Image getMapImage(String mapName){
        loadImages();
        return images.getOrDefault(mapName.toLowerCase(), null);
    }

    /**
     * gets the image for the map with the given name
     * @param map - the name of the map
     * @return - the image of the given map
     */
    public Image getMapImage(DataController.Map map){
        loadImages();
        return images.getOrDefault(map.toString().toLowerCase(), null);
    }
}
