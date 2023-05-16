package main.java.entity;

import javafx.scene.image.Image;
import main.java.Environment;
import main.java.entity.projectile.Arrow;

import java.util.Random;

public class Monster extends LivingEntity {
	
	private static final Image image = new Image("file:src/main/resources/monster.png");
	
	
	public Monster(double x, double y) {
		super(x, y,4);
	}
	
	@Override
	public void tick(Environment environment){
		
		super.tick(environment);
		
		if(getLast_shot()<=0){
			setLast_shot(60);
			double dir = new Random().nextInt(2);
			if(dir==0) environment.addEntity(new Arrow(x-w/2,y+2*h/3,-0.5, Monster.class));
			if(dir==1) environment.addEntity(new Arrow(x+3*w/2,y+2*h/3,0.5, Monster.class));
		}
	}

	@Override
	public Image getImage(){
		return image;
	}
}