package main.java.item;

import javafx.scene.image.Image;
import main.java.Environment;

/**
 * Cette classe représente une clé.
 */
public class Shield extends Item {

	public static Image img = new Image("file:src/main/resources/items/shield.png");

    /**
     * Constructeur de la classe Key.
     *
     * @param x La coordonnée X de la clé.
     * @param y La coordonnée Y de la clé.
     */
	public Shield(double x, double y) {
		super (x, y, 1, 1);
	}

    /**
     * Met à jour l'état de la clé à chaque tick.
     *
     * @param e L'environnement dans lequel se trouve la clé.
     */
	@Override
	public void tick (Environment e) {
		super.tick(e);
		if (!status) {
			e.getPlayer().setShieldValue(3);
		}
	}

    /**
     * Renvoie l'image correspondante à la clé.
     *
     * @return L'image de la clé.
     */
	@Override
	public Image getImage() {
		return img;
	}
}
