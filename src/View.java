package src;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.FileInputStream;
import java.io.IOException;

public class View extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        String fxmlPath = ClassLoader.getSystemResource("view.fxml").getPath();
        FileInputStream fxmlView = new FileInputStream(fxmlPath);
        Parent root = loader.load(fxmlView);

        GameController controller = loader.getController();
        controller.setView(this);
        controller.setup();

        primaryStage.setTitle("TicTacToe");

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
