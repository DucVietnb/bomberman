package uet.oop.bomberman.entities.ai;

import uet.oop.bomberman.entities.bomber.Bomber;
import uet.oop.bomberman.entities.bomber.weapon.Bomb;
import uet.oop.bomberman.entities.staticentities.Wall;
import uet.oop.bomberman.graphics.Point;

public abstract class AI {
    Bomber target = null;
    Bomb bomb;
    int currentDirection = 1;

    public Bomb getBomb() {
        return bomb;
    }

    public void setBomb(Bomb bomb) {
        this.bomb = bomb;
    }

    public Bomber getTarget() {
        return target;
    }

    public void setTarget(Bomber target) {
        this.target = target;
    }

    public abstract int calculateDirection(double ... p);


}
