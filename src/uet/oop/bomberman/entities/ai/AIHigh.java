package uet.oop.bomberman.entities.ai;

import uet.oop.bomberman.entities.bomber.weapon.Bomb;

public class AIHigh extends AIMedium{


    @Override
    public int calculateDirection(double ... p) {
        if (bomb!=null && target!=null){
            double x = p[0];
            double y = p[1];
            if (Math.abs(bomb.getY()-y) > MAX_OFFSET ) {
                if (bomb.getY() <= y && currentDirection !=1) {
                    currentDirection = 3;
                } else {
                    currentDirection = 1;
                }
            } else if (Math.abs(bomb.getX() - x) > MAX_OFFSET){
                if (bomb.getX() <= x && currentDirection !=4) {
                    //System.out.println("Here");
                    currentDirection = 2;
                } else {
                    currentDirection = 4;
                }
                return currentDirection;
            }
            return currentDirection;
        }
        else {
            return super.calculateDirection(p);
        }
    }
}
