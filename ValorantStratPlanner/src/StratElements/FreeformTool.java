package StratElements;

import ElementDecorators.ElementDecorator;
import Records.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class FreeformTool extends TwoPointStratElement{
    protected ArrayList<Point> points = new ArrayList<Point>();
    private Color color;
    private String tool;

    /**
     * default constructor
     * @param color - the color to use to draw parts of this tool
     * @param tool - the name of the tool
     */
    public FreeformTool(Color color, String tool){
        this.tool = tool;
        this.color = color;
    }

    /**
     * alternate constructor that uses a default color of Color.YELLOW
     * @param tool - the name of the tool
     */
    public FreeformTool(String tool){
        this(Color.YELLOW, tool);
    }

    /**
     * adds a point to the list of points that represents the path
     * @param p - the Point to add
     */
    public void addPoint(Point p){
        points.add(p);
    }

    /**
     * adds a point to the list of points that represents the path
     * @param x - the x coordinate of the new point
     * @param y - the y coordinate of the new point
     */
    public void addPoint(double x, double y){
        points.add(new Point(x, y));
    }

    /**
     * helper method for serialization, inserts the start and end coordinates into the given JSONObject
     * @param root - the JSONObject into which the coords should be inserted
     */
    @Override
    protected void insertCoords(JSONObject root){
        JSONArray pointsArr = new JSONArray();
        points.forEach(p -> {
            pointsArr.put(p.toJSON());
        });

        root.put("points", pointsArr);
    }

    /**
     * helper method for deserialization, sets the start and coordinates from the given JSONObject
     * @param root - the JSONObject to get the coords from
     */
    @Override
    protected void setCoordsFromJSON(JSONObject root){
        JSONArray pointsArr = root.getJSONArray("points");
        for(int i = 0; i < pointsArr.length(); i++){
            points.add(new Point(pointsArr.getJSONObject(i)));
        }
    }

    /**
     * converts this object to a JSON string
     * implementation of method defined in StratElement
     * @return - the JSONObject representing this object
     */
    public JSONObject toJSON(){
        JSONObject root = new JSONObject();
        insertProperties(root);

        return root;
    }

    /**
     * imports the properties of this object from a JSON string
     * implementation of method defined in StratElement
     * @param root - the JSONObject representing the object
     */
    public void importFromJSON(JSONObject root){
        setPropertiesFromJSON(root);
    }

    /**
     * implementation of abstract method defined in StratElement
     * draws this tool in the given GraphicsContext
     * @param gc - the GraphicsContext in which to draw the element
     */
    public void draw(GraphicsContext gc){
        gc.setStroke(color);
        for(int i = 0; i < points.size() - 1; i++){
            Point a = points.get(i);
            Point b = points.get(i + 1);
            gc.strokeLine(a.x, a.y, b.x, b.y);
        }
    }

    /**
     * get the coordinates of the beginning point of this tool
     * @return - a Point representing the start coordinates of the tool
     */
    @Override
    public Point getStart(){
        return points.size() != 0 ? points.get(0) : new Point(0,0);
    }

    /**
     * get the coordinates of the ending point of this tool
     * @return - a Point representing the end coordinates of the tool
     */
    @Override
    public Point getEnd(){
        return points.size() != 0 ? points.get(points.size() - 1) : new Point(0,0);
    }

    /*
    These methods have been intentionally overridden to do nothing
    since this class uses a different method for storing its points
     */
    @Override
    public void setCoords(double x1, double y1, double x2, double y2){/* INTENTIONALLY BLANK */}

    @Override
    public void setStart(double x, double y){/* INTENTIONALLY BLANK */}

    @Override
    public void setEnd(double x, double y){/* INTENTIONALLY BLANK */}
}
