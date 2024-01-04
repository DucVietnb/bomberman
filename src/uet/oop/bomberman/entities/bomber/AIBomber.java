package uet.oop.bomberman.entities.bomber;

import uet.oop.bomberman.GameController;
import uet.oop.bomberman.entities.StaticEntity;
import uet.oop.bomberman.graphics.Point;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AIBomber {
    // 10: Random move, 11: Place bomb, 12: Collect item, 13: Escape from bomb, 14: Follow enemy, 15: Reach portal
    int status=10;
    Bomber bomber;
    int currentDirection = 1;
    StaticEntity target;
    boolean isSafe = true;

    public static final double MAX_OFFSET = 5;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int calculateDirection(double x, double y) {
        //System.out.println("Bomber cal dir");
        if (status == 10) {
            int d;
            Random rand = new Random();
            do {
                d = rand.nextInt(4) +1;
                //System.out.println(d);
            } while (d == currentDirection);
            currentDirection = d;
        }
        if (status == 11) {
            if (GameController.layerBomb.size()<GameController.bombLimit) {
                System.out.println("Status 11");
                target = bomber.placeBomb();
                status = 13;
            }

        }
        if (status==12) {
            if (Math.abs(target.getY()-y) > MAX_OFFSET ) {
                System.out.println(Math.abs(target.getY()-y));
                if (target.getY() <= y && currentDirection !=1) {
                    currentDirection = 1;
                } else {
                    currentDirection = 3;
                }
            } else if (Math.abs(target.getX() - x) > MAX_OFFSET){
                if (target.getX() <= x && currentDirection !=4) {
                    System.out.println("Here");
                    currentDirection = 4;
                } else {
                    currentDirection = 2;
                }
            }
        }
        if (status == 13) {
            while (isSafe == false) {
                System.out.println("Here");
                if (new Bomber((double)x, y-bomber.getVelocity()* Sprite.SCALED_SIZE, Sprite.player_up.getFxImage()).canMove()
                && currentDirection != 1){
                    System.out.println("Change to 1");
                    currentDirection = 1;
                    break;
                }
                if (new Bomber((double)x+bomber.getVelocity()* Sprite.SCALED_SIZE, y, Sprite.player_up.getFxImage()).canMove()
                &&currentDirection!=2){
                    System.out.println("Change to 2");
                    currentDirection = 2;
                    break;
                }if (new Bomber((double)x, y+bomber.getVelocity()* Sprite.SCALED_SIZE, Sprite.player_up.getFxImage()).canMove()
                && currentDirection != 3){
                    System.out.println("Change to 3");
                    currentDirection = 3;
                    break;
                }if (new Bomber((double)x-bomber.getVelocity()* Sprite.SCALED_SIZE, y, Sprite.player_up.getFxImage()).canMove()
                && currentDirection != 4){
                    System.out.println("Change to 4");
                    currentDirection = 4;
                    break;
                }
            }

        }
        if (status == 14) {
            if (Math.abs(target.getY()-y) > MAX_OFFSET ) {
                System.out.println(Math.abs(target.getY()-y));
                if (target.getY() <= y && currentDirection !=1) {
                    currentDirection = 1;
                } else {
                    currentDirection = 3;
                }
            } else if (Math.abs(target.getX() - x) > MAX_OFFSET){
                if (target.getX() <= x && currentDirection !=4) {
                    System.out.println("Here");
                    currentDirection = 4;
                } else {
                    currentDirection = 2;
                }
            }
        }
        //System.out.println(currentDirection);
        return currentDirection;
    }
}
