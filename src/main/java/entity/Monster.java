package main.java.entity;

import javafx.scene.image.Image;

public class Monster extends NonPlayablePhysicalEntity{
	private static final Image image = new Image("file:src/main/resources/monster.png");
	public Monster(double x, double y) {
		super(x, y);
	}
	@Override
	public Image getImage(){
		return image;
	}
}