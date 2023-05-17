package main.java.item;

import javafx.scene.image.Image;
import main.java.Environment;

/**
 * Cette classe repr�sente une potion de soin.
 */
public class HealPotion extends Item {

	public static Image img = new Image("file:src/main/resources/items/heal_potion.png");

    /**
     * Constructeur de la classe HealPotion.
     *
     * @param x La coordonn�e X de la potion de soin.
     * @param y La coordonn�e Y de la potion de soin.
     */
	public HealPotion(double x, double y) {
		super (x, y, 1, 1);
	}

    /**
     * Met � jour l'�tat de la potion de soin � chaque tick.
     *
     * @param e L'environnement dans lequel se trouve la potion de soin.
     */
	public void tick (Environment e) {
		super.tick(e);
		if (!status) {
			e.getPlayer().heal(5);
		}
	}

    /**
     * Renvoie l'image correspondante � la potion de soin.
     *
     * @return L'image de la potion de soin.
     */
	@Override
	public Image getImage() {
		return img;
	}
}
