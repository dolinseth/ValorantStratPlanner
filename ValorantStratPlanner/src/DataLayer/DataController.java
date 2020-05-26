package DataLayer;

import javafx.scene.image.Image;

public class DataController {
    private AbilityImageStore abilityImages;
    private CharacterImageStore characterImages;
    private MapImageStore mapImages;

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

    public enum Character {
        BRIMSTONE, RAZE, CYPHER, JETT, OMEN, BREACH, VIPER, PHOENIX, SAGE, SOVA
    };

    public enum Map {
        BIND, SPLIT, HAVEN
    };

    //Default constructor, initializes lists
    public DataController(){
        abilityImages = new AbilityImageStore("AbilityImages");
        abilityImages.loadImages();
        characterImages = new CharacterImageStore("CharacterIcons");
        characterImages.loadImages();
        mapImages = new MapImageStore("MapImages");
        mapImages.loadImages();
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

    public Image getCharacterIcon(Character character){
        return characterImages.getCharacterIcon(character);
    }

    public Image getCharacterIcon(String charName){
        return characterImages.getCharacterIcon(charName);
    }

    public String getAbilityName(Ability ability){
        String label = "ABILITY NOT FOUND";
        switch(ability){
            case SKY_SMOKE:
                label = "Sky Smoke";
                break;
            case ORBITAL_STRIKE:
                label = "Orbital Strike";
                break;
            case STIM_BEACON:
                label = "Stim Beacon";
                break;
            case INCENDIARY:
                label = "Incendiary";
                break;
            case PAINT_SHELLS:
                label = "Paint Shells";
                break;
            case SHOWSTOPPER:
                label = "Showstopper";
                break;
            case BOOM_BOT:
                label = "Boom Bot";
                break;
            case BLAST_PACK:
                label = "Blast Pack";
                break;
            case SPYCAM:
                label = "Spycam";
                break;
            case NEURAL_THEFT:
                label = "Neural Theft";
                break;
            case TRIPWIRE:
                label = "Tripwire";
                break;
            case CYBER_CAGE:
                label = "Cyber Cage";
                break;
            case TAILWIND:
                label = "Tailwind";
                break;
            case BLADESTORM:
                label = "Bladestorm";
                break;
            case CLOUDBURST:
                label = "Cloudburst";
                break;
            case UPDRAFT:
                label = "Updraft";
                break;
            case DARK_COVER:
                label = "Dark Cover";
                break;
            case FROM_THE_SHADOWS:
                label = "From the Shadows";
                break;
            case SHROUDED_STEP:
                label = "Shrouded Step";
                break;
            case PARANOIA:
                label = "Paranoia";
                break;
            case FAULT_LINE:
                label = "Fault Line";
                break;
            case ROLLING_THUNDER:
                label = "Rolling Thunder";
                break;
            case AFTERSHOCK:
                label = "Aftershock";
                break;
            case FLASHPOINT:
                label = "Flashpoint";
                break;
            case TOXIC_SCREEN:
                label = "Toxic Screen";
                break;
            case VIPERS_PIT:
                label = "Viper's Pit";
                break;
            case SNAKE_BITE:
                label = "Snake Bite";
                break;
            case POISON_CLOUD:
                label = "Poison Cloud";
                break;
            case HOT_HANDS:
                label = "Hot Hands";
                break;
            case RUN_IT_BACK:
                label = "Run it Back";
                break;
            case BLAZE:
                label = "Blaze";
                break;
            case CURVEBALL:
                label = "Curveball";
                break;
            case HEALING_ORB:
                label = "Healing Orb";
                break;
            case RESURRECTION:
                label = "Resurrection";
                break;
            case BARRIER_ORB:
                label = "Barrier Orb";
                break;
            case SLOW_ORB:
                label = "Slow Orb";
                break;
            case RECON_BOLT:
                label = "Recon Bolt";
                break;
            case HUNTERS_FURY:
                label = "Hunter's Fury";
                break;
            case OWL_DRONE:
                label = "Owl Drone";
                break;
            case SHOCK_BOLT:
                label = "Shock Bolt";
                break;
        }

        return label;
    }

    public Image getMapImage(Map map){
        return mapImages.getMapImage(map);
    }

    public Image getMapImage(String mapName){
        return mapImages.getMapImage(mapName);
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
