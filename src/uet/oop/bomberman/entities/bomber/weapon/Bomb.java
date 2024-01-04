package uet.oop.bomberman.entities.bomber.weapon;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.GameController;
import uet.oop.bomberman.entities.StaticEntity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Bomb extends StaticEntity {
    public int timeToExplode = 180;
    protected int power = GameController.power;
    protected List<Flame> flameRights = new ArrayList<>();
    protected List<Flame> flameLefts = new ArrayList<>();
    protected List<Flame> flameTops = new ArrayList<>();
    protected List<Flame> flameDowns = new ArrayList<>();
    public boolean isExploded = false;
//    protected Flame flameTopLast, flameDownLast, flameLeftLast, flameRightLast;


    public Bomb(double x, double y, Image img) {
        super(x, y, img);
//        flameTopLast = new Flame();
//        flameDownLast = new Flame();
//        flameLeftLast = new Flame();
//        flameRightLast = new Flame();
    }


    @Override
    public void update() {

        //System.out.println("Time countdown:" + timeToExplode);
        timeToExplode--;
        if (timeToExplode>60) {

            this.setImg(Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, timeToExplode, 20).getFxImage());
        }
        else if (timeToExplode==60) {
            isExploded = true;
            GameController.audio.playBombExplode();
            for (int i=1; i<=power; i++) {
                if (i == power) {
                    //flameTopLast = new Flame(x, y - i * Sprite.SCALED_SIZE, Sprite.explosion_vertical_top_last.getFxImage());
                    flameTops.add(new Flame(x, y - i * Sprite.SCALED_SIZE, Sprite.explosion_vertical_top_last.getFxImage()));
                    //flameDownLast = new Flame(x, y + i * Sprite.SCALED_SIZE, Sprite.explosion_vertical_down_last.getFxImage());
                    flameDowns.add(new Flame(x, y + i * Sprite.SCALED_SIZE, Sprite.explosion_vertical_down_last.getFxImage()));
                    //flameLeftLast = new Flame(x - i * Sprite.SCALED_SIZE, y, Sprite.explosion_horizontal_left_last.getFxImage());
                    flameLefts.add(new Flame(x - i * Sprite.SCALED_SIZE, y, Sprite.explosion_horizontal_left_last.getFxImage()));
                    //flameRightLast = new Flame(x + i * Sprite.SCALED_SIZE, y, Sprite.explosion_horizontal_right_last.getFxImage() );
                    flameRights.add(new Flame(x + i * Sprite.SCALED_SIZE, y, Sprite.explosion_horizontal_right_last.getFxImage() ));
                } else {
                    flameTops.add(new Flame(x, y - i * Sprite.SCALED_SIZE, Sprite.explosion_vertical.getFxImage()));
                    flameDowns.add(new Flame(x, y + i * Sprite.SCALED_SIZE, Sprite.explosion_vertical.getFxImage()));
                    flameLefts.add(new Flame(x - i * Sprite.SCALED_SIZE, y, Sprite.explosion_horizontal.getFxImage()));
                    flameRights.add(new Flame(x + i * Sprite.SCALED_SIZE, y, Sprite.explosion_horizontal.getFxImage()));

                }

            }
        } else if (timeToExplode<60 && timeToExplode>0) {
            explode();
        }
        else if (timeToExplode==0) {
            GameController.layerBomb.remove(this);
        }


    }

    private void explode() {
        this.setImg(Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2,timeToExplode, 20).getFxImage());
//        flameRightLast.setImg(Sprite.movingSprite(Sprite.explosion_horizontal_right_last,Sprite.explosion_horizontal_right_last1, Sprite.explosion_horizontal_right_last2, timeToExplode, 20).getFxImage());
//        flameLeftLast.setImg(Sprite.movingSprite(Sprite.explosion_horizontal_left_last,Sprite.explosion_horizontal_left_last1, Sprite.explosion_horizontal_left_last2, timeToExplode, 20).getFxImage());
//        flameTopLast.setImg(Sprite.movingSprite(Sprite.explosion_vertical_top_last,Sprite.explosion_vertical_top_last1, Sprite.explosion_vertical_top_last2, timeToExplode, 20).getFxImage());
//        flameDownLast.setImg(Sprite.movingSprite(Sprite.explosion_vertical_down_last,Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2, timeToExplode, 20).getFxImage());
        for (int i = 0; i< flameRights.size(); i++) {
            if (i == flameRights.size() - 1) {
                flameRights.get(i).setImg(Sprite.movingSprite(Sprite.explosion_horizontal_right_last,Sprite.explosion_horizontal_right_last1, Sprite.explosion_horizontal_right_last2, timeToExplode, 20).getFxImage());
            } else {
                flameRights.get(i).setImg(Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1, Sprite.explosion_horizontal2, timeToExplode, 20).getFxImage());
            }
        }

        for (int i = 0; i< flameLefts.size(); i++) {
            if (i == flameLefts.size() - 1) {
                flameLefts.get(i).setImg(Sprite.movingSprite(Sprite.explosion_horizontal_left_last,Sprite.explosion_horizontal_left_last1, Sprite.explosion_horizontal_left_last2, timeToExplode, 20).getFxImage());
            } else {
                flameLefts.get(i).setImg(Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1, Sprite.explosion_horizontal2, timeToExplode, 20).getFxImage());
            }
        }
        for (int i = 0; i< flameDowns.size(); i++) {
            if (i == flameDowns.size()-1) {
                flameDowns.get(i).setImg(Sprite.movingSprite(Sprite.explosion_vertical_down_last,Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2, timeToExplode, 20).getFxImage());
            }else {
                flameDowns.get(i).setImg(Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1, Sprite.explosion_vertical2, timeToExplode, 20).getFxImage());
            }        
        }
        for (int i = 0; i< flameTops.size(); i++) {
            if (i == flameTops.size()-1) {
                flameTops.get(i).setImg(Sprite.movingSprite(Sprite.explosion_vertical_top_last,Sprite.explosion_vertical_top_last1, Sprite.explosion_vertical_top_last2, timeToExplode, 20).getFxImage());
            } else{
                flameTops.get(i).setImg(Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1, Sprite.explosion_vertical2, timeToExplode, 20).getFxImage());
            }
        }


    }

    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
//        flameTopLast.render(gc);
//        flameDownLast.render(gc);
//        flameLeftLast.render(gc);
//        flameRightLast.render(gc);
//        flameVerticals.forEach(f -> f.render(gc));
//        flameHorizontals.forEach(f ->f.render(gc));
        for (int i=0; i<flameLefts.size(); i++) {
            flameLefts.get(i).render(gc);
        }
        for (int i=0; i<flameDowns.size(); i++) {
            flameDowns.get(i).render(gc);
        }
        for (int i=0; i<flameRights.size(); i++) {
            flameRights.get(i).render(gc);
        }
        for (int i=0; i<flameTops.size(); i++) {
            flameTops.get(i).render(gc);
        }
    }

    public List<Flame> getFlameRights() {
        return flameRights;
    }

    public List<Flame> getFlameLefts() {
        return flameLefts;
    }

    public List<Flame> getFlameTops() {
        return flameTops;
    }

    public List<Flame> getFlameDowns() {
        return flameDowns;
    }
}
