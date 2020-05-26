package DataLayer;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;

public class MapImageStore extends ImageStore {
    public MapImageStore(String imageFolderPath){
        this.imageFolderPath = imageFolderPath;
        prefWidth = 1000;
        prefHeight = 800;
    }

    public void loadImages(){
        if(images.size() == 0) {
            ArrayList<String> mapNames = new ArrayList<>(Arrays.asList("Bind", "Split", "Haven"));
            loadImageSet(mapNames, ".png");
        }
    }

    public Image getMapImage(String mapName){
        loadImages();
        return images.getOrDefault(mapName.toLowerCase(), null);
    }

    public Image getMapImage(DataController.Map map){
        loadImages();
        return images.getOrDefault(map.toString().toLowerCase(), null);
    }
}
