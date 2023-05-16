package main.java.entity.projectile;

import javafx.scene.image.Image;
import main.java.Environment;
import main.java.entity.Entity;
import main.java.entity.LivingEntity;
import main.java.entity.NonPlayableCharacter;

public class Arrow extends Projectile {

	private static final Image imageLeft = new Image("file:src/main/resources/arrow_left.png");
	private static final Image imageRight = new Image("file:src/main/resources/arrow_right.png");

	public Arrow(double x, double y, double vx, LivingEntity owner) {
		super(x-0.5, y,vx,0,1,0.2,owner);
	}

	public LivingEntity shotFrom() {
		return owner;
	}

	public void tick(Environment environment) {
		super.tick(environment);
	}

	@Override
	protected boolean onHit(LivingEntity livingEntity) {
		if(!(livingEntity instanceof NonPlayableCharacter)){
			livingEntity.damage(1);
			destroy();
			return true;
		}
		return false;
	}

	public Image getImage(){
		return vx>0?imageRight:imageLeft;
	}
}