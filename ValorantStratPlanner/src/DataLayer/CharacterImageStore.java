package DataLayer;

import javafx.scene.image.Image;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CharacterImageStore extends ImageStore{
    /**
     * default constructor
     * @param imageFolderPath - the path to the folder that contains the character images
     */
    public CharacterImageStore(String imageFolderPath){
        this.imageFolderPath = imageFolderPath;
        prefHeight = 60;
        prefWidth = 60;
    }

    /**
     * implementation of loadImages, defined in the abstract class ImageStore
     * fetches the images so that they don't have to be gotten from disk every time
     */
    public void loadImages() {
        if(images.size() == 0) {
            ArrayList<String> charNames = new ArrayList<>(Arrays.asList("Brimstone", "Raze", "Cypher", "Jett", "Omen", "Breach", "Viper", "Phoenix", "Sage", "Sova", "Reyna"));
            loadImageSet(charNames, ".png");
        }
    }

    /**
     * gets the character icon for the character with the given name
     * @param characterName - the name of the character
     * @return - an image containing the character's icon
     */
    public Image getCharacterIcon(String characterName){
        loadImages();
        return images.getOrDefault(characterName.toLowerCase(), null);
    }

    /**
     * gets the character icon for the character with the given name
     * @param character - the name of the character
     * @return - an image containing the character's icon
     */
    public Image getCharacterIcon(DataController.Character character){
        loadImages();
        return images.getOrDefault(character.toString().toLowerCase(), null);
    }
}
