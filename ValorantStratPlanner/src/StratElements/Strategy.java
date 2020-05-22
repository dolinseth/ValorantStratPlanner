package StratElements;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Strategy {
    private ArrayList<StratElement> elements;
    private String jsonString;

    public void addElement(StratElement el){
        elements.add(el);
    }

    public void addElements(List<StratElement> els){
        els.stream().forEach(el -> elements.add(el));
    }

    public String serialize(){
        String ret = "";
        JSONArray elementArr = new JSONArray();
        elements.stream().forEach(el -> {
            JSONObject elementObj = new JSONObject();
            if(el instanceof TwoPointStratElement) {
                TwoPointStratElement element = (TwoPointStratElement)el;
                elementObj.put("x1", element.x1);
                elementObj.put("y1", element.y1);
                elementObj.put("x2", element.x2);
                elementObj.put("y2", element.y2);
            }
            if(el instanceof WatchHere){
                elementObj.put("type", "WatchHere");
            }
            else if(el instanceof Line){
                elementObj.put("type", "Line");
            }
            else if(el instanceof CharacterAbility){
                elementObj.put("type", "CharacterAbility");
                elementObj.put("ability", ((CharacterAbility) el).getAbility());
                //if additional points ever get added, that'll happen here
            }
            elementArr.put(elementObj);
        });

        return elementArr.toString();
    }

    public ArrayList<StratElement> deSerialize(){
        //TODO implement this method
    }

    public void importJSON(String filepath){
        //TODO implement this method
    }


    public ArrayList<StratElement> getElements() {
        return elements;
    }

    public void setElements(ArrayList<StratElement> elements) {
        this.elements = elements;
    }

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }
}
