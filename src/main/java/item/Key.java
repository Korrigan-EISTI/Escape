package main.java.item;

import javafx.scene.image.Image;
import main.java.Environment;

/**
 * Cette classe repr�sente une cl�.
 */
public class Key extends Item {

	public static Image img = new Image("file:src/main/resources/items/key.png");

    /**
     * Constructeur de la classe Key.
     *
     * @param x La coordonn�e X de la cl�.
     * @param y La coordonn�e Y de la cl�.
     */
	public Key(double x, double y) {
		super (x, y, 1, 1);
	}

    /**
     * Met � jour l'�tat de la cl� � chaque tick.
     *
     * @param e L'environnement dans lequel se trouve la cl�.
     */
	@Override
	public void tick (Environment e) {
		super.tick(e);
		if (!status) {
			e.getPlayer().setHasKey(true);
			e.setGameProgression(Environment.Progress.KEY);
		}
	}

    /**
     * Renvoie l'image correspondante � la cl�.
     *
     * @return L'image de la cl�.
     */
	@Override
	public Image getImage() {
		return img;
	}
}
