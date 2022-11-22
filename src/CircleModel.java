package src;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class CircleModel extends PieceModel {

    protected Shape shape = new Circle(50, 50, 30);

    public CircleModel(GameController controller) {
        super(controller);
    }

    public void select() {

    }

    public void unSelect() {

    }

    protected Shape getShape() {
        return this.shape;
    }

}
