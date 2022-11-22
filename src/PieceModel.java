package src;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

abstract public class PieceModel {

    abstract protected Shape getShape();

    private StackPane attachedTo = null;
    private final GameController controller;

    protected void setupClickListener() {
        // Notify the controller whenever this piece is clicked
        EventHandler<MouseEvent> eventHandler = e -> this.controller.onPieceClick(this);
        this.getShape().addEventHandler(MouseEvent.MOUSE_PRESSED, eventHandler);
    }

    public PieceModel(GameController controller) {
        this.controller = controller;
    }

    public boolean attachTo(StackPane pane) {
        boolean setupClickListener = this.attachedTo == null;

        if(pane.getChildren().size() > 0) {
            return false;
        }

        pane.getChildren().add(this.getShape());

        this.attachedTo = pane;

        if(setupClickListener) {
            this.setupClickListener();
        }

        return true;
    }

    public void detach() {
        this.attachedTo.getChildren().clear();
    }

    public StackPane getAttachedTo() {
        return this.attachedTo;
    }

    public void highlightWin() {
        this.getShape().setFill(Color.GREEN);
    }

    public int isAttachedToAny(StackPane[] panes) {
        if(this.attachedTo == null) {
            return -1;
        }

        for(int i = 0; i < panes.length; i++) {
            if(panes[i].equals(this.attachedTo)) {
                return i;
            }
        }

        return -1;
    }

    public void select() {
        this.getShape().setFill(Color.RED);
    }

    public void unSelect() {
        this.getShape().setFill(Color.BLACK);
    }
}
