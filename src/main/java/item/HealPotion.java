package main.java.item;

import javafx.scene.image.Image;
import main.java.Environment;

public class HealPotion extends Item{
	
	public static Image img = new Image("file:src/main/resources/items/heal_potion.png");
	
	public HealPotion(double x, double y) {
		super (x, y, 1, 1);
	}
	
	public void tick (Environment e) {
		super.tick(e);
		if (!status) {
			e.getPlayer().heal(5);
		}
	}
	
	@Override
	public Image getImage() {
		return img;
	}
}
