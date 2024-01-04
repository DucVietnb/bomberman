package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.GameController;
import uet.oop.bomberman.entities.ai.AILow;
import uet.oop.bomberman.entities.ai.AIMedium;
import uet.oop.bomberman.graphics.Point;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Oneal extends Enemy{



    public Oneal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        ai = new AIMedium();

    }

    @Override
    public void update() {
        super.update();
        //System.out.println("Oneal here");
        if (ai.getTarget() == null) {
            if (timeToChangeDirection==0 || canMove()==false) {
                timeToChangeDirection = new Random().nextInt(200) + 100;
                int d = ai.calculateDirection();
                while (d==direction) {
                    d = ai.calculateDirection();
                }
                direction = d;
            }
            if (Point.distance(x, y, GameController.bomber.getX(), GameController.bomber.getY()) < sight) {
                System.out.println("See bomber");
                setVelocity(this.getVelocity()*1.5);
                ai.setTarget(GameController.bomber);
            }
        }
        else {
//            if (canMove() == false || Math.abs(x-GameController.bomber.getX()) <5 || Math.abs(y - GameController.bomber.getY()) <5) {
//                System.out.println("Change direction");
//                direction = ai.calculateDirection(x, y);
//                System.out.println(direction);
//            }
            if (canMove() == false) {
                System.out.println("Collide jam");
                direction = ai.calculateDirection(x, y);
                System.out.println(direction);
            }
            if (Math.abs(y - GameController.bomber.getY()) < 5 && (direction!=2 && direction!=4)) {
                System.out.println("Math.abs(y-GameController.bomber.getY()) <5");
                direction = ai.calculateDirection(x, y);
                System.out.println(direction);
            }
            if (Math.abs(x - GameController.bomber.getX()) < 5 && (direction!=1 && direction !=3)) {
                System.out.println("Math.abs(x-GameController.bomber.getX()) <5");
                direction = ai.calculateDirection(x, y);
                System.out.println(direction);
            }

            if (Point.distance(x, y, GameController.bomber.getX(), GameController.bomber.getY()) > sight) {
                System.out.println("Bomber dissappeared");
                ai.setTarget(null);
                setVelocity(this.getVelocity()/1.5);
            }
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
            this.setImg(Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate, time).getFxImage());
            this.setX(xt);
            direction = 4;
        }
    }

    @Override
    public void moveDown() {
        super.moveDown();
        double yt = y + velocity;
        if (canMove()) {
            this.setImg(Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate, time).getFxImage());
            this.setY(yt);
            direction = 3;
        }
    }

    @Override
    public void moveRight() {
        super.moveRight();
        double xt = x + velocity;
        if (canMove()) {
            this.setImg(Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animate, time).getFxImage());
            this.setX(xt);
            direction = 2;
        }
    }

    @Override
    public void moveUp() {
        super.moveUp();
        double yt = y - velocity;
        if (canMove()) {
            this.setImg(Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animate, time).getFxImage());
            this.setY(yt);
            direction = 1;
        }
    }

    @Override
    public void beKilled() {
        if (timeBeforeDeath==60) {
            isAlive = false;
            System.out.println("Kondoria dead");
            setImg(Sprite.oneal_dead.getFxImage());
            setVelocity(0);
        }
        this.setImg(Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead1, Sprite.mob_dead2, timeBeforeDeath, 20).getFxImage());
        super.beKilled();
    }
}
