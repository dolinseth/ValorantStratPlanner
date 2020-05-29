package DataLayer;

import StratElements.Strategy;
import javafx.scene.image.Image;
import org.json.JSONObject;

import java.util.List;

public class DataController {
    private AbilityImageStore abilityImages;
    private CharacterImageStore characterImages;
    private MapImageStore mapImages;
    private JSONManager jsonManager;

    //enumerated type containing every possible character ability
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

    //enumerated type containing every possible character
    public enum Character {
        BRIMSTONE, RAZE, CYPHER, JETT, OMEN, BREACH, VIPER, PHOENIX, SAGE, SOVA
    };

    //enumerated type containing every possible map
    public enum Map {
        BIND, SPLIT, HAVEN
    };

    /**
     * default constructor
     */
    public DataController(){
        abilityImages = new AbilityImageStore("AbilityImages");
        abilityImages.loadImages();
        characterImages = new CharacterImageStore("CharacterIcons");
        characterImages.loadImages();
        mapImages = new MapImageStore("MapImages");
        mapImages.loadImages();
        jsonManager = new JSONManager("res/SavedStrats");
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
     * @param ability - the name of the ability
     * @return - an image containing the icon for the given ability
     */
    public Image getAbilityImage(String ability){
        return abilityImages.getAbilityImage(ability);
    }

    /**
     * returns an image containing the icon for the character with the given name
     * @param character - the name of the character
     * @return - an image containing the icon for the given character
     */
    public Image getCharacterIcon(Character character){
        return characterImages.getCharacterIcon(character);
    }

    /**
     * returns an image containing the icon for the character with the given name
     * @param charName - the name of the character
     * @return - an image containing the icon for the given character
     */
    public Image getCharacterIcon(String charName){
        return characterImages.getCharacterIcon(charName);
    }

    /**
     * translates the enumerated ability type into a string representing the name of the ability
     * @param ability - the name of the ability
     * @return - the canonical name of the ability
     */
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

    /**
     * returns an image containing the map with the given name
     * @param map - the name of the map
     * @return - an image containing the given map
     */
    public Image getMapImage(Map map){
        return mapImages.getMapImage(map);
    }

    /**
     * returns an image containing the map with the given name
     * @param mapName - the name of the map
     * @return - an image containing the given map
     */
    public Image getMapImage(String mapName){
        return mapImages.getMapImage(mapName);
    }

    /**
     * gets the list of all saved strategies with their canonical names rather than file names
     * @return - a list containing the names of strategies saved in the SavedStrats folder
     */
    public List<String> getStrategies(){
        return jsonManager.getFileList();
    }

    /**
     * gets the Strategy object containing the strategy with the given name
     * @param name - the canonical name of the strategy (not including file extension)
     * @return - the Strategy object representing the strat, or null if a strat with that name is not found
     */
    public Strategy getStrat(String name){
        JSONObject root = jsonManager.getStrat(name);
        if(root == null){
            return null;
        }
        return new Strategy(root);
    }

    /**
     * saves the given strategy to the SavedStrats folder with the given name
     * @param strategy - the Strategy to save
     * @param name - the name to use to save the strategy
     */
    public void saveStrat(Strategy strategy, String name){
        jsonManager.saveStrat(strategy, name);
    }
}
