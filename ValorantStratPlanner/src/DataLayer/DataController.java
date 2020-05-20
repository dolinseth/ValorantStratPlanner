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

    public DataController(){
        abilityImages = new AbilityImageStore("AbilityImages");
        abilityImages.loadImages();
    }

    public Image getAbilityImage(Ability ability){
        return abilityImages.getAbilityImage(ability.toString());
    }

    public Image getAbilityImage(String ability){
        return abilityImages.getAbilityImage(ability);
    }
}
