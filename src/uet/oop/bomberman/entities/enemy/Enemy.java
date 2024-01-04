package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.GameController;
import uet.oop.bomberman.entities.DynamicEntity;
import uet.oop.bomberman.entities.StaticEntity;
import uet.oop.bomberman.entities.ai.AI;
import uet.oop.bomberman.entities.ai.AILow;
import uet.oop.bomberman.entities.bomber.Bomber;

import java.util.Random;

public class Enemy extends DynamicEntity {

    public int animate_timeout;
    protected int timeBeforeDeath = 60;
    protected AI ai;
    int sight = 100;


    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setVelocity(1);
    }

    @Override
    public boolean collide(StaticEntity other) {
        return super.collide(other);

    }

    @Override
    public void update() {
        super.update();
        if (collide(GameController.bomber)) {
            GameController.bomber.beKilled();
        }
        if (isAlive) {
            move(direction);
            timeToChangeDirection --;

        } else {
            beKilled();
        }
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
    public void beKilled() {
        if (timeBeforeDeath==60) {
            GameController.audio.playEnemyDead();
            GameController.score += 100;
        }
        timeBeforeDeath--;
        if (timeBeforeDeath==0) {
            GameController.enemies.remove(this);
        }
    }

    public void moveLeft() {
    }

    public void moveDown() {

    }

    public void moveRight() {

    }

    public void moveUp() {
    }
}
