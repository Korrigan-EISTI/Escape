package main.java.entity;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import main.java.Environment;
import main.java.Input;
import main.java.entity.projectile.Arrow;

public class Player extends LivingEntity{

    private static final Image image = new Image("file:src/main/resources/player.png");
    protected boolean climbing;

    public Player(double x, double y) {
        super(x, y);
        setLast_shot(60);
    }

	@Override
    public void tick(Environment environment) {
        super.tick(environment);
        climbing=false;
        for (short block : touched){
            climbing=climbing || Environment.BLOCK_PROPERTIES.get(block).climbable();
        }
        if(climbing){
            vx=0;
            vy=0;
        }
    }
	
    public void handleInput(Input input,Environment environment){

        if(input.keyPressed(KeyCode.RIGHT)){
            vx+=0.07;
        }
        if(input.keyPressed(KeyCode.LEFT)){
            vx-=0.07;
        }
        if(input.keyPressed(KeyCode.UP) && (on_ground || climbing)){
            vy=0.35;
        }
        if(input.keyPressed(KeyCode.DOWN) && climbing){
            vy=-0.3;
        }
        if(input.keyPressed(KeyCode.A) && getLast_shot()<=0){
        	environment.addEntity(new Arrow(x-w/2,y+2*h/3,-0.5, true));
        	setLast_shot(30);
        }
        if(input.keyPressed(KeyCode.E) && getLast_shot()<=0){
            environment.addEntity(new Arrow(x+3*w/2,y+2*h/3,0.5, true));
            setLast_shot(30);
        }
    }
    
    @Override
    public Image getImage(){
        return image;
    }
}