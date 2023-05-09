package main.java;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import main.java.entity.Entity;
import main.java.entity.Monster;
import main.java.entity.Player;

import java.util.ArrayList;
import java.util.Objects;


public class Renderer {
	
    private final Canvas canvas;
    private final GraphicsContext gc;
    private Image[] block_images;
    private final Image player_image;
    private final Image monster_image;

    public Renderer(Canvas canvas){
    	
        this.canvas = canvas;
        gc = this.canvas.getGraphicsContext2D();
        
        player_image = new Image("file:src/main/resources/player.png", 20, 20, false, false);
        monster_image = new Image("file:src/main/resources/monster.png", 20, 20, false, false);
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
        
        int start_x = (int) Math.floor(camera.getX() - canvas.getWidth() / 48);
        int end_x = (int) Math.ceil(camera.getX() + canvas.getWidth() / 48);
        int start_y = (int) Math.floor(camera.getY() - canvas.getHeight() / 48);
        int end_y = (int) Math.ceil(camera.getY() + canvas.getHeight() / 48);
        
        for (int x = start_x; x < end_x; x++) {
            for (int y = start_y; y < end_y; y++) {
                short block = map.getBlock(x, y);
                if (block_images[block]!=null) {
                    gc.drawImage(block_images[block], canvas.getWidth() / 2 + (x - camera.getX())*24, canvas.getHeight() / 2 - 24 - (y - camera.getY())*24);
                }
            }
        }
        
        for(int j = 0; j<entities.size(); j++) {
        	Image choosen_image;
        	if(entities.get(j) instanceof Player ) {
        		choosen_image = player_image;
        	}
        	else {
        		choosen_image = monster_image;
        	}
        	gc.drawImage(choosen_image,canvas.getWidth()/2 + (entities.get(j).getX() - camera.getX())*24, canvas.getHeight()/2 - player_image.getHeight() - (entities.get(j).getY() - camera.getY())*24);
        }
    }
}
