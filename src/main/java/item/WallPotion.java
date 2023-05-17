package main.java.item;

import javafx.scene.image.Image;
import main.java.Environment;

/**
 * Cette classe représente une potion permettant de traverser les murs.
 */
public class WallPotion extends Item {

	public static Image img = new Image("file:src/main/resources/items/wall_potion.png");

    /**
     * Constructeur de la classe WallPotion.
     *
     * @param x La coordonnée X de la potion de mur.
     * @param y La coordonnée Y de la potion de mur.
     */
	public WallPotion(double x, double y) {
		super (x, y, 1, 1);
	}

    /**
     * Met à jour l'état de la potion de mur à chaque tick.
     *
     * @param e L'environnement dans lequel se trouve la potion de mur.
     */
	public void tick (Environment e) {
		super.tick(e);
		if (!status) {
			e.getPlayer().setHasWallPotion(true);
		}
	}

    /**
     * Renvoie l'image correspondante à la potion de mur.
     *
     * @return L'image de la potion de mur.
     */
    @Override
    public Image getImage() {
		return img;
	}
}
