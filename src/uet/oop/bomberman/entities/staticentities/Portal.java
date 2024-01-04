package uet.oop.bomberman.entities.staticentities;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.GameController;
import uet.oop.bomberman.entities.StaticEntity;

public class Portal extends StaticEntity {


    public Portal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Portal(double x, double y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (this.collide(GameController.bomber) && GameController.enemies.size()==0) {
            GameController.victory();
        }
    }

    @Override
    public Bounds getBounds() {
        Rectangle rectangle = new Rectangle(x+5,y+5, img.getWidth()/2,img.getHeight()/2);
        Bounds bounds = rectangle.getLayoutBounds();
        return bounds;
    }
}
