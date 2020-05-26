package DataLayer;

import javafx.scene.image.Image;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class ImageStore {
    protected HashMap<String, Image> images = new HashMap<>();
    protected String imageFolderPath;
    protected double prefWidth = 32;
    protected double prefHeight = 32;

    public abstract void loadImages();

    public HashMap<String,Image> getImages(){
        return images;
    }

    protected void loadImageSet(ArrayList<String> fileNames, String fileExtension){
        fileNames.forEach(n -> {
            String path = (imageFolderPath.startsWith("/") ? "" : "/") + imageFolderPath + (imageFolderPath.endsWith("/") ? "" : "/") + n + (fileExtension.startsWith(".") ? "" : ".") + fileExtension;
            URL url = getClass().getResource(path);
            if(url != null) {
                String urlStr = url.toString();
                Image i = new Image(urlStr, prefWidth, prefHeight, true, false);
                images.put(n.toLowerCase(), i);
            }
            else{
                System.out.println("ERROR: failed to load image " + imageFolderPath + "/" + n + fileExtension);
            }
        });
    }
}
