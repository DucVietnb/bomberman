package uet.oop.bomberman;


import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import uet.oop.bomberman.graphics.gui.HomeScene;
import uet.oop.bomberman.graphics.gui.VictoryScene;

public class Main extends Application {
    public Main() {
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        AnchorPane anchorPane = new AnchorPane();
        primaryStage.setScene(new VictoryScene(anchorPane));
        primaryStage.show();

    }

    public static void exit() {
        Platform.exit();
    }

    public void stop() throws IOException {
        System.out.println("STOP!!!");
    }
}
