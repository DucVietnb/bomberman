package uet.oop.bomberman.entities.staticentities;

import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.GameController;
import uet.oop.bomberman.entities.StaticEntity;

public class Item extends StaticEntity {
    String itemType;
//    boolean visible = false;

//    public boolean isVisible() {
//        return visible;
//    }
//
//    public void setVisible(boolean visible) {
//        this.visible = visible;
//    }

    public Item(int xUnit, int yUnit, Image img, String itemType) {
        super(xUnit, yUnit, img);
        this.itemType = itemType;
        //visible = false;

    }

    @Override
    public void update() {

        if (this.collide(GameController.bomber)) {
            GameController.audio.playItemCollected();
            Bounds bounds = getBounds();
            //System.out.println("height: "+bounds.getHeight()+ " width: "+bounds.getWidth());
            System.out.println("Item collide bomber");
            GameController.layerItem.remove(this);
            GameController.bomber.powerUp(itemType);
        }

    }

//    @Override
//    public void render(GraphicsContext gc) {
//        super.render(gc);
//        Rectangle rectangle = new Rectangle(x+5,y+5, img.getWidth()/2,img.getHeight()/2);
////        rectangle.setX(rectangle.getX()-img.getWidth()/1.1);
////        rectangle.setY(rectangle.getY()-img.getWidth()/1.1);
//        rectangle.setStroke(Color.RED);
//        rectangle.setFill(Color.RED);
//        gc.setFill(Color.RED);
//        gc.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
//    }

    @Override
    public Bounds getBounds() {
        Rectangle rectangle = new Rectangle(x+5,y+5, img.getWidth()/2,img.getHeight()/2);
        Bounds bounds = rectangle.getLayoutBounds();
        return bounds;
    }
}
