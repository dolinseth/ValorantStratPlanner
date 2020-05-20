package DataLayer;

import javafx.scene.image.Image;

public class DataController {
    private AbilityImageStore abilityImages;
    public enum Ability {
        SKY_SMOKE, ORBITAL_STRIKE, STIM_BEACON, INCENDIARY,
        PAINT_SHELLS, SHOWSTOPPER, BOOM_BOT, BLAST_PACK,
        SPYCAM, NEURAL_THEFT, TRIPWIRE, CYBER_CAGE,
        TAILWIND, BLADESTORM, CLOUDBURST, UPDRAFT,
        DARK_COVER, FROM_THE_SHADOWS, SHROUDED_STEP, PARANOIA,
        FAULT_LINE, ROLLING_THUNDER, AFTERSHOCK, FLASHPOINT,
        TOXIC_SCREEN, VIPERS_PIT, SNAKE_BITE, POISON_CLOUD,
        HOT_HANDS, RUN_IT_BACK, BLAZE, CURVEBALL,
        HEALING_ORB, RESURRECTION, BARRIER_ORB, SLOW_ORB,
        RECON_BOLT, HUNTERS_FURY, OWL_DRONE, SHOCK_BOLT
    };

    //Default constructor, initializes lists
    public DataController(){
        abilityImages = new AbilityImageStore("AbilityImages");
        abilityImages.loadImages();
    }

    /**
     * returns an Image containing the icon for the ability with the given name,
     * or null if no image is found associated with that ability name
     * @param ability - A DataController.Ability typed object describing the ability to fetch the icon for
     * @return Image
     */
    public Image getAbilityImage(Ability ability){
        return abilityImages.getAbilityImage(ability.toString());
    }

    /**
     * returns an Image containing the icon for the ability with the given name,
     * or null if no image is found associated with that ability name
     * @param ability - string containing the name of the ability to get the icon for
     * @return Image
     */
    public Image getAbilityImage(String ability){
        return abilityImages.getAbilityImage(ability);
    }
}
