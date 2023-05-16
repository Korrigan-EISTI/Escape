package main.java.item;

import javafx.scene.image.Image;
import main.java.Environment;

public class WallPotion extends Item{
	
	public static Image img = new Image("file:src/main/resources/wall_potion.png");
	public static Image img_used = new Image("file:src/main/resources/wall_potion_used.png");
	
	public WallPotion(double x, double y) {
		super (x, y, 1, 1);
	}
	
	public void tick (Environment e) {
		super.tick(e);
		if (!status) {
			e.getPlayer().setHasWallPotion(true);
		}
	}
	
	@Override
	public Image getImage() {
		return img;
	}
}
