package main.java;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import main.java.entity.Arrow;
import main.java.entity.Entity;
import main.java.entity.Monster;
import main.java.entity.Player;
import java.util.ArrayList;
import java.util.Objects;

public class Renderer {
    private final Canvas canvas;
    private final GraphicsContext gc;
    private final Image[] block_images;
    private final Image player_image;
    private final Image monster_image;
    private final Image left_arrow_image;
    private final Image right_arrow_image;
    private final Image npc_image;
    public static final int block_size = 64;

    public Renderer(Canvas canvas){
    	
        this.canvas = canvas;
        gc = this.canvas.getGraphicsContext2D();
        gc.setImageSmoothing(false);
        
        player_image = new Image("file:src/main/resources/player.png");
        monster_image = new Image("file:src/main/resources/monster.png");
        npc_image = new Image("file:src/main/resources/pnj.png");
        left_arrow_image = new Image("file:src/main/resources/left_arrow.png");
        right_arrow_image = new Image("file:src/main/resources/right_arrow.png");
        block_images = new Image[Map.BLOCK_PROPERTIES.size()];
        
        for (int i=0;i<Map.BLOCK_PROPERTIES.size();i++){
            String path = Map.BLOCK_PROPERTIES.get((short)i).imagePath();
            if(!Objects.equals(path, "")){
                block_images[i] = new Image("file:src/main/resources/blocks/"+Map.BLOCK_PROPERTIES.get((short)i).imagePath());
            }
        }
    }
    
    public void render(Map map, Camera camera, ArrayList<Entity> entities, ArrayList<Arrow> arrows){
    	
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
            Image choosen_image;
            if (entity instanceof Player) {
                choosen_image = player_image;
            } else if (entity instanceof Monster){
                choosen_image = monster_image;
            }else {
            	choosen_image = npc_image;
            }
            gc.drawImage(choosen_image, canvas.getWidth() / 2 + (entity.getX() - camera.getX()) * block_size, canvas.getHeight() / 2 - player_image.getHeight() - (entity.getY() - camera.getY()) * block_size);
        }
        
        for (Arrow arrow : arrows) {
            Image choosen_image;
            if (((Arrow) arrow).getVx()<0) choosen_image = left_arrow_image;
        	else choosen_image = right_arrow_image;
            gc.drawImage(choosen_image, canvas.getWidth() / 2 + (arrow.getX() - camera.getX()) * block_size, canvas.getHeight() / 2 - player_image.getHeight() - (arrow.getY() - camera.getY()) * block_size);
    	}
	}
}