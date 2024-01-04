package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.GameController;
import uet.oop.bomberman.entities.ai.AILow;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Balloon extends Enemy{
    public Balloon(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        ai = new AILow();
    }

    @Override
    public void update() {
        super.update();
        if (timeToChangeDirection==0 || canMove() == false) {
            timeToChangeDirection = new Random().nextInt(200) + 100;
            int d = ai.calculateDirection();
            while (d==direction) {
                d = ai.calculateDirection();
            }
            direction = d;
        }
    }

    @Override
    public void move(int direction) {
        super.move(direction);

    }

    @Override
    public void moveLeft() {
        super.moveLeft();
        double xt = x - velocity;
        if (canMove()) {
            this.setImg(Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, time).getFxImage());
            this.setX(xt);
            direction = 4;
        }
    }

    @Override
    public void moveDown() {
        super.moveDown();
        double yt = y + velocity;
        if (canMove()) {
            this.setImg(Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, time).getFxImage());
            this.setY(yt);
            direction = 3;
        }


    }

    @Override
    public void moveRight() {
        super.moveRight();
        double xt = x + velocity;
        if (canMove()) {
            this.setImg(Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animate, time).getFxImage());
            this.setX(xt);
            direction = 2;
        }
    }

    @Override
    public void moveUp() {
        super.moveUp();
        double yt = y - velocity;
        if (canMove()) {
            this.setImg(Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, time).getFxImage());
            this.setY(yt);
            direction = 1;
        }
    }

    @Override
    public void beKilled() {
        if (timeBeforeDeath==60) {
            isAlive = false;
            System.out.println("Balloon dead");
            setImg(Sprite.balloom_dead.getFxImage());
            setVelocity(0);
        }
        this.setImg(Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead1, Sprite.mob_dead2, timeBeforeDeath, 20).getFxImage());
        super.beKilled();
    }
}
