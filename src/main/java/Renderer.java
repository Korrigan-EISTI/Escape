package main.java;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import main.java.entity.Entity;

import java.util.ArrayList;
import java.util.Objects;


public class Renderer {
    private final Canvas canvas;
    private final GraphicsContext gc;
    private final Image[] block_images;
    public static final int block_size = 64;

    public Renderer(Canvas canvas){
        this.canvas = canvas;
        gc = this.canvas.getGraphicsContext2D();
        gc.setImageSmoothing(false);
        block_images = new Image[Map.BLOCK_PROPERTIES.size()];
        for (int i=0;i<Map.BLOCK_PROPERTIES.size();i++){
            String path = Map.BLOCK_PROPERTIES.get((short)i).imagePath();
            if(!Objects.equals(path, "")){
                block_images[i] = new Image("file:src/main/resources/blocks/"+Map.BLOCK_PROPERTIES.get((short)i).imagePath());
            }
        }
    }
    public void render(Map map, Camera camera, ArrayList<Entity> entities){
        gc.setFill(Color.GRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        int start_x = (int) Math.floor(camera.getX() - canvas.getWidth() / (block_size*2));
        int end_x = (int) Math.ceil(camera.getX() + canvas.getWidth() / (block_size*2));
        int start_y = (int) Math.floor(camera.getY() - canvas.getHeight() / (block_size*2));
        int end_y = (int) Math.ceil(camera.getY() + canvas.getHeight() / (block_size*2));
        for (int x = start_x; x < end_x; x++) {
            for (int y = start_y; y < end_y; y++) {
                short block = map.getBlock(x, y);
                if (block_images[block]!=null) {
                    gc.drawImage(block_images[block], canvas.getWidth() / 2 + (x - camera.getX())*block_size, canvas.getHeight() / 2 - block_size - (y - camera.getY())*block_size,block_size,block_size);
                }
            }
        }
        for (Entity entity : entities) {
            if (entity.getImage()!=null){
                gc.drawImage(entity.getImage(), canvas.getWidth() / 2 + (entity.getX() - camera.getX()) * block_size, canvas.getHeight() / 2 - entity.getImageSizeY()*block_size - (entity.getY() - camera.getY()) * block_size, entity.getImageSizeX()*block_size, entity.getImageSizeY()*block_size);
            }
        }
    }
}
