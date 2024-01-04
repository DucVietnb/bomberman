package uet.oop.bomberman.entities.ai;

import java.util.Random;

public class AILow extends AI{
    @Override
    public int calculateDirection(double ... p) {
        Random rand = new Random();
        int direction = rand.nextInt(4) +1;
        return direction;
    }
}
