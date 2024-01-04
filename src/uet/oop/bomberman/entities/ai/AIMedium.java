package uet.oop.bomberman.entities.ai;

import java.util.Random;

public class AIMedium extends AILow{

    public static final double MAX_OFFSET = 5;

    @Override
    public int calculateDirection(double ... p) {
        if (target == null) {
            //System.out.println("Target null");
            return super.calculateDirection();
        }
        else if (p.length == 2){
            //System.out.println("Reach target");
            double x = p[0];
            double y = p[1];
//            if (target.getY() - y  <= -MAX_OFFSET  && currentDirection !=1) {
//                currentDirection = 1;
//            } else if (target.getX() - x >= MAX_OFFSET && currentDirection!=2) {
//                currentDirection = 2;
//            } else if (target.getY()  - y > MAX_OFFSET && currentDirection!=3) {
//                currentDirection = 3;
//            } else if (target.getX() -x < -MAX_OFFSET  && currentDirection!=4) {
//                currentDirection = 4;
//            }
//            else {
//                System.out.println("Here");
//                Random rd = new Random();
//                int d;
//                while ((d=rd.nextInt(4)+1) != currentDirection) {
//                    currentDirection = d;
//                }
//            }
            if (Math.abs(target.getY()-y) > MAX_OFFSET ) {
                //System.out.println(Math.abs(target.getY()-y));
                if (target.getY() <= y && currentDirection !=1) {
                    currentDirection = 1;
                } else {
                    currentDirection = 3;
                }
            } else if (Math.abs(target.getX() - x) > MAX_OFFSET){
                if (target.getX() <= x && currentDirection !=4) {
                    //System.out.println("Here");
                    currentDirection = 4;
                } else {
                    currentDirection = 2;
                }
            }

            return currentDirection;
        }
        return currentDirection;
    }


}
