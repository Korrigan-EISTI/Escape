package main.java.entity;

import javafx.scene.image.Image;
import main.java.Environment;
import main.java.entity.projectile.ArrowUpgraded;

public class MonsterUpgraded extends LivingEntity {

	private static final Image image = new Image("file:src/main/resources/monster_upgraded.png");

	public MonsterUpgraded(double x, double y) {
		super(x, y, 10, 2);
	}

	@Override
	public void tick(Environment environment){

		super.tick(environment);

		if(getLast_shot()<=0){
			setLast_shot(90);
			environment.addEntity(new ArrowUpgraded(x-w/2,y+2*h/3,-0.5,this));
			environment.addEntity(new ArrowUpgraded(x-w/2,y+h/3,-0.5,this));
		}
	}

	@Override
	public Image getImage(){
		return image;
	}
}