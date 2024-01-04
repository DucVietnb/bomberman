package uet.oop.bomberman.graphics.gui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class VictoryScene extends Scene {
    public Button btnReplay, btnNextLevel, btnHome;
    public Label score;
    AnchorPane root;
    ImageView imageView;

    public VictoryScene(AnchorPane root) {
        super(root);
        this.root = root;
        addControls();
    }

    private void addControls() {
        root.setPrefSize(992,416);
        root.getStylesheets().add("file:src/uet/oop/bomberman/graphics/gui/css/VictoryScene.css");

        score = new Label("Your score");
        btnReplay = new Button("Replay");
        btnNextLevel = new Button("Next level");
        btnHome = new Button("Home");
        Image img = new Image("file:res/images/Victory.png");
        imageView = new ImageView(img);

        root.getChildren().addAll(imageView,score, btnNextLevel, btnReplay, btnHome);

        btnReplay.setLayoutY(300);
        btnReplay.setLayoutX(400);
        btnHome.setLayoutY(300);
        btnHome.setLayoutX(470);
        btnNextLevel.setLayoutY(300);
        btnNextLevel.setLayoutX(530);
        score.setLayoutX(364);
        score.setLayoutY(180);

    }

}
