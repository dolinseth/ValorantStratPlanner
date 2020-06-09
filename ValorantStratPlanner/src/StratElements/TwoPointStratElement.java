package StratElements;

import ElementDecorators.*;
import Records.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public abstract class TwoPointStratElement extends StratElement{
    protected double x1, y1, x2, y2;
    protected ArrayList<ElementDecorator> decorators = new ArrayList<ElementDecorator>();
    protected AdditionalPointHandler additionalPointHandler;
    protected double maxLength = -1;
    protected double minLength = -1;

    /**
     * helper function that allows additionalPointHandler functions to decide
     * to pass the additional point to all decorators
     * @param e - the mouse event to pass
     */
    public void passAdditionalPointsToDecorators(MouseEvent e){
        decorators.forEach(d -> d.handleAdditionalPoint(e));
    }

    /**
     * helper function that allows additionalPointHandler functions to
     * decide to selectively pass additional points to decorators by
     * providing a prototype decorator and a comparator function
     * and passing the points to any decorator d s.t. dc.compare(d, prototype) == true
     * @param e - the MouseEvent to pass to the decorators
     * @param prototype - the prototype decorator for comparison with
     * @param dc - the comparator function
     */
    public void passAdditionalPointsToDecorators(MouseEvent e, ElementDecorator prototype, DecoratorComparator dc){
        decorators.stream().filter(d -> dc.compare(prototype, d)).forEach(d -> handleAdditionalPoint(e));
    }

    /**
     * helper method for determining if this object needs additional points to be set
     * @return - a boolean indicating whether or not this object accepts additional points
     */
    public boolean hasAdditionalPoints(){
        return additionalPointHandler != null;
    }

    /**
     * adds a new decorator to this ability
     * @param elementDecorator - the decorator to add
     */
    public void addDecorator(ElementDecorator elementDecorator){
        elementDecorator.setParent(this);
        decorators.add(elementDecorator);
    }

    /**
     * helper method to get a reference to a certain decorator
     * by presenting a prototype decorator
     * and a function with which to compare them
     * @param eld - the prototype decorator
     * @param dc - the function with which to compare decorators
     * @return - the first decorator in the list s.t. dc.compare(eld, returned_value) == true
     */
    public ElementDecorator getDecorator(ElementDecorator eld, DecoratorComparator dc){
        return decorators.stream().filter(d -> dc.compare(eld, d)).findFirst().get();
    }

    /**
     * helper method to update decorator positions and draw them
     * @param gc - the GraphicsContext to draw the decorators in
     */
    protected void drawDecorators(GraphicsContext gc){
        decorators.forEach(d -> {
            d.updateElement();
            d.draw(gc);
        });
    }

    /**
     * sets the coordinates of this object
     * @param x1 - the start x coordinate
     * @param y1 - the start y coordinate
     * @param x2 - the end x coordinate
     * @param y2 - the end y coordinate
     */
    public void setCoords(double x1, double y1, double x2, double y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    /**
     * sets the start coordinates
     * @param x - the new start x coordinate
     * @param y - the new start y coordinate
     */
    public void setStart(double x, double y){
        x1 = x;
        y1 = y;
    }

    /**
     * sets the end coordinates
     * @param x - the new end x coordinate
     * @param y - the new end y coordinate
     */
    public void setEnd(double x, double y){
        x2 = x;
        y2 = y;
        if(maxLength != -1 && getLength() > maxLength){
            Point end = getStartOffsetByRadius(maxLength*2);
            x2 = end.x;
            y2 = end.y;
        }
        else if(minLength != -1 && getLength() < minLength){
            Point end = getStartOffsetByRadius(minLength * 2);
            x2 = end.x;
            y2 = end.y;
        }
    }

    /**
     * alternate setStart that uses a point instead of explicit coordinates
     * @param p - the point to set the start to
     */
    public void setStart(Point p){
        setStart(p.x, p.y);
    }

    /**
     * alternate setEnd that uses a point instead of explicit coordinates
     * @param p - the point to set the end to
     */
    public void setEnd(Point p){
        setEnd(p.x, p.y);
    }

    /**
     * helper method for serialization, inserts the start and end coordinates into the given JSONObject
     * @param root - the JSONObject into which the coords should be inserted
     */
    protected void insertCoords(JSONObject root){
        root.put("x1", x1);
        root.put("y1", y1);
        root.put("x2", x2);
        root.put("y2", y2);
    }

    /**
     * helper method for deserialization, sets the start and coordinates from the given JSONObject
     * @param root - the JSONObject to get the coords from
     */
    protected void setCoordsFromJSON(JSONObject root){
        x1 = root.getDouble("x1");
        y1 = root.getDouble("y1");
        x2 = root.getDouble("x2");
        y2 = root.getDouble("y2");
    }

    /**
     * helper method for serialization, inserts the decorator list into the given JSONObject
     * @param root - the JSONObject into which the decorator list should be inserted
     */
    protected void insertDecorators(JSONObject root){
        JSONArray decoratorJSON = new JSONArray();
        decorators.forEach(d -> {
            decoratorJSON.put(d.toJSON());
        });
        root.put("decorators", decoratorJSON);
    }

    /**
     * helper method for deserialization, sets the decorators from the given JSONObject
     * @param root - the JSONObject that contains a description of the decorators
     */
    protected void setDecoratorsFromJSON(JSONObject root){
        JSONArray decoratorsJSON = root.getJSONArray("decorators");
        for(int i = 0; i < decoratorsJSON.length(); i++){
            JSONObject decorator = decoratorsJSON.getJSONObject(i);
            ElementDecorator eld = ElementDecoratorFactory.getInstance().getDecoratorFromJSON(decorator);
            if(eld != null) {
                addDecorator(eld);
            }
        }
    }

    /**
     * helper method for drawing tools that include both a start circle and a line
     * returns a point that is offset to be at the edge of a circle with the given radius
     * from the start point (x1, y1)
     * @param radius - the radius to use for the offset math
     * @return - the point representing the offset coordinates
     */
    protected Point getStartOffsetByRadius(double radius){
        double angle = Math.atan2(y2 - y1, x2 - x1);
        double lineStartX = x1 + (radius / 2) * Math.cos(angle);
        double lineStartY = y1 + (radius / 2) * Math.sin(angle);
        return new Point(lineStartX, lineStartY);
    }

    /**
     * helper method for deserialization, sets all properties from the given JSONObject
     * @param root - the JSONObject containing all the properties
     */
    protected void setPropertiesFromJSON(JSONObject root){
        setCoordsFromJSON(root);
        setDecoratorsFromJSON(root);
        color = Color.web(root.getString("color"));
    }

    /**
     * helper method for serialization, inserts all properties into the given JSONObject
     * @param root - the JSONObject into which the properties should be inserted
     */
    protected void insertProperties(JSONObject root){
        insertCoords(root);
        insertDecorators(root);
        root.put("color", color);
    }

    /**
     * gets the length of the element measured as cartesian distance between (x1,y1) and (x2,y2)
     * @return - double representing the length
     */
    public double getLength(){
        double dx = x2 - x1;
        double dy = y2 - y1;
        return Math.sqrt(dx*dx + dy*dy);
    }

    /**
     * get the coordinates of the beginning point of this tool
     * @return - a Point representing the start coordinates of the tool
     */
    public Point getStart(){
        return new Point(x1, y1);
    }

    /**
     * get the coordinates of the ending point of this tool
     * @return - a Point representing the end coordinates of the tool
     */
    public Point getEnd(){
        return new Point(x2, y2);
    }

    /**
     * Set the function to be used to handle additional points
     * @param aph - a lambda body or anonymous class that implements the AdditionalPointHandler.handle() method
     */
    public void setAdditionalPointHandler(AdditionalPointHandler aph){
        additionalPointHandler = aph;
    }

    /**
     * Pass the mouse event to the AdditionalPointHandler of this class, or do nothing
     * if no AdditionalPointHandler has been set
     * @param e - the mouse event to pass to the function
     */
    public void handleAdditionalPoint(MouseEvent e){
        if(additionalPointHandler != null) {
            additionalPointHandler.handle(e);
        }
    }

    /*
    GETTERS AND SETTERS
     */

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getY1() {
        return y1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getY2() {
        return y2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    public double getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(double maxLength) {
        this.maxLength = maxLength;
    }

    public double getMinLength() {
        return minLength;
    }

    public void setMinLength(double minLength) {
        this.minLength = minLength;
    }
}
