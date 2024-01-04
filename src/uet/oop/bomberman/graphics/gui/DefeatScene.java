package uet.oop.bomberman.graphics.gui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class DefeatScene extends Scene {
    public AnchorPane root;
    ImageView imageView;
    public Button btnReplay, btnHome;
    public Label score;

    public DefeatScene(AnchorPane root) {
        super(root);
        this.root = root;
        addControls();

    }

    private void addControls() {
        root.setPrefSize(992,416);
        root.getStylesheets().add("file:src/uet/oop/bomberman/graphics/gui/css/VictoryScene.css");

        Image img = new Image("file:res/images/Defeat.png");
        imageView = new ImageView(img);
        btnReplay = new Button("Replay");
        btnHome = new Button("Home");
        score = new Label();

        root.getChildren().addAll(imageView, btnHome, btnReplay, score);

        btnReplay.setLayoutY(300);
        btnReplay.setLayoutX(400);
        btnHome.setLayoutY(300);
        btnHome.setLayoutX(470);
        score.setLayoutX(364);
        score.setLayoutY(180);
    }
}
