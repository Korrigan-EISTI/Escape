package main.java.entity;

import main.java.Map;

public abstract class ThrowableEntity extends Entity {
	
	private PhysicalEntity parent;

    public ThrowableEntity(double x, double y, double x_speed, PhysicalEntity parent, Double w, Double h) {
        super(x,y,x_speed,0,(double)20/24,(double)20/24);
        this.setParent(parent);
    }

    public PhysicalEntity getParent() {
		return parent;
	}

	public void setParent(PhysicalEntity parent) {
		this.parent = parent;
	}

	public void tick(Map map){
        super.tick(map);
    }
}