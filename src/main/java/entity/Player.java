package main.java.entity;

import java.util.ArrayList;

import javafx.scene.input.KeyCode;
import main.java.Input;
import main.java.Map;

public class Player extends PhysicalEntity{
	
	private double life;
    protected boolean climbing;

    public Player(double x, double y) {
        super(x, y, 0, 0, (double) 20 /24, (double) 20 /24);
        life = 1;
    }

    public double getLife() {
		return life;
	}

	public void setLife(double d) {
		this.life = d;
	}

	@Override
    public void tick(Map map) {
        super.tick(map);
        climbing=false;
        for (short block : touched){
            climbing=climbing || Map.BLOCK_PROPERTIES.get(block).climbable();
        }
        if(climbing){
            vx=0;
            vy=0;
        }
    }
    public void handleInput(Input input, ArrayList<Arrow> arrows){

        if(input.keyPressed(KeyCode.RIGHT)){
            vx+=0.07;
        }
        if(input.keyPressed(KeyCode.LEFT)){
            vx-=0.07;
        }
        if(input.keyPressed(KeyCode.UP) && (on_ground || climbing)){
            vy=0.35;
        }
        if(input.keyPressed(KeyCode.DOWN) && climbing){
            vy=-0.3;
        }
        if(input.keyPressed(KeyCode.A)){
        	arrows.add(new Arrow(this.getX()-this.getWidth(),this.getY(),-0.5,null));
        }
        if(input.keyPressed(KeyCode.E)){
        	arrows.add(new Arrow(this.getX()+this.getWidth(),this.getY(),0.5,null));
        }
    }
}