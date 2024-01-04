package uet.oop.bomberman.entities.bomber;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.GameController;
import uet.oop.bomberman.entities.DynamicEntity;
import uet.oop.bomberman.entities.bomber.weapon.Bomb;
import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.entities.staticentities.Brick;
import uet.oop.bomberman.graphics.Point;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Bomber extends DynamicEntity {

    protected int timeBeforeGameOver = 60;
    int timeToChangeDirection = 100;
    int sight = 100;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        aiBomber = new AIBomber();
        aiBomber.bomber = this;
    }

    public Bomber(double x, double y, Image img) {
        super(x, y, img);
    }

    double xt=0, yt=0;
    int MAX_ACCELERATOR = 3;
    AIBomber aiBomber;

    @Override
    public void update() {
        if (isAlive == true) {
            super.update();
            if (GameController.aiBomberOn) {
                move(direction);
                MAX_ACCELERATOR = 1;
                velocity = 1;
                if (canMove()==false) {

                    direction = aiBomber.calculateDirection(x,y);
                    System.out.println("Status " +aiBomber.status);
                    if (aiBomber.getStatus()!=11) {
                        System.out.println("Change stat 11 Here");
                        for (int i=0; i<GameController.layerBrick.size(); i++) {
                            Brick brick = GameController.layerBrick.get(i);
                            if (Point.distance(x,y, brick.getX(), brick.getY())<50) {
                                System.out.println("Bomber collide brick");
                                aiBomber.setStatus(11);
                                direction = aiBomber.calculateDirection(x,y);
                            }
                        }
                    }
                    System.out.println(direction);
                }

                if (aiBomber.getStatus()==10) {
                    timeToChangeDirection --;
                    if (timeToChangeDirection == 0) {
                        Random random = new Random();
                        timeToChangeDirection = random.nextInt(400) + 200;
                        direction = aiBomber.calculateDirection(x, y);

                    }
                }


                if (aiBomber.getStatus()!=13) {
                    if (aiBomber.target instanceof Bomb) {
                        aiBomber.status = 13;
                        aiBomber.isSafe=false;
                    }
                }
                if (aiBomber.status == 13) {
                    if (Point.distance(x, y, aiBomber.target.getX(), aiBomber.target.getY())< GameController.power*2*Sprite.SCALED_SIZE) {
                        aiBomber.isSafe = false;
                    } else {
                        //System.out.println("Safe here");
                        aiBomber.isSafe = true;
                        setVelocity(0);
                    }
                    if (GameController.layerBomb.size()==0) {
                        setVelocity(1);
                        aiBomber.status = 10;
                        aiBomber.target = null;
                    }
                }
                if (aiBomber.status!=14 && aiBomber.status!=13) {
                    for (int i = 0; i < GameController.enemies.size(); i++) {
                        Enemy enemy = GameController.enemies.get(i);
                        if (Point.distance(x, y, enemy.getX(), enemy.getY()) <= sight) {
                            aiBomber.target = enemy;
                            aiBomber.status = 14;
                        }
                    }
                }
                if (aiBomber.status==14) {
                    double distanceFromTarget = Point.distance(x,y, aiBomber.target.getX(), aiBomber.target.getY());
                    if (distanceFromTarget <= sight/2) {
                        System.out.println("place bomb to destroy enemy");
                        aiBomber.status = 11;
                        aiBomber.isSafe = false;
                        direction = aiBomber.calculateDirection(x,y);

                    }

                    if (distanceFromTarget>sight) {
                        aiBomber.target = null;
                        aiBomber.status = 10;
                    }
                }

            }


        } else {
            beKilled();
        }

    }

//    @Override
//    public boolean collide(StaticEntity other) {
//        if (getBounds().intersects(other.getBounds()) && other instanceof Enemy) {
//            beKilled();
//            return true;
//        }
//        return super.collide(other);
//    }


    @Override
    public void beKilled() {
        if (timeBeforeGameOver==60) {
            isAlive = false;
            System.out.println("Player dead");
            setVelocity(0);
        } else if (timeBeforeGameOver==0) {
            GameController.gameOver();
        }
        this.setImg(Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, timeBeforeGameOver, 20).getFxImage());
        timeBeforeGameOver--;
    }


    @Override
    public void move(int direction) {
        super.move(direction);
        if (direction == 1) {
            moveUp();
        } else if (direction == 2) {
            moveRight();
        } else if (direction == 3) {
            moveDown();
        } else if (direction == 4) {
            moveLeft();
        }
    }

    @Override
    public void move(KeyEvent keyEvent) {

        KeyCode keyCode = keyEvent.getCode();
        if (keyCode == KeyCode.W || keyCode == KeyCode.UP) {
            moveUp();
        }
        if (keyCode == KeyCode.A || keyCode == KeyCode.LEFT) {
            moveLeft();
        }
        if (keyCode == KeyCode.D || keyCode == KeyCode.RIGHT) {
            moveRight();
        }
        if (keyCode == KeyCode.S || keyCode == KeyCode.DOWN) {
            moveDown();
        }
        if (keyCode == KeyCode.SPACE) {
            placeBomb();
        }
    }

    public Bomb placeBomb() {
        if (GameController.layerBomb.size()<GameController.bombLimit) {
            GameController.audio.playBombDrop();
            Bomb bomb = new Bomb(x, y, Sprite.bomb_1.getFxImage());
            GameController.layerBomb.add(bomb);
            return bomb;
        }
        return null;
    }

    private void moveDown() {

        if (direction == 3 && yt<MAX_ACCELERATOR) {
            yt ++;
        } else if (direction!=3){
            yt =0;
        }
        if (canMove()) {
            this.setImg(Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, animate, time).getFxImage());
            this.setY(y+yt*velocity);
            direction = 3;
        }


    }

    private void moveRight() {
        if (direction == 2 && xt<MAX_ACCELERATOR) {
            xt ++;
        } else if (direction!=2){
            xt =0;
        }

        if (canMove()) {
            this.setImg(Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, animate, time).getFxImage());
            this.setX(x + xt*velocity);
            direction = 2;
        }

    }

    private void moveLeft() {
        if (direction == 4 && xt>-MAX_ACCELERATOR) {
            xt --;
        } else if (direction!=4){
            xt =0;
        }

        if (canMove()) {
            this.setImg(Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, animate, time).getFxImage());
            this.setX(x + xt*velocity);
            direction = 4;
        }


    }

    private void moveUp() {
        if (direction == 1 && yt>-MAX_ACCELERATOR) {
            yt --;
        } else if (direction!=1){
            yt =0;
        }

        if (canMove()) {
            this.setImg(Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, animate, time).getFxImage());
            this.setY(y+yt*velocity);
            direction = 1;
        }

    }

    public void powerUp(String itemType) {
        if (itemType.equals("b")) {
            GameController.bombLimit++;
        } else if (itemType.equals("f")) {
            GameController.power++;
        } else if (itemType.equals("s")) {
            velocity*=2;
        }
    }
}
