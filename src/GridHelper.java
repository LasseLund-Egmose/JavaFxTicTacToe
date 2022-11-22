package src;

import javafx.scene.layout.StackPane;

public class GridHelper {

    public static boolean check3InARow(StackPane[][] paneGrid, PieceModel[] models) {
        for (StackPane[] row : GridHelper.getRows(paneGrid)) {
            int count = 0;

            for (PieceModel model : models) {
                if (model == null) {
                    continue;
                }

                int attachedIndex = model.isAttachedToAny(row);

                if(attachedIndex >= 0) {
                    count++;
                }
            }

            if (count == 3) {
                return true;
            }
        }

        return false;
    }

    public static StackPane[][] getRows(StackPane[][] paneGrid) {
        return new StackPane[][] {
            // Rows
            new StackPane[] {paneGrid[0][0], paneGrid[0][1], paneGrid[0][2]},
            new StackPane[] {paneGrid[1][0], paneGrid[1][1], paneGrid[1][2]},
            new StackPane[] {paneGrid[2][0], paneGrid[2][1], paneGrid[2][2]},

            // Columns
            new StackPane[] {paneGrid[0][0], paneGrid[1][0], paneGrid[2][0]},
            new StackPane[] {paneGrid[0][1], paneGrid[1][1], paneGrid[2][1]},
            new StackPane[] {paneGrid[0][2], paneGrid[1][2], paneGrid[2][2]},

            // Diagonals
            new StackPane[] {paneGrid[0][0], paneGrid[1][1], paneGrid[2][2]},
            new StackPane[] {paneGrid[0][2], paneGrid[1][1], paneGrid[2][0]},
        };
    }

    public static StackPane getCPUMandatoryDefensivePane(StackPane[][] paneGrid, PieceModel[] playerModels) {
        for (StackPane[] row : GridHelper.getRows(paneGrid)) {
            int dangerousIndex = 6;

            for (PieceModel playerModel : playerModels) {
                if (playerModel == null) {
                    continue;
                }

                int attachedIndex = playerModel.isAttachedToAny(row);
                dangerousIndex -= attachedIndex + 1;
            }

            if (dangerousIndex <= 3 && dangerousIndex > 0 && row[dangerousIndex - 1].getChildren().size() == 0) {
                return row[dangerousIndex - 1];
            }
        }

        return null;
    }
}
