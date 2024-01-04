package uet.oop.bomberman.graphics.irender;

import javafx.scene.canvas.GraphicsContext;

public interface IRender {
    public abstract void render(GraphicsContext gc);
    public abstract void update();
}
