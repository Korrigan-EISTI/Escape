package main.java.entity.projectile;

import javafx.scene.image.Image;
import main.java.Environment;
import main.java.entity.LivingEntity;
import main.java.entity.NonPlayableCharacter;

public class Arrow extends Projectile {

	private static final Image imageLeft = new Image("file:src/main/resources/arrow_left.png");
	private static final Image imageRight = new Image("file:src/main/resources/arrow_right.png");
	private boolean shotFromPlayer;

	public Arrow(double x, double y, double vx, boolean shotFromPlayer) {
		super(x-0.5, y,vx,0,1,0.2);
		this.setShotFromPlayer(shotFromPlayer);
	}

	public boolean isShotFromPlayer() {
		return shotFromPlayer;
	}

	public void setShotFromPlayer(boolean shotFromPlayer) {
		this.shotFromPlayer = shotFromPlayer;
	}

	public void tick(Environment environment) {
		super.tick(environment);
		for (int i = 0;i<environment.getEntityCount();i++){
			if(environment.getEntity(i) instanceof LivingEntity livingEntity && !(livingEntity instanceof NonPlayableCharacter)){
				if(x<livingEntity.getX() + livingEntity.getWidth() && x+w>livingEntity.getX() && y<livingEntity.getY() + livingEntity.getHeight() && y+h>livingEntity.getY()){
					if(isShotFromPlayer()) livingEntity.damage(0.25);
					else livingEntity.damage(0.1);
					destroy();
				}
			}
		}
	}

	public Image getImage(){
		return vx>0?imageRight:imageLeft;
	}
}
