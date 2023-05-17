package main.java.entity;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import main.java.Environment;
import main.java.Input;
import main.java.entity.projectile.Arrow;
import main.java.entity.projectile.ArrowUpgraded;

public class Player extends LivingEntity{

    private static final Image image = new Image("file:src/main/resources/player.png");
    protected boolean climbing;
    protected boolean allowToShoot;
    private boolean hasKey;
    private boolean hasWallPotion;
    private boolean canWalkThroughMagicWalls;
    private boolean bowIsUpgraded;

    public Player(double x, double y) {
        super(x, y, 10, 1);
        setLast_shot(30);
        allowToShoot = false;
        hasKey = false;
        hasWallPotion = false;
        setBowIsUpgraded(false);
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
            if(on_ground){
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
            	if(this.isBowUpgraded()) environment.addEntity(new ArrowUpgraded(x+w/2,y+2*h/3,-0.5,this));
            	else environment.addEntity(new Arrow(x+w/2,y+2*h/3,-0.5,this));
            	setLast_shot(30);
            }
            if(input.keyPressed(KeyCode.E) && getLast_shot()<=0) {
            	if (this.isBowUpgraded()) environment.addEntity(new ArrowUpgraded(x+w/2,y+2*h/3, 0.5,this));
            	else environment.addEntity(new Arrow(x+w/2,y+2*h/3, 0.5,this));
                setLast_shot(30);
            }
        }
        if(input.keyPressed(KeyCode.P)){
            useWallPotion();
        }
    }
    
    @Override
    public Image getImage(){
        return image;
    }

	public boolean hasKey() {
		return hasKey;
	}

	public void setHasKey(boolean hasKey) {
		this.hasKey = hasKey;
	}

	public boolean hasWallPotion() {
		return hasWallPotion;
	}

	public void setHasWallPotion(boolean hasWallPotion) {
		this.hasWallPotion = hasWallPotion;
	}

	public boolean canWalkThroughMagicWalls() {
		return canWalkThroughMagicWalls;
	}

	public void setCanWalkThroughMagicWalls(boolean canWalkThroughMagicWalls) {
		this.canWalkThroughMagicWalls = canWalkThroughMagicWalls;
	}
    
	public void useWallPotion() {
		if(hasWallPotion) {
			this.canWalkThroughMagicWalls = true;
			this.setHasWallPotion(false);
		}
	}

	public boolean isAllowToShoot() {
		return allowToShoot;
	}

	public void setAllowToShoot(boolean allowToShoot) {
		this.allowToShoot = allowToShoot;
	}

	public boolean isBowUpgraded() {
		return bowIsUpgraded;
	}

	public void setBowIsUpgraded(boolean bowIsUpgraded) {
		this.bowIsUpgraded = bowIsUpgraded;
	}
	
}