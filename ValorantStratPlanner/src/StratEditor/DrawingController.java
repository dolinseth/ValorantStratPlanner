package StratEditor;

import Records.Point;
import StratElements.OnePointStratElement;
import StratElements.StratElement;
import StratElements.TwoPointStratElement;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class DrawingController {
    private static DrawingController drawingController;
    private enum State {PRE_ONE_POINT, PRE_TWO_POINT, IN_TWO_POINT}
    private State curState;
    private OnePointStratElement curOnePoint;
    private TwoPointStratElement curTwoPoint;
    private ArrayList<StratElement> elements = new ArrayList<StratElement>();
    private GraphicsContext gc;
    private Canvas canvas;
    private Class curElement;

    private DrawingController(){}

    private void resetCurrentElement(){
        if(curElement.getSuperclass() == OnePointStratElement.class){
            curState = State.PRE_ONE_POINT;
            try {
                curOnePoint = (OnePointStratElement)curElement.newInstance();
            } catch(Exception e){
                System.out.println("Error initializing OnePointStratElement");
            }
        }
        else if(curElement.getSuperclass() == TwoPointStratElement.class){
            curState = State.PRE_TWO_POINT;
            try{
                curTwoPoint = (TwoPointStratElement)curElement.newInstance();
            } catch(Exception e){
                System.out.println("Error initializing TwoPointStratElement");
            }
        }
    }

    public void setCurrentElement(Class c){
        this.curElement = c;
        resetCurrentElement();
    }

    public static DrawingController getInstance(){
        if(drawingController == null){
            drawingController = new DrawingController();
        }
        return drawingController;
    }

    public void handleHover(Point p){
        if(curState == State.IN_TWO_POINT){
            curTwoPoint.setEnd(p.x, p.y);
        }
    }

    public void handleClick(Point p){
        if(curState == State.IN_TWO_POINT){
            curState = State.PRE_TWO_POINT;
            elements.add(curTwoPoint);
            curTwoPoint = null;
        }
        else if(curState == State.PRE_TWO_POINT){
            curState = State.IN_TWO_POINT;
            curTwoPoint.setStart(p.x, p.y);
        }
        else if(curState == State.PRE_ONE_POINT){
            curOnePoint.setCoords(p.x, p.y);
            elements.add(curOnePoint);
            curOnePoint = null;
        }
    }

    public void drawElements(){
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        elements.stream().forEach(e ->{
            e.draw(gc);
        });
    }

    /*
    GETTERS AND SETTERS
     */

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public void setCurOnePoint(OnePointStratElement el){
        this.curOnePoint = el;
    }

    public void setCurTwoPoint(TwoPointStratElement el){
        this.curTwoPoint = el;
    }
}
