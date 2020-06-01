package StratElements;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Strategy {
    private ArrayList<StratElement> elements = new ArrayList<StratElement>();
    private JSONObject root;

    /**
     * default constructor, does nothing
     */
    public Strategy(){}

    /**
     * alternate constructor that builds this object from a JSONObject
     * @param root - the JSONObject that describes this object
     */
    public Strategy(JSONObject root){
        this.root = root;
        deserialize(root);
    }

    /**
     * alternate constructor that builds the object from a list of elements
     * @param elements - the list of elements to build this strategy from
     */
    public Strategy(ArrayList<StratElement> elements){
        this.elements = elements;
    }

    /**
     * adds an element to the internal element list
     * @param el - the element to add
     */
    public void addElement(StratElement el){
        elements.add(el);
    }

    /**
     * adds a list of elements to the internal element list
     * @param els - the list of elements to add
     */
    public void addElements(List<StratElement> els){
        els.stream().forEach(el -> elements.add(el));
    }

    public void serialize(){
        root = new JSONObject();
        String ret = "";
        JSONArray elementArr = new JSONArray();
        elements.stream().forEach(el -> {
            elementArr.put(el.toJSON());
        });
        root.put("elements", elementArr);
    }

    public void deserialize(JSONObject root){
        elements = new ArrayList<StratElement>();
        JSONArray elementArr = root.getJSONArray("elements");
        for(int i = 0; i < elementArr.length(); i++){
            JSONObject eljson = elementArr.getJSONObject(i);
            String type = eljson.getString("type");
            switch(type){
                case "CharacterAbility":
                    elements.add(new CharacterAbility(eljson));
                    break;
                case "tool":
                    elements.add(new TwoPointTool(eljson));
                    break;
                case "FreeformTool":
                    elements.add(new FreeformTool(eljson));
                    break;
            }
        }
    }



    /*
    GETTERS AND SETTERS
     */

    public ArrayList<StratElement> getElements() {
        return elements;
    }

    public void setElements(ArrayList<StratElement> elements) {
        this.elements = elements;
    }

    public JSONObject getRoot(){
        return root;
    }
}
