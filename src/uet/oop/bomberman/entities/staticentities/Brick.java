package uet.oop.bomberman.entities.staticentities;

import javafx.scene.image.Image;
import uet.oop.bomberman.GameController;
import uet.oop.bomberman.entities.StaticEntity;
import uet.oop.bomberman.entities.bomber.weapon.Flame;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

public class Brick extends StaticEntity {
    protected int timeToMelt = 60;
    protected boolean isMolten = false;
    Flame flameBurning;

    public Brick(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        if (isMolten) {
            melt();

        } else
            for (int j=0 ; j< GameController.layerBomb.size(); j++) {
                List<Flame> flameDowns = GameController.layerBomb.get(j).getFlameDowns();
                List<Flame> flameTops = GameController.layerBomb.get(j).getFlameTops();
                List<Flame> flameLefts = GameController.layerBomb.get(j).getFlameLefts();
                List<Flame> flameRights = GameController.layerBomb.get(j).getFlameRights();
                for (int i=0 ; i<flameDowns.size(); i++) {
                    if (this.collide(flameDowns.get(i))) {
                        isMolten = true;
                        for (int k = i + 1; k < flameDowns.size(); k++) {
                            flameDowns.remove(k);
                            k--;
                        }
                    }
                }
                for (int i=0 ; i<flameTops.size(); i++) {
                    if (this.collide(flameTops.get(i))) {
                        isMolten = true;
                        for (int k = i + 1; k < flameTops.size(); k++) {
                            flameTops.remove(k);
                            k--;
                        }
                    }
                }
                for (int i=0 ; i<flameLefts.size(); i++) {
                    if (this.collide(flameLefts.get(i))) {
                        isMolten = true;
                        for (int k = i + 1; k < flameLefts.size(); k++) {
                            flameLefts.remove(k);
                            k--;
                        }
                    }
                }
                for (int i=0 ; i<flameRights.size(); i++) {
                    if (this.collide(flameRights.get(i))) {
                        isMolten = true;
                        for (int k = i+1; k<flameRights.size(); k++) {
                            flameRights.remove(k);
                            k--;
                        }
                    }
                }

            }

    }

    private void melt() {
        timeToMelt--;
        setImg(Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2, timeToMelt, 21).getFxImage());
        if (timeToMelt==0) {
            GameController.layerBrick.remove(this);
            //GameController.stillObjects.remove(this);
        }
    }
}
