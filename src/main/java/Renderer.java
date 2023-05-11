package main.java;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import main.java.entity.Entity;
import main.java.entity.particle.Particle;

import java.util.Objects;

public class Renderer {
    private final Canvas canvas;
    private final GraphicsContext gc;
    private final Image[] block_images;
    public static final int scale = 24;

    public Renderer(Canvas canvas){
    	
        this.canvas = canvas;
        gc = this.canvas.getGraphicsContext2D();
        gc.setImageSmoothing(false);

        block_images = new Image[Environment.BLOCK_PROPERTIES.size()];
        
        for (int i=0;i<Environment.BLOCK_PROPERTIES.size();i++){
            String path = Environment.BLOCK_PROPERTIES.get((short)i).imagePath();
            if(!Objects.equals(path, "")){
                block_images[i] = new Image("file:src/main/resources/blocks/"+Environment.BLOCK_PROPERTIES.get((short)i).imagePath());
            }
        }
    }
    
    public void render(Camera camera,Environment environment){
    	
        gc.setFill(Color.GRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        
        int start_x = (int) Math.floor(camera.getX() - canvas.getWidth() / (scale *2));
        int end_x = (int) Math.ceil(camera.getX() + canvas.getWidth() / (scale *2));
        int start_y = (int) Math.floor(camera.getY() - canvas.getHeight() / (scale *2));
        int end_y = (int) Math.ceil(camera.getY() + canvas.getHeight() / (scale *2));
        
        for (int x = start_x; x < end_x; x++) {
            for (int y = start_y; y < end_y; y++) {
                short block = environment.getBlock(x, y);
                if (block_images[block]!=null) {
                    gc.drawImage(block_images[block], canvas.getWidth() / 2 + (x - camera.getX())* scale, canvas.getHeight() / 2 - scale - (y - camera.getY())* scale, scale, scale);
                }
            }
        }
        for (int i=0;i<environment.getEntityCount();i++) {
            Entity entity = environment.getEntity(i);
            if (entity.getImage()!=null){
                if(entity instanceof Particle){
                    gc.setGlobalAlpha(((Particle) entity).getAlpha());
                }
                gc.drawImage(entity.getImage(), canvas.getWidth() / 2 + (entity.getX() + entity.getImageOffsetX() - camera.getX()) * scale, canvas.getHeight() / 2 - entity.getImageSizeY()* scale - (entity.getY()  + entity.getImageOffsetY() - camera.getY()) * scale, entity.getImageSizeX()* scale, entity.getImageSizeY()* scale);
                gc.setGlobalAlpha(1);
            }
        }
	}
}