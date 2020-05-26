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

    /**
     * pre fetches images so that they don't have to be loaded from disk every time
     * implemented in classes that extend this one
     */
    public abstract void loadImages();

    /**
     * gets the internal hashmap containing the images
     * @return - the hashmap of image names to images
     */
    public HashMap<String,Image> getImages(){
        return images;
    }

    /**
     * helper method for classes that extend this one to load their images by passing in a list of file names and an extension
     * @param fileNames - the list of the image file names, extension excluded
     * @param fileExtension - the file extension of the images, assumed to be the same for all images
     */
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
