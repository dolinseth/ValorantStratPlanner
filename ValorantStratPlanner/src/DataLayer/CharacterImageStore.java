package DataLayer;

import javafx.scene.image.Image;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CharacterImageStore extends ImageStore{
    public CharacterImageStore(String imageFolderPath){
        this.imageFolderPath = imageFolderPath;
    }

    public void loadImages(){
        ArrayList<String> charNames = new ArrayList<>(Arrays.asList("Brimstone", "Raze", "Cypher", "Jett", "Omen", "Breach", "Viper", "Phoenix", "Sage", "Sova"));
        loadImageSet(charNames, ".png");
    }

    public Image getCharacterIcon(String characterName){
        return images.getOrDefault(characterName.toLowerCase(), null);
    }

    public Image getCharacterIcon(DataController.Character character){
        return images.getOrDefault(character.toString().toLowerCase(), null);
    }
}
