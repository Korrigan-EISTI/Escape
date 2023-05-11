package main.java.entity.projectile;

import javafx.scene.image.Image;
import main.java.Environment;

public class Arrow extends Projectile {

	private static Image image = new Image("file:src/main/resources/right_arrow.png");

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
	}

	public Image getImage(){
		return image;
	}
	public double getImageOffsetX(){
		return 0;
	}
	public double getImageOffsetY(){
		return -.5;
	}
	public double getImageSizeX(){
		return 1;
	}
	public double getImageSizeY(){
		return 1;
	}
}
