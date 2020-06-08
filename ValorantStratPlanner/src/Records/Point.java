package Records;

import org.json.JSONObject;

public class Point {
    public double x, y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Point(JSONObject root){
        importFromJSON(root);
    }

    public JSONObject toJSON(){
        JSONObject root = new JSONObject();
        root.put("type", "point");
        root.put("x", x);
        root.put("y", y);
        return root;
    }

    public void importFromJSON(JSONObject root){
        x = root.getDouble("x");
        y = root.getDouble("y");
    }

    @Override
    public String toString(){
        return x + ", " + y;
    }
}
