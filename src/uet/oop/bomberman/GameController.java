package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.staticentities.*;
import uet.oop.bomberman.graphics.gui.DefeatScene;
import uet.oop.bomberman.graphics.gui.HomeScene;
import uet.oop.bomberman.graphics.gui.VictoryScene;
import uet.oop.bomberman.graphics.gui.controllers.Menu;
import uet.oop.bomberman.sound.Audio;
import uet.oop.bomberman.entities.bomber.Bomber;
import uet.oop.bomberman.entities.StaticEntity;
import uet.oop.bomberman.entities.bomber.weapon.Bomb;
import uet.oop.bomberman.entities.enemy.*;
import uet.oop.bomberman.graphics.Sprite;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GameController extends Application {
    
    public static int WIDTH=20;
    public static int HEIGHT=15;
    public static int bombLimit = 1;
    public static int power = 1;
    public  static int level = 1;
    public static int score=0;
    
    private static GraphicsContext gc;
    private static Canvas canvas;
    public static AnimationTimer timer;
    public static Button btnPause = new Button("Pause");
    public static Button btnContinue = new Button("Continue");
    public static MenuButton btnChooseLevel = new MenuButton("Level");
    public static Button btnAIOn = new Button("AI on");
    public static HomeScene homeScene;
    public static Scene gameScene;
    public static Stage primaryStage;

    public static List<StaticEntity> entities = new ArrayList<>();
    public static List<Enemy> enemies = new ArrayList<>();
    public static List<Grass> layerGrass = new ArrayList<>();
    public static List<Brick> layerBrick = new ArrayList<>();
    public static List<Item> layerItem = new ArrayList<>();
    public static List<Bomb> layerBomb = new ArrayList<>();
    public static List<StaticEntity> stillObjects = new ArrayList<>();
    public static List<Portal> layerPortal = new ArrayList<>();

    public static List<Item> items = new ArrayList<>();
    public static Bomber bomber;
    public static boolean aiBomberOn = false;

    public static Audio audio = new Audio();
    public static String fileLevel = "";
    public static Float volume = 1f;


    public GameController() {
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    public static void gameOver() {
        audio.stopAllSound();
        audio.playGameOver();
        timer.stop();
        AnchorPane anchorPane = new AnchorPane();
        DefeatScene defeatScene = new DefeatScene(anchorPane);
        primaryStage.setScene(defeatScene);
        defeatScene.score.setText("Your score: " + score);
        defeatScene.btnHome.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                returnHomeScene();
            }
        });
        defeatScene.btnReplay.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadMapFromFile(fileLevel);
                primaryStage.setScene(gameScene);
                timer.start();
            }
        });
    }

    public static void victory() {
        audio.stopAllSound();
        audio.playVictory();
        timer.stop();
        AnchorPane anchorPane = new AnchorPane();
        VictoryScene victoryScene = new VictoryScene(anchorPane);
        primaryStage.setScene(victoryScene);
        victoryScene.score.setText("Your score: " + score);
        victoryScene.btnReplay.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadMapFromFile(fileLevel);
                primaryStage.setScene(gameScene);
                timer.start();
            }
        });
        victoryScene.btnNextLevel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                changeLevel(++level);
                loadMapFromFile(fileLevel);
                primaryStage.setScene(gameScene);
                timer.start();
            }
        });
        victoryScene.btnHome.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                returnHomeScene();
            }
        });
    }

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        primaryStage.setResizable(false);
        setupHomeScene();
        primaryStage.show();
    }

    public static void returnHomeScene() {
        AnchorPane anchorPane = new AnchorPane();
        homeScene = new HomeScene(anchorPane);
        primaryStage.setScene(homeScene);
        homeScene.btnStart.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                changeLevel(1);
                loadMapFromFile(fileLevel);
                primaryStage.setScene(gameScene);
                timer.start();
            }
        });
        homeScene.btnExit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                exitGame();
            }
        });
        homeScene.btnOption.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (volume>0) {
                    volume = 0f;
                    homeScene.btnOption.setText("Music off");
                }
                else {
                    volume = 1f;
                    homeScene.btnOption.setText("Music on");
                }
            }
        });
    }

    public void setupHomeScene() {
        AnchorPane anchorPane = new AnchorPane();
        homeScene = new HomeScene(anchorPane);
        primaryStage.setScene(homeScene);
        homeScene.btnStart.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                loadGameScene();
            }
        });
        homeScene.btnExit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                exitGame();
            }
        });
        homeScene.btnOption.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (volume>0) {
                    volume = 0f;
                    homeScene.btnOption.setText("Music off");
                }
                else {
                    volume = 1f;
                    homeScene.btnOption.setText("Music on");
                }
            }
        });
    }


    public void loadGameScene() {
        fileLevel = String.format("res/levels/Level%d.txt", level);
        loadMapFromFile(fileLevel);
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        BorderPane pan = new BorderPane();
        FlowPane p = new FlowPane();
        p.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnPause.setVisible(true);
                btnAIOn.setVisible(true);
            }
        });
        p.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnPause.setVisible(false);
                btnAIOn.setVisible(false);
            }
        });
        btnPause.setVisible(false);
        p.getChildren().add(btnPause);
        p.getChildren().add(btnAIOn);
        p.getChildren().add(btnContinue);
        btnAIOn.setVisible(false);
        btnContinue.setVisible(false);
        p.getChildren().add(btnChooseLevel);
        btnChooseLevel.setVisible(false);
        MenuItem lv1 = new MenuItem("Level 1");
        lv1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                changeLevel(1);
                loadMapFromFile(fileLevel);
            }
        });
        MenuItem lv2 = new MenuItem("Level 2");
        lv2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                changeLevel(2);
                loadMapFromFile(fileLevel);
            }
        });
        MenuItem lv3 = new MenuItem("Level 3");
        lv3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                changeLevel(3);
                loadMapFromFile(fileLevel);
            }
        });
        MenuItem lv4 = new MenuItem("Level 4");
        lv4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                changeLevel(4);
                loadMapFromFile(fileLevel);
            }
        });
        MenuItem lv5 = new MenuItem("Level 5");
        lv5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                changeLevel(5);
                loadMapFromFile(fileLevel);
            }
        });
        btnChooseLevel.getItems().addAll(lv1, lv2, lv3, lv4, lv5);

        pan.setTop(p);
        btnPause.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                gamePause();
            }
        });
        btnAIOn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (aiBomberOn == false) {
                    aiBomberOn = true;
                    btnAIOn.setText("AI off");
                }
                else {
                    aiBomberOn = false;
                    btnAIOn.setText("AI on");
                }
            }
        });
        btnContinue.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                gameContinue();
            }
        });

        pan.setCenter(canvas);
        Parent parent = pan;
        gameScene = new Scene(pan);
        primaryStage.setScene(gameScene);

        primaryStage.show();

        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        entities.add(bomber);

        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                bomber.move(event);
            }
        });
    }

    private static void changeLevel(int level) {
        fileLevel = String.format("res/levels/Level%d.txt", level);
    }

    public static void gameContinue() {
        timer.start();
        btnContinue.setVisible(false);
        btnChooseLevel.setVisible(false);
    }

    public static void gamePause() {
        timer.stop();
        btnContinue.setVisible(true);
        btnChooseLevel.setVisible(true);
    }

    public static void loadMapFromFile(String path) {
        audio.stopAllSound();
        audio.playThemeSound();
        audio.setVolume(volume);
        try {
            String line = "";
            int i =0;
            layerGrass.clear();
            layerItem.clear();
            layerPortal.clear();
            layerBrick.clear();
            stillObjects.clear();
            entities.clear();
            enemies.clear();
            FileInputStream inputStream = new FileInputStream(path);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String[] properties = bufferedReader.readLine().split(" ");
            WIDTH = Integer.parseInt(properties[2]);
            HEIGHT = Integer.parseInt(properties[1]);
            while (i<HEIGHT) {
                line = bufferedReader.readLine();
                for (int j=0; j<line.length(); j++) {
                    layerGrass.add(new Grass(j,i, Sprite.grass.getFxImage()));
                    switch (line.charAt(j)) {
                        case '#': {
                            stillObjects.add(new Wall(j,i, Sprite.wall.getFxImage()));
                            break;
                        }
                        case 'x': {
                            layerBrick.add(new Brick(j,i, Sprite.brick.getFxImage()));
                            layerPortal.add(new Portal(j,i, Sprite.portal.getFxImage()));
                            break;
                        }
                        case '*': {
                            layerBrick.add(new Brick(j,i, Sprite.brick.getFxImage()));
                            break;
                        }
                        case 'b': {
                            layerBrick.add(new Brick(j,i, Sprite.brick.getFxImage()));
                            layerItem.add(new Item(j,i, Sprite.powerup_bombs.getFxImage(), "b"));
                            break;
                        }
                        case 'f': {
                            layerBrick.add(new Brick(j,i, Sprite.brick.getFxImage()));
                            layerItem.add(new Item(j,i, Sprite.powerup_flames.getFxImage(), "f"));
                            break;
                        }
                        case 's': {
                            layerBrick.add(new Brick(j,i, Sprite.brick.getFxImage()));
                            layerItem.add(new Item(j,i, Sprite.powerup_speed.getFxImage(), "s"));
                            break;
                        }
                        case 'p': {
                            bomber = new Bomber(j,i, Sprite.player_right.getFxImage());
                            break;
                        }
                        case '1': {
                            enemies.add(new Balloon(j, i, Sprite.balloom_left1.getFxImage()));
                            break;
                        }
                        case '2': {
                            enemies.add(new Oneal(j, i, Sprite.oneal_right1.getFxImage()));
                            break;
                        }
                        case '3': {
                            enemies.add(new Doll(j, i, Sprite.doll_left1.getFxImage()));
                            break;
                        }
                        case '4': {
                            enemies.add(new Minvo(j, i, Sprite.minvo_left1.getFxImage()));
                            break;
                        }
                        case '5': {
                            enemies.add(new Kondoria(j, i, Sprite.kondoria_left1.getFxImage()));
                            break;
                        }
                    }
                }
                i++;
            }
            //stillObjects.addAll(layerBrick);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void update() {
        entities.forEach(StaticEntity::update);
        for (int i=0; i<layerBomb.size(); i++) {
            layerBomb.get(i).update();
        }
        for (int i=0; i<enemies.size(); i++) {
            enemies.get(i).update();
        }
        for (int i=0; i<layerBrick.size(); i++) {
            layerBrick.get(i).update();
        }
        for (int i=0; i<stillObjects.size(); i++) {
            stillObjects.get(i).update();
        }
        for (int i=0; i<layerItem.size(); i++) {
            layerItem.get(i).update();
        }
        layerPortal.forEach(p -> p.update());
        bomber.update();

    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        layerGrass.forEach(l -> l.render(gc));
        layerItem.forEach(l -> l.render(gc));
        layerPortal.forEach(p -> p.render(gc));
        layerBrick.forEach(b -> b.render(gc));
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        enemies.forEach(e -> e.render(gc));
        layerBomb.forEach(b -> b.render(gc));
        bomber.render(gc);

    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void exitGame() {
        Platform.exit();
    }
}
