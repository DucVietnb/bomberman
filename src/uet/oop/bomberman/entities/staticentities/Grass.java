package uet.oop.bomberman.entities.staticentities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.StaticEntity;

public class Grass extends StaticEntity {

    public Grass(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

    @Override
    public boolean collide(StaticEntity other) {
        return false;
    }
}
