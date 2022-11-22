package src;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class CrossModel extends PieceModel {

    protected Shape shape = new Polygon(
        0, 5,
        5, 0,
        50, 45,
        95, 0,
        100, 5,
        55, 50,
        100, 95,
        95, 100,
        50, 55,
        5, 100,
        0, 95,
        45, 50
    );

    public CrossModel(GameController controller) {
        super(controller);
    }

    protected Shape getShape() {
        return this.shape;
    }

}
