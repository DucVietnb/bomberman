package uet.oop.bomberman.graphics.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import uet.oop.bomberman.GameController;
import uet.oop.bomberman.Main;

import java.awt.*;

public class HomeScene extends Scene {
    public Label btnStart, btnExit, btnOption;
    AnchorPane root;
    ImageView imageView;

    public HomeScene(AnchorPane root) {
        super(root);
        this.root = root;
        addControls();
        addEvents();
    }
    public void addControls() {
        Font.loadFont("file:res/font/UglyByteFreeTrial.otf", 10);
        AnchorPane anchorPane = root;
        anchorPane.setPrefSize(992,416);
        root.getStylesheets().add("file:src/uet/oop/bomberman/graphics/gui/css/HomeScene.css");
        btnStart = new Label("Start");
        btnOption = new Label("Music on");
        btnExit = new Label("Exit");
        Image img = new Image("file:res/images/Bomberman.png");
        imageView = new ImageView(img);


        anchorPane.getChildren().add(imageView);
        anchorPane.getChildren().add(btnStart);
        anchorPane.getChildren().add(btnOption);
        anchorPane.getChildren().add(btnExit);
        btnOption.setLayoutX(476);
        btnExit.setLayoutX(476);
        btnStart.setLayoutX(476);
        btnStart.setLayoutY(150);
        btnOption.setLayoutY(190);
        btnExit.setLayoutY(230);

    }
    public void addEvents() {
        btnStart.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnStart.setTextFill(Color.AQUA);
            }
        });
        btnStart.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnStart.setTextFill(Color.BLACK);
            }
        });
        btnOption.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnOption.setTextFill(Color.AQUA);
            }
        });
        btnOption.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnOption.setTextFill(Color.BLACK);
            }
        });
        btnExit.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnExit.setTextFill(Color.AQUA);
            }
        });
        btnExit.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnExit.setTextFill(Color.BLACK);
            }
        });
    }


}
