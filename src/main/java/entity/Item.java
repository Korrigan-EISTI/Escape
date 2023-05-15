package main.java.entity;

import main.java.Environment;

public class Item extends Entity{
	protected boolean status;
	
	public Item(double x, double y, double w, double h) {
		super(x, y, 0, 0);
		status = true;
	}
	
	public void tick (Environment e) {
		super.tick(e);
		for (int i = 0;i<e.getEntityCount();i++){
			if(e.getEntity(i) instanceof Player){
				Player player = (Player)e.getEntity(i);
				if(x - player.getWidth()<=player.getX() && player.getX()<= x + 1 && player.getY() >= y && player.getY()<= y + 1){
					this.status = false;
					destroy();
				}
			}
		}
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
