package main.java.item;

import javafx.scene.image.Image;
import main.java.Environment;

public class BowUpgraded extends Item{
	
	public static Image img = new Image("file:src/main/resources/items/bow_upgraded.png");
	
	public BowUpgraded(double x, double y) {
		super (x, y, 1, 1);
	}
	
	public void tick (Environment e) {
		super.tick(e);
		if (!status) {
			e.getPlayer().setBowIsUpgraded(true);
		}
	}
	
	@Override
	public Image getImage() {
		return img;
	}
	
}
