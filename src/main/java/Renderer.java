package main.java;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import main.java.entity.Entity;
import main.java.entity.LivingEntity;
import main.java.entity.NonPlayableCharacter;
import main.java.entity.particle.Particle;
import java.util.Objects;

public class Renderer {
    private final Canvas canvas;
    private final GraphicsContext gc;
    private final Image[] block_images;
    public static final int scale = 64;

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
    private void drawImage(Image image, Camera camera, double x, double y, double w, double h){
        gc.drawImage(image, canvas.getWidth() / 2 + (x - camera.getX())* scale, canvas.getHeight() / 2 - (h + y - camera.getY())* scale, w*scale, h*scale);
    }
    private void drawRect(Camera camera,double x, double y, double w, double h, Color color){
        gc.setFill(color);
        gc.fillRect(canvas.getWidth() / 2 + (x - camera.getX()) * scale, canvas.getHeight() / 2 - (y - camera.getY()) * scale,w * scale, h * scale);
    }
    public void render(Camera camera,Environment environment){
    	
        gc.setFill(Color.LIGHTBLUE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        
        int start_x = (int) Math.floor(camera.getX() - canvas.getWidth() / (scale *2));
        int end_x = (int) Math.ceil(camera.getX() + canvas.getWidth() / (scale *2));
        int start_y = (int) Math.floor(camera.getY() - canvas.getHeight() / (scale *2));
        int end_y = (int) Math.ceil(camera.getY() + canvas.getHeight() / (scale *2));
        
        for (int x = start_x; x < end_x; x++) {
            for (int y = start_y; y < end_y; y++) {
                short block = environment.getBlock(x, y);
                if (block_images[block]!=null) {
                    drawImage(block_images[block],camera,x,y,1,1);
                }
            }
        }
        for (int i=0;i<environment.getEntityCount();i++) {
            Entity entity = environment.getEntity(i);
            if (entity.getImage()!=null){
                if(entity instanceof Particle){
                    gc.setGlobalAlpha(((Particle) entity).getAlpha());
                }
                drawImage(entity.getImage(),camera,entity.getX() + entity.getImageOffsetX(),entity.getY()  + entity.getImageOffsetY(),entity.getImageSizeX(),entity.getImageSizeY());
                gc.setGlobalAlpha(1);
                if(entity instanceof LivingEntity livingEntity){
                	if (entity instanceof NonPlayableCharacter npc){
                		gc.setFill(Color.WHITE);
                	    gc.fillRect(canvas.getWidth() / 2 + (livingEntity.getX() - 0.75 + entity.getImageOffsetX() - camera.getX() - .15 ) * scale, canvas.getHeight() / 2 - livingEntity.getImageSizeY()* scale + (livingEntity.getY() - 0.25  + livingEntity.getImageOffsetY() - camera.getY() - 0.95) * scale,(livingEntity.getImageSizeX()*4.25)* scale, .9* scale);
                		if (environment.getGameProgression() == 1) {
                			gc.setFill(Color.BLACK);
                			gc.fillText("Viens me voir héros! \nJ'ai un problème mon coffre est fermé \net j'ai oublié où j'ai mis la clé \n", canvas.getWidth() / 2 + (livingEntity.getX() - 0.5 + entity.getImageOffsetX() - camera.getX() - .15 ) * scale, canvas.getHeight() / 2 - livingEntity.getImageSizeY()* scale + (livingEntity.getY()  + livingEntity.getImageOffsetY() - camera.getY() - 0.95) * scale);
                			if (npc.getCooldown() <= 0) environment.setGameProgression(2);
                		}
                		if (environment.getGameProgression() == 2) {
                			gc.setFill(Color.BLACK);
                			gc.fillText("Ah!!!! \nMon chateau est rempli de monstre, \ntiens prends mon arc et va les tuer", canvas.getWidth() / 2 + (livingEntity.getX() - 0.5 + entity.getImageOffsetX() - camera.getX() - .15 ) * scale, canvas.getHeight() / 2 - livingEntity.getImageSizeY()* scale + (livingEntity.getY()  + livingEntity.getImageOffsetY() - camera.getY() - 0.95) * scale);
                		}
                	}

                    drawRect(camera,livingEntity.getX() + entity.getImageOffsetX() - .15,livingEntity.getY()  + livingEntity.getImageOffsetY() - 0.05,livingEntity.getImageSizeX()+.3,.2,Color.LIGHTGRAY);
                    Color color;
                    double lifeRatio = livingEntity.getLife()/ livingEntity.getMaxLife();
                    if (lifeRatio<=0.3) color = Color.RED;
                    else if (lifeRatio<=0.6) color = Color.ORANGE;
                    else color = Color.GREEN;
                    drawRect(camera,livingEntity.getX() + entity.getImageOffsetX() - .1,livingEntity.getY()  + livingEntity.getImageOffsetY() - 0.1,(livingEntity.getImageSizeX()+.2)* lifeRatio,.1,color);
                }
            }
        }
	}
}