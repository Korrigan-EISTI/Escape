package main.java.entity.projectile;

import javafx.scene.image.Image;
import main.java.Environment;
import main.java.entity.Player;

public class Arrow extends Projectile {

	private static Image imageLeft = new Image("file:src/main/resources/arrow_left.png");
	private static Image imageRight = new Image("file:src/main/resources/arrow_right.png");

	private int hit;

	public Arrow(double x, double y, double vx) {
		super(x, y,vx,0);
		this.setHit(0);
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public void tick(Environment environment) {
		super.tick(environment);
		for (int i = 0;i<environment.getEntityCount();i++){
			if(environment.getEntity(i) instanceof Player){
				Player player = (Player)environment.getEntity(i);
				if(x>player.getX() && x<player.getX()+player.getWidth() && y>player.getY() && y<player.getY()+player.getHeight() ){
					player.damage(0.1);
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
