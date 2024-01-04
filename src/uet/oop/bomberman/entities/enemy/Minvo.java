package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Minvo extends Oneal{
    public Minvo(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void move(int direction) {
        super.move(direction);
    }

    @Override
    public void moveLeft() {

        double xt = x - velocity;
        if (canMove()) {
            this.setImg(Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, animate, time).getFxImage());
            this.setX(xt);
            direction = 4;
        }
    }

    @Override
    public void moveDown() {

        double yt = y + velocity;
        if (canMove()) {
            this.setImg(Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, animate, time).getFxImage());
            this.setY(yt);
            direction = 3;
        }
    }

    @Override
    public void moveRight() {

        double xt = x + velocity;
        if (canMove()) {
            this.setImg(Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, animate, time).getFxImage());
            this.setX(xt);
            direction = 2;
        }
    }

    @Override
    public void moveUp() {

        double yt = y - velocity;
        if (canMove()) {
            this.setImg(Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, animate, time).getFxImage());
            this.setY(yt);
            direction = 1;
        }
    }

    @Override
    public void beKilled() {
        if (timeBeforeDeath==60) {
            isAlive = false;
            System.out.println("Kondoria dead");
            setImg(Sprite.minvo_dead.getFxImage());
            setVelocity(0);
        }
        this.setImg(Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead1, Sprite.mob_dead2, timeBeforeDeath, 20).getFxImage());
        super.beKilled();
    }
}
