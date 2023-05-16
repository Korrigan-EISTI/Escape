package main.java.entity;

import main.java.Environment;

public class NonPlayableCharacter extends LivingEntity {
	
	private int cooldown;
	
	public NonPlayableCharacter(double x, double y) {
		super(x,y,100000);
		cooldown = 0;
	}
	
	public void tick (Environment e) {
		super.tick(e);
		for (int i = 0;i<e.getEntityCount();i++){
			if(e.getEntity(i) instanceof Player){
				Player player = (Player)e.getEntity(i);
				if(x - 5<=player.getX() && player.getX()<= x + 1 && player.getY() >= y && player.getY()<= y + 1){
					if (e.getGameProgression() == 0) {
						e.setGameProgression(1);
						cooldown = 300;
					}
					cooldown--;
					if (e.getGameProgression() == 2) {
						player.setAllowToShoot(true);
					}
				}
			}
		}
	}

	public int getCooldown() {
		return cooldown;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}
	
}
