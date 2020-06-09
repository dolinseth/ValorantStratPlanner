package ElementDecorators;

import org.json.JSONObject;

public class ElementDecoratorFactory {
    private static ElementDecoratorFactory elementDecoratorFactory;

    /**
     * default constructor, used privately because this is a singleton class
     */
    private ElementDecoratorFactory(){}

    /**
     * returns the single instance of this class that is stored statically
     * and initializes that static instance if it has not been already
     * @return - the single instance of ElementDecoratorFactory
     */
    public static ElementDecoratorFactory getInstance(){
        if(elementDecoratorFactory == null){
            elementDecoratorFactory = new ElementDecoratorFactory();
        }
        return elementDecoratorFactory;
    }

    /**
     * returns an element decorator built from a JSONObject
     * @param root - the JSONObject describing the decorator to build
     * @return - the decorator described by root
     */
    public ElementDecorator getDecoratorFromJSON(JSONObject root){
        if(!root.has("type")){
            System.out.println(root.toString(2));
        }
        String type = root.getString("type");
        ElementDecorator eld = null;
        switch (type){
            case "AbilityIcon":
                eld = new AbilityIcon(root);
                break;
            case "Circle":
                eld = new Circle(root);
                break;
            case "Rectangle":
                eld = new Rectangle(root);
                break;
            case "ArrowHead":
                eld = new ArrowHead(root);
                break;
            case "TextBox":
                eld = new TextBox(root);
                break;
            case "ToolIcon":
                eld = new ToolIcon(root);
                break;
            case "FilledArc":
                eld = new FilledArc(root);
                break;
            case "Line":
                eld = new Line(root);
                break;
            case "CharacterIcon":
                eld = new CharacterIcon(root);
                break;
            case "TieredTrapezoid":
                eld = new TieredTrapezoid(root);
                break;
        }
        return eld;
    }
}
