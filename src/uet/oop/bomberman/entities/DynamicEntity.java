package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.GameController;
import uet.oop.bomberman.entities.bomber.weapon.Bomb;
import uet.oop.bomberman.entities.bomber.weapon.Flame;
import uet.oop.bomberman.entities.staticentities.Brick;
import uet.oop.bomberman.entities.staticentities.Wall;

import java.util.List;

public abstract class DynamicEntity extends StaticEntity{

    protected int animate=0;
    public static final int MAX_ANIMATE = 2000;
    protected double velocity=1.5;
    protected int time = 20;
    protected int direction=1; // Up = 1; Right = 2; Down =3; Left = 4
    protected int timeToChangeDirection = 50;
    protected boolean isAlive = true;

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public DynamicEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);

    }

    public DynamicEntity(double x, double y, Image img) {
        super(x, y, img);
    }

    public int getAnimate() {
        return animate;
    }

    public void setAnimate(int animate) {
        this.animate = animate;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    @Override
    public void update() {
        animate++;

        if (animate>=MAX_ANIMATE) {
            animate = 0;
        }
        for (int j=0 ; j< GameController.layerBomb.size(); j++) {
            List<Flame> flameDowns = GameController.layerBomb.get(j).getFlameDowns();
            List<Flame> flameTops = GameController.layerBomb.get(j).getFlameTops();
            List<Flame> flameLefts = GameController.layerBomb.get(j).getFlameLefts();
            List<Flame> flameRights = GameController.layerBomb.get(j).getFlameRights();
            for (int i=0 ; i<flameDowns.size(); i++) {
                if (this.collide(flameDowns.get(i))) {
                    beKilled();
                }
            }
            for (int i=0 ; i<flameTops.size(); i++) {
                if (this.collide(flameTops.get(i))) {
                    beKilled();
                }
            }
            for (int i=0 ; i<flameLefts.size(); i++) {
                if (this.collide(flameLefts.get(i))) {
                    beKilled();
                }
            }
            for (int i=0 ; i<flameRights.size(); i++) {
                if (this.collide(flameRights.get(i))) {
                    beKilled();
                }
            }

        }
        for (int i=0; i<GameController.layerBomb.size(); i++) {
            Bomb bomb = GameController.layerBomb.get(i);
            if (collide(bomb) && bomb.isExploded) {
                beKilled();
            }
        }
    }


    @Override
    public boolean collide(StaticEntity other) {
        if (getBounds().intersects(other.getBounds())) {
            //System.out.println("Collide");
            return true;
        }
        return false;
    }

    public boolean canMove() {
        if (!isAlive) {
            return false;
        }
        List<StaticEntity> staticEntities = GameController.stillObjects;
        for(int i = 0; i < staticEntities.size(); i++) {
            while (this.collide(staticEntities.get(i)) && staticEntities.get(i) instanceof Wall) {
                //System.out.println("Can't move");
                if (direction == 1) {
                    y += velocity;
                } else if (direction == 2) {
                    x -= velocity;
                } else if (direction == 3) {
                    y -= velocity;
                } else if (direction == 4) {
                    x += velocity;
                }
                return false;
            }
//            if (this.collide(staticEntities.get(i)) && staticEntities.get(i) instanceof Wall) {
//                return false;
//            }
        }
        List<Brick> bricks = GameController.layerBrick;
        for (int i=0; i<bricks.size(); i++) {
            while (this.collide(bricks.get(i))) {
                if (direction == 1) {
                    y += velocity;
                } else if (direction == 2) {
                    x -= velocity;
                } else if (direction == 3) {
                    y -= velocity;
                } else if (direction == 4) {
                    x += velocity;
                }
                return false;
            }
//            if (this.collide(bricks.get(i))) {
//                return false;
//            }
        }

        return true;
    }

    public void move(KeyEvent keyEvent) {};
    public void move(int direction) {

    }

    public abstract void beKilled();


}





