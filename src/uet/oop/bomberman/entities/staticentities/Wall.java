package uet.oop.bomberman.entities.staticentities;

import javafx.scene.image.Image;
import uet.oop.bomberman.GameController;
import uet.oop.bomberman.entities.StaticEntity;
import uet.oop.bomberman.entities.bomber.weapon.Flame;

import java.util.List;

public class Wall extends StaticEntity {

    public Wall(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        for (int j=0 ; j< GameController.layerBomb.size(); j++) {
            List<Flame> flameDowns = GameController.layerBomb.get(j).getFlameDowns();
            List<Flame> flameTops = GameController.layerBomb.get(j).getFlameTops();
            List<Flame> flameLefts = GameController.layerBomb.get(j).getFlameLefts();
            List<Flame> flameRights = GameController.layerBomb.get(j).getFlameRights();
            for (int i=0 ; i<flameDowns.size(); i++) {
                if (this.collide(flameDowns.get(i))) {
                    for (int k = i ; k < flameDowns.size(); k++) {
                        flameDowns.remove(k);
                        k--;
                    }
                }
            }
            for (int i=0 ; i<flameTops.size(); i++) {
                if (this.collide(flameTops.get(i))) {
                    for (int k = i ; k < flameTops.size(); k++) {
                        flameTops.remove(k);
                        k--;
                    }
                }
            }
            for (int i=0 ; i<flameLefts.size(); i++) {
                if (this.collide(flameLefts.get(i))) {
                    for (int k = i ; k < flameLefts.size(); k++) {
                        flameLefts.remove(k);
                        k--;
                    }
                }
            }
            for (int i=0 ; i<flameRights.size(); i++) {
                if (this.collide(flameRights.get(i))) {
                    for (int k = i; k<flameRights.size(); k++) {
                        flameRights.remove(k);
                        k--;
                    }
                }
            }

        }
    }

}
