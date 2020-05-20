package DataLayer;

import javafx.scene.image.Image;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AbilityImageStore {
    private HashMap<String, Image> images = new HashMap<String, Image>();
    private String imageFolderPath;

    public AbilityImageStore(String imageFolderPath){
        this.imageFolderPath = imageFolderPath;
    }

    public void loadImages(){
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
        abilities.forEach(ability -> {
            String path = (imageFolderPath.startsWith("/") ? "" : "/") + imageFolderPath + (imageFolderPath.endsWith("/") ? "" : "/") + ability + ".png";
            URL url = getClass().getResource(path);
            String urlStr = url.toString();
            Image i = new Image(urlStr, 32, 32, true, false);
            images.put(ability.toLowerCase(), i);
        });
    }

    public HashMap<String, Image> getImages(){
        if(images.size() == 0){
            loadImages();
        }
        return images;
    }

    public Image getAbilityImage(String ability){
        return images.getOrDefault(ability.toLowerCase(), null);
    }
}
