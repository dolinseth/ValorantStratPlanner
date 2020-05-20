package CharacterAbilities;

import DataLayer.DataController;
import Main.AppController;
import Records.Point;
import Shapes.AbilityIcon;
import Shapes.Circle;
import Shapes.Line;
import StratElements.StratElement;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class CharacterAbility extends StratElement implements Cloneable{
    private ArrayList<Point> points = new ArrayList<Point>();
    private Image icon;
    private Color color;
    private double effectRadius;
    private String ability;

    public CharacterAbility(Color color, double effectRadius, String ability){
        icon = AppController.getInstance().getData().getAbilityImage(ability);
        this.color = color;
        this.effectRadius = effectRadius;
        this.ability = ability;
    }

    public void addPoint(Point p){
        points.add(p);
    }

    public void setLastPoint(Point p){
        Point last = points.get(points.size() - 1);
        last.x = p.x;
        last.y = p.y;
    }

    public void setLastPoint(double x, double y){
        Point last = points.get(points.size() - 1);
        last.x = x;
        last.y = y;
    }

    public void addPoint(double x, double y){
        points.add(new Point(x, y));
    }

    public CharacterAbility clone(){
        CharacterAbility ret = new CharacterAbility(color, effectRadius, ability);
        return ret;
    }

    public void draw(GraphicsContext gc){
        for(int i = 0; i < points.size(); i++){
            Point p = points.get(i);
            if(i == points.size() - 1){
                AbilityIcon.draw(gc, ability, p.x, p.y);
                Circle.draw(gc, p.x, p.y, effectRadius, color);
                //draw the arrow head
            }
            if(i == 0){
                Circle.draw(gc, p.x, p.y, 10, color);
            }
            else{
                Point p2 = points.get(i + 1);
                Line.draw(gc, p.x, p.y, p2.x, p2.y, color);
            }
        }
    }
}
