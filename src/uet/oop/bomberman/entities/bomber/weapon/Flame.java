package uet.oop.bomberman.entities.bomber.weapon;

import javafx.scene.image.Image;
import uet.oop.bomberman.GameController;
import uet.oop.bomberman.entities.StaticEntity;
import uet.oop.bomberman.entities.staticentities.Item;

public class Flame extends StaticEntity {
    public Flame(double x, double y, Image img) {
        super(x, y, img);
    }

    public Flame() {
        super();
    }

    @Override
    public void update() {
        if (collide(GameController.bomber)) {
            GameController.bomber.beKilled();
        }
        for (StaticEntity s: GameController.stillObjects) {

        }
//        for (int i=0; i<GameController.layerItem.size(); i++) {
//
//            Item item = GameController.layerItem.get(i);
//            if (this.collide(item)&& item.isVisible()==false) {
//                item.setVisible(true);
//            }
//
//        }
    }
}
