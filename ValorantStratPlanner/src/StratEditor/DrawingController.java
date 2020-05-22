//package StratEditor;
//
//import StratEditor.CharacterAbility;
//import DataLayer.DataController;
//import Records.Point;
//import StratElements.OnePointStratElement;
//import StratElements.StratElement;
//import StratElements.TwoPointStratElement;
//import javafx.scene.canvas.Canvas;
//import javafx.scene.canvas.GraphicsContext;
//
//import java.util.ArrayList;
//
//public class DrawingController {
//    private static DrawingController drawingController;
//    private enum State {IDLE, IN_PLACEMENT}
//    private State curState;
//    private CharacterAbility currentTool;
//    private ArrayList<CharacterAbility> elements = new ArrayList<CharacterAbility>();
//    private GraphicsContext gc;
//    private Canvas canvas;
//
//    private DrawingController(){}
//
//    public static DrawingController getInstance(){
//        if(drawingController == null){
//            drawingController = new DrawingController();
//        }
//        return drawingController;
//    }
//
//    public void handleHover(Point p){
//        if(curState == State.IN_PLACEMENT){
//            CharacterAbility cur = elements.get(elements.size() - 1);
//            cur.setLastPoint(p);
//        }
//    }
//
//    public void handleLeftClick(Point p){
//        if(curState == State.IDLE){
//            CharacterAbility cur = currentTool.clone();
//            cur.addPoint(p);
//            elements.add(cur);
//        }
//        else if(curState == State.IN_PLACEMENT){
//            elements.get(elements.size() - 1).addPoint(p);
//        }
//    }
//
//    public void handleRightClick(Point p){
//
//    }
//
//    public void drawElements(){
//        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
//        elements.stream().forEach(e ->{
//            e.draw(gc);
//        });
//    }
//
//    /*
//    GETTERS AND SETTERS
//     */
//
//    public Canvas getCanvas() {
//        return canvas;
//    }
//
//    public void setCanvas(Canvas canvas) {
//        this.canvas = canvas;
//        this.gc = canvas.getGraphicsContext2D();
//    }
//
//    public GraphicsContext getGc() {
//        return gc;
//    }
//
//    public void setCurrentTool(CharacterAbility el){
//        this.currentTool = el;
//        System.out.println("set current tool");
//    }
//
//    public ArrayList<CharacterAbility> getElements(){
//        return elements;
//    }
//}
