package main.java;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Renderer {
    private final Canvas canvas;
    private final GraphicsContext gc;

    public Renderer(Canvas canvas){
        this.canvas = canvas;
        gc = this.canvas.getGraphicsContext2D();
    }
    public void render(Map map,Camera camera){
        gc.setFill(Color.GRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (int x = 0; x<map.getWidth(); x++){
            for (int y = 0; y<map.getHeight(); y++){
                if(map.getBlock(x,y)==1){
                    gc.drawImage(map.getImage(0),canvas.getWidth()/2 + x * 24 - camera.getX(), canvas.getHeight()/2 - 32 - y * 24 + camera.getY());
                }
            }
        }
    }
}
