package main.java.item;

import javafx.scene.image.Image;
import main.java.Environment;

/**
 * Cette classe represente un bouclier.
 */
public class Shield extends Item {

	public static Image img = new Image("file:src/main/resources/items/shield.png");

    /**
     * Constructeur de la classe Shield.
     *
     * @param x La coordonnee X du bouclier.
     * @param y La coordonnee Y du bouclier.
     */
	public Shield(double x, double y) {
		super (x, y, 1, 1);
	}

    /**
     * Met a jour l'etat du bouclier à chaque tick.
     *
     * @param e L'environnement dans lequel se trouve le bouclier.
     */
	@Override
	public void tick (Environment e) {
		super.tick(e);
		if (!status) {
			e.getPlayer().setShieldValue(3);
		}
	}

    /**
     * Renvoie l'image correspondante au bouclier
     *
     * @return L'image du bouclier.
     */
	@Override
	public Image getImage() {
		return img;
	}
}
