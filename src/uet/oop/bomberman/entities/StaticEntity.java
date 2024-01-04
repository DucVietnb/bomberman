package uet.oop.bomberman.entities;

import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.irender.IRender;

public abstract class StaticEntity implements IRender, Collider {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected double x;
    protected double xUnit;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected double y;
    protected double yUnit;

    protected Image img;

    public double getxUnit() {
        return x/Sprite.SCALED_SIZE;
    }

    public void setxUnit(double xUnit) {
        this.xUnit = xUnit;
    }

    public double getyUnit() {
        return y/Sprite.SCALED_SIZE;
    }

    public void setyUnit(double yUnit) {
        this.yUnit = yUnit;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public StaticEntity(double x, double y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
    }

    public StaticEntity() {
    }

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public StaticEntity(int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);

    }
    @Override
    public abstract void update();

    @Override
    public boolean collide(StaticEntity other) {
        if (this.getBounds().intersects(other.getBounds())) {
            return true;
        }
        return false;
    }

    public void setBounds() {

    }
    public Bounds getBounds() {
        Rectangle rectangle = new Rectangle(x+2,y+2, img.getWidth()/1.5, img.getHeight()/1.5);
        rectangle.setStroke(Color.RED);
        Bounds bounds = rectangle.getLayoutBounds();
        return bounds;
    }

}
