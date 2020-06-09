package StratElements;

import org.json.JSONObject;

public class StratElementFactory {
    private static StratElementFactory stratElementFactory;

    /**
     * default constructor, used privately because this is a singleton class
     */
    private StratElementFactory(){}

    /**
     * returns the single instance of this class that is stored statically
     * and initializes that static instance if it has not been already
     * @return - the single instance of StratElementFactory
     */
    public static StratElementFactory getInstance(){
        if(stratElementFactory == null){
            stratElementFactory = new StratElementFactory();
        }
        return stratElementFactory;
    }

    /**
     * returns a strat element built from a JSONObject
     * @param root - the JSONObject describing the element to build
     * @return - the element described by root
     */
    public StratElement getStratElementFromJSON(JSONObject root){
        String type = root.getString("type");
        StratElement ret = null;
        switch(type){
            case "CharacterAbility":
                ret = new CharacterAbility(root);
                break;
            case "Tool":
                ret = new TwoPointTool(root);
                break;
            case "FreeformTool":
                ret = new FreeformTool(root);
                break;
            case "CharacterTool":
                ret = new CharacterTool(root);
                break;
        }

        return ret;
    }
}
