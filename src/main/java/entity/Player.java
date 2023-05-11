package main.java.entity;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import main.java.Environment;
import main.java.Input;
import main.java.entity.projectile.Arrow;

public class Player extends PhysicalEntity{

    private static final Image image = new Image("file:src/main/resources/player.png");
	private double life;
    protected boolean climbing;


    public Player(double x, double y) {
        super(x, y, 0, 0, (double) 20 /24, (double) 20 /24,1);
        life = 1;
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
        if(input.keyPressed(KeyCode.A)){
        	environment.addEntity(new Arrow(x+w/2,y+2*h/2,-1));
        }
        if(input.keyPressed(KeyCode.E)){
            environment.addEntity(new Arrow(x+w/2,y+2*h/2,1));
        }
    }
    @Override
    public Image getImage(){
        return image;
    }
}