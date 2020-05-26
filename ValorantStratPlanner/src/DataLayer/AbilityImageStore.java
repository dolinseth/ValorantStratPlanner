package DataLayer;

import javafx.scene.image.Image;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AbilityImageStore extends ImageStore{
    /**
     * default constructor
     * @param imageFolderPath - the path to the folder that contains the character images
     */
    public AbilityImageStore(String imageFolderPath){
        this.imageFolderPath = imageFolderPath;
    }

    /**
     * implementation of loadImages, defined in the abstract class ImageStore
     * fetches the images so that they don't have to be gotten from disk every time
     */
    public void loadImages(){
        if(images.size() == 0) {
            ArrayList<String> abilities = new ArrayList<String>(Arrays.asList(
                    "Sky_Smoke", "Orbital_Strike", "Stim_Beacon", "Incendiary",
                    "Paint_Shells", "Showstopper", "Boom_Bot", "Blast_Pack",
                    "Spycam", "Neural_Theft", "Tripwire", "Cyber_Cage",
                    "Tailwind", "Bladestorm", "Cloudburst", "Updraft",
                    "Dark_Cover", "From_the_Shadows", "Shrouded_Step", "Paranoia",
                    "Fault_Line", "Rolling_Thunder", "Aftershock", "Flashpoint",
                    "Toxic_Screen", "Vipers_Pit", "Snake_Bite", "Poison_Cloud",
                    "Hot_Hands", "Run_it_Back", "Blaze", "Curveball",
                    "Healing_Orb", "Resurrection", "Barrier_Orb", "Slow_Orb",
                    "Recon_Bolt", "Hunters_Fury", "Owl_Drone", "Shock_Bolt"

            ));
            loadImageSet(abilities, ".png");
        }
    }

    /**
     * gets the image for the ability with the given name
     * @param ability - the name of the ability to get the icon for
     * @return Image
     */
    public Image getAbilityImage(String ability){
        loadImages();
        return images.getOrDefault(ability.toLowerCase(), null);
    }
}
