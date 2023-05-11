package main.java.entity;

import javafx.scene.image.Image;
import main.java.Environment;
import main.java.entity.projectile.Arrow;
import main.java.entity.projectile.Bullet;

import java.util.Random;

public class Monster extends NonPlayablePhysicalEntity{
	private static final Image image = new Image("file:src/main/resources/monster.png");
	public Monster(double x, double y) {
		super(x, y,10);
	}
	@Override
	public void tick(Environment environment){
		int rand = new Random().nextInt(100);
		if(rand<10){
			double dir = new Random().nextInt(2);
			environment.addEntity(new Arrow(x+w/2,y+2*h/3,(double)(dir*2-1)/2));
		}
	}
	@Override
	public Image getImage(){
		return image;
	}
}