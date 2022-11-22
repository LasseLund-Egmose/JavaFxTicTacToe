package src;

import javafx.scene.layout.StackPane;

public class CPU {

    protected GameController controller;

    protected void attachToRandom(StackPane[][] paneGrid, PieceModel model) {
        for(StackPane[] row : paneGrid) {
            for(StackPane pane : row) {
                if(pane.getChildren().size() > 0) {
                    continue;
                }

                model.attachTo(pane);
            }
        }
    }

    protected void moveTurn(StackPane[][] paneGrid, PieceModel[] CPUModels, PieceModel[] playerModels) {
        StackPane pane = GridHelper.getCPUMandatoryDefensivePane(paneGrid, playerModels);

        if(pane == null) {
            // Move first item in CPU models - bad strategy
            this.attachToRandom(paneGrid, CPUModels[0]);
            return;
        }

        // Move random CPU model to defend
        PieceModel model = CPUModels[(int) (Math.random() * 3)];
        model.attachTo(pane);
    }

    protected void setTurn(StackPane[][] paneGrid, PieceModel[] CPUModels, PieceModel[] playerModels) {
        StackPane pane = GridHelper.getCPUMandatoryDefensivePane(paneGrid, playerModels);

        CircleModel cm = new CircleModel(this.controller);

        this.controller.addCPUModel(cm);

        if(pane == null) {
            this.attachToRandom(paneGrid, cm);
            return;
        }

        cm.attachTo(pane);
    }

    public CPU(GameController controller) {
        this.controller = controller;
    }

    // Returns num CPU models
    public int takeTurn(StackPane[][] paneGrid, PieceModel[] CPUModels, PieceModel[] playerModels, int numCPUModels) {
        if(numCPUModels == 3) {
            this.moveTurn(paneGrid, CPUModels, playerModels);
            return numCPUModels;
        }

        this.setTurn(paneGrid, CPUModels, playerModels);

        return numCPUModels + 1;
    }

}
