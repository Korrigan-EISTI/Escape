package main.java.entity;

import main.java.Map;

public class Monster extends NonPlayablePhysicalEntity{
	
	private int shoot;

    public Monster(double x, double y) {
        super(x, y);
        this.shoot = 0;
    }
    
    public int getShoot() {
		return shoot;
	}

	public void setShoot(int shoot) {
		this.shoot = shoot;
	}
    
    @Override
    public void tick(Map map) {
    	super.tick(map);
    	int r = (int) (Math.random()*150);
    	this.setShoot(r);
    }
}