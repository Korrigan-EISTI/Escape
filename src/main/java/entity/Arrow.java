package main.java.entity;

import main.java.Map;

public class Arrow extends ThrowableEntity {
	
	private int hit;

	public Arrow(double x, double y, double x_speed, Monster parent) {
		super(x, y, x_speed, parent,(double)20/24,(double)20/24);
		this.setHit(0);
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public void tick(Map map, Player player) {
		super.tick(map);
		
        if(vx<0){ //Collision de la flèche à gauche
            int next_x = (int)Math.floor(x);
            int next_y = (int)Math.floor(y);
            
            //Avec un mur solide
            if(Map.BLOCK_PROPERTIES.get(map.getBlock(next_x,next_y)).solid()) this.setHit(1);
            
            //Avec le joueur
            if((this.getX()>player.getX()-this.getWidth() && this.getX()<=player.getX()+player.getWidth() && this.getY()>=player.getY()-this.getHeight() && this.getY()<player.getY()+player.getHeight())) this.setHit(2);
        }
        else { //Collision de la flèche à droite
            int next_x = (int)Math.floor(x+w);
            int next_y = (int)Math.ceil(y+h)-1;
            
            //Avec un mur solide
            if(Map.BLOCK_PROPERTIES.get(map.getBlock(next_x,next_y)).solid()) this.setHit(1);
            
            //Avec le joueur
            if((this.getX()>player.getX()-this.getWidth() && this.getX()<=player.getX()+player.getWidth() && this.getY()>=player.getY()-this.getHeight() && this.getY()<player.getY()+player.getHeight())) this.setHit(2);
        }
	}
}
