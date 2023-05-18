package main.java.item;

import javafx.scene.image.Image;
import main.java.Environment;

public class Beer extends Item {

	public static Image img = new Image("file:src/main/resources/items/beer.png");

	public Beer(double x, double y) {
		super (x, y, 1, 1);
	}

	public void tick (Environment e) {
		super.tick(e);
		if (!status) {
			e.getPlayer().heal(10);
		}
	}

	@Override
	public Image getImage() {
		return img;
	}
}
