package main.java.entity;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import main.java.Environment;
import main.java.Input;
import main.java.entity.projectile.Arrow;

public class Player extends LivingEntity{

    private static final Image image = new Image("file:src/main/resources/player.png");
    protected boolean climbing;
    private boolean allowToShoot;
    private boolean hasKey;

    public Player(double x, double y) {
        super(x, y,10);
        setLast_shot(60);
        allowToShoot = true;
        hasKey = false;
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
        if(input.keyPressed(KeyCode.UP)){
            if (on_ground){
                vy=0.35;
            }
            else if(climbing){
                vy=0.2;
            }
        }
        if(input.keyPressed(KeyCode.DOWN) && climbing){
            vy=-0.2;
        }
        if (allowToShoot) {
            if(input.keyPressed(KeyCode.A) && getLast_shot()<=0){
            	environment.addEntity(new Arrow(x+w/2,y+2*h/3,-0.5, this.getClass()));
            	setLast_shot(30);
            }
            if(input.keyPressed(KeyCode.E) && getLast_shot()<=0){
                environment.addEntity(new Arrow(x+w/2,y+2*h/3,0.5, this.getClass()));
                setLast_shot(30);
            }
        }
    }
    
    @Override
    public Image getImage(){
        return image;
    }
    
    public void setAllowToShoot(boolean b) {
    	this.allowToShoot = b;
    }
    
    public boolean isAllowToShoot() {
    	return this.allowToShoot;
    }

	public boolean hasKey() {
		return hasKey;
	}

	public void setHasKey(boolean hasKey) {
		this.hasKey = hasKey;
	}
    
}