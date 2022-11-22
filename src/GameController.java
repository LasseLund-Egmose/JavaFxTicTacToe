package src;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class GameController {

    @FXML
    private StackPane pane00, pane10, pane20, pane01, pane11, pane21, pane02, pane12, pane22;

    private StackPane[][] paneGrid;
    private View view;

    private boolean isPlayersTurn = true;

    private CircleModel[] CPUModels = new CircleModel[3];
    private CrossModel[] playerModels = new CrossModel[3];

    private int numCPUModels = 0;
    private int numPlayerModels = 0;

    private CrossModel selectedPiece = null;

    private CPU cpu;

    /*
     * Game brain
     */

    protected void CPUTurn() {
        this.isPlayersTurn = false;

        if(this.testGameIsWon()) {
            return;
        }

        this.numCPUModels = this.cpu.takeTurn(this.paneGrid, this.CPUModels, this.playerModels, this.numCPUModels);

        if(this.testGameIsWon()) {
            return;
        }

        this.isPlayersTurn = true;
    }

    protected boolean testGameIsWon() {
        if(this.testGameIsWonByCPU()) {
            for(PieceModel pm : this.CPUModels) {
                pm.highlightWin();
            }
            return true;
        }

        if(this.testGameIsWonByPlayer()) {
            for(PieceModel pm : this.playerModels) {
                pm.highlightWin();
            }
            return true;
        }

        return false;
    }

    protected boolean testGameIsWonByCPU() {
        return GridHelper.check3InARow(this.paneGrid, this.CPUModels);
    }

    protected boolean testGameIsWonByPlayer() {
        return GridHelper.check3InARow(this.paneGrid, this.playerModels);
    }

    /*
     * End game brain
     */

    public void addCPUModel(CircleModel cm) {
        this.CPUModels[this.numCPUModels] = cm;
    }

    protected void onPaneClick(StackPane pane) {
        if(!this.isPlayersTurn) {
            return;
        }

        if(this.numPlayerModels == 3) {
            if(pane.getChildren().size() == 0 && this.selectedPiece != null) {
                this.selectedPiece.detach();
                this.selectedPiece.attachTo(pane);

                this.unSelectAll();

                this.CPUTurn();
            }

            return;
        }

        CrossModel cm = new CrossModel(this);
        if(cm.attachTo(pane)) {
            this.playerModels[this.numPlayerModels] = cm;
            this.numPlayerModels++;

            this.unSelectAll();

            this.CPUTurn();
        }
    }

    protected void unSelectAll() {
        for(CrossModel cm : this.playerModels) {
            if(cm == null) {
                continue;
            }

            cm.unSelect();
        }

        this.selectedPiece = null;
    }

    public void onPieceClick(PieceModel p) {
        if(!this.isPlayersTurn || !(p instanceof CrossModel) || p.getAttachedTo() == null || this.numPlayerModels < 3) {
            return;
        }

        this.unSelectAll();

        this.selectedPiece = (CrossModel) p;
        p.select();
    }

    public void setup() {
        this.cpu = new CPU(this);

        this.setupGrid();
        this.setupEvents();
    }

    public void setupEvents() {
        for(int row = 0; row < paneGrid.length; row++) {
            for(int col = 0; col < paneGrid[row].length; col++) {
                StackPane pane = paneGrid[row][col];

                EventHandler<MouseEvent> eventHandler = e -> this.onPaneClick(pane);
                pane.addEventHandler(MouseEvent.MOUSE_PRESSED, eventHandler);
            }
        }
    }

    public void setupGrid() {
        this.paneGrid = new StackPane[][] {
            new StackPane[]{this.pane00, this.pane10, this.pane20},
            new StackPane[]{this.pane01, this.pane11, this.pane21},
            new StackPane[]{this.pane02, this.pane12, this.pane22}
        };
    }

    public void setView(View view) {
        this.view = view;
    }
}
