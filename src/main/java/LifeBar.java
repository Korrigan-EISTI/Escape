package main.java;

import javafx.scene.control.ProgressBar;

public class LifeBar {
	
	private ProgressBar lifeBar;
	private double life;
	
	public LifeBar() {
		lifeBar = new ProgressBar(1);
		life = 1;
	}

	public ProgressBar getLifeBar() {
		return lifeBar;
	}

	public void setLifeBar(ProgressBar lifeBar) {
		this.lifeBar = lifeBar;
	}

	public double getLife() {
		return life;
	}

	public void setLife(double life) {
		this.life = life;
	}
	
	public void setLifeBarColor(String color) {
		lifeBar.setStyle("-fx-accent:"+ color+";");
	}
}
