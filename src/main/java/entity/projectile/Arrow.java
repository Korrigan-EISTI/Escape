package main.java.entity.projectile;

import javafx.scene.image.Image;
import main.java.Environment;
import main.java.entity.LivingEntity;
import main.java.entity.PhysicalEntity;

public class Arrow extends Projectile {

	private static Image imageLeft = new Image("file:src/main/resources/arrow_left.png");
	private static Image imageRight = new Image("file:src/main/resources/arrow_right.png");
	private boolean shotFromPlayer;

	public Arrow(double x, double y, double vx, boolean shotFromPlayer) {
		super(x, y,vx,0);
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
			if(environment.getEntity(i) instanceof LivingEntity livingEntity){
				if(x>livingEntity.getX() && x<livingEntity.getX()+livingEntity.getWidth() && y>livingEntity.getY() && y<livingEntity.getY()+livingEntity.getHeight() ){
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
	public double getImageOffsetX(){
		return vx>0?-1:0;
	}
	public double getImageOffsetY(){
		return -0.1;
	}
	public double getImageSizeX(){
		return 1;
	}
	public double getImageSizeY(){
		return 0.1;
	}
}
