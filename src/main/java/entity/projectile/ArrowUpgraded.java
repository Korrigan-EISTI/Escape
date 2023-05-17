package main.java.entity.projectile;

import javafx.scene.image.Image;
import main.java.Environment;
import main.java.entity.LivingEntity;
import main.java.entity.MonsterUpgraded;
import main.java.entity.NonPlayableCharacter;

public class ArrowUpgraded extends Projectile {

	private static final Image imageLeft = new Image("file:src/main/resources/arrows/arrow_left_upgraded.png");
	private static final Image imageRight = new Image("file:src/main/resources/arrows/arrow_right_upgraded.png");

	public ArrowUpgraded(double x, double y, double vx, LivingEntity owner) {
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
		if(!(livingEntity instanceof NonPlayableCharacter) || !(livingEntity instanceof MonsterUpgraded)){
			livingEntity.damage(2);
			destroy();
			return true;
		}
		return false;
	}

	public Image getImage(){
		return vx>0?imageRight:imageLeft;
	}
}