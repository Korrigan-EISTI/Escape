package main.java.item;

import javafx.scene.image.Image;
import main.java.Environment;

/**
 * Cette classe représente un arc amélioré.
 */
public class BowUpgraded extends Item {

	public static Image img = new Image("file:src/main/resources/items/bow_upgraded.png");

    /**
     * Constructeur de la classe BowUpgraded.
     *
     * @param x La coordonnée X de l'arc amélioré.
     * @param y La coordonnée Y de l'arc amélioré.
     */
	public BowUpgraded(double x, double y) {
		super (x, y, 1, 1);
	}

    /**
     * Met à jour l'état de l'arc amélioré à chaque tick.
     *
     * @param e L'environnement dans lequel se trouve l'arc amélioré.
     */
	public void tick (Environment e) {
		super.tick(e);
		if (!status) {
			e.getPlayer().setBowIsUpgraded(true);
		}
	}

    /**
     * Renvoie l'image correspondante à l'arc amélioré.
     *
     * @return L'image de l'arc amélioré.
     */
	@Override
    public Image getImage() {
        return img;
    }
}
