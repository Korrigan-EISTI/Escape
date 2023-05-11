package main.java;

import javafx.scene.control.ProgressBar;
import main.java.entity.Entity;

public class LifeBar {
	
	private ProgressBar lifeBar;
	private int life;
	private Entity parent;
	
	public LifeBar(Entity parent) {
		this.parent = parent;
		lifeBar = new ProgressBar(1);
		life = 100;
	}

	public ProgressBar getLifeBar() {
		return lifeBar;
	}

	public void setLifeBar(ProgressBar lifeBar) {
		this.lifeBar = lifeBar;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public Entity getParent() {
		return parent;
	}

	public void setParent(Entity parent) {
		this.parent = parent;
	}
	
	public void setLifeBarColor(String color) {
		lifeBar.setStyle("-fx-accent:"+ color+";");
	}
}
