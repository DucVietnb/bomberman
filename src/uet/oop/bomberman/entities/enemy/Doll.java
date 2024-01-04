package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.GameController;
import uet.oop.bomberman.entities.ai.AIHigh;
import uet.oop.bomberman.entities.bomber.weapon.Bomb;
import uet.oop.bomberman.graphics.Point;
import uet.oop.bomberman.graphics.Sprite;

public class Doll extends Oneal{


    public Doll(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        ai = new AIHigh();
    }

    @Override
    public void update() {


            if (canMove()==false) {
                direction = ai.calculateDirection(x,y);
            }
            if (GameController.layerBomb.size()==0) {
                ai.setBomb(null);
            }

            super.update();
            for (int i = 0; i < GameController.layerBomb.size(); i++) {
                Bomb bomb = GameController.layerBomb.get(i);
                if (Point.distance(x, y, bomb.getX(), bomb.getY()) < sight) {
                    //System.out.println("Avoid bomb");
                    ai.setBomb(bomb);
                    direction = ai.calculateDirection(x, y);
                    //System.out.println(direction);
                }
            }


    }

    @Override
    public void move(int direction) {
        super.move(direction);

    }

    @Override
    public void moveLeft() {
        double xt = x - velocity;
        if (canMove()) {
            this.setImg(Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, animate, time).getFxImage());
            this.setX(xt);
            direction = 4;
        }
    }

    @Override
    public void moveDown() {
        double yt = y + velocity;
        if (canMove()) {
            this.setImg(Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, animate, time).getFxImage());
            this.setY(yt);
            direction = 3;
        }
    }

    @Override
    public void moveRight() {
        double xt = x + velocity;
        if (canMove()) {
            this.setImg(Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, animate, time).getFxImage());
            this.setX(xt);
            direction = 2;
        }
    }

    @Override
    public void moveUp() {
        double yt = y - velocity;
        if (canMove()) {
            this.setImg(Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, animate, time).getFxImage());
            this.setY(yt);
            direction = 1;
        }
    }

    @Override
    public void beKilled() {
        if (timeBeforeDeath==60) {
            isAlive = false;
            System.out.println("Doll dead");
            setImg(Sprite.doll_dead.getFxImage());
            setVelocity(0);
        }
        this.setImg(Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead1, Sprite.mob_dead2, timeBeforeDeath, 20).getFxImage());
        super.beKilled();
    }
}
