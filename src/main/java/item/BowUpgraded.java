package main.java.item;

import javafx.scene.image.Image;
import main.java.Environment;

/**
 * Cette classe repr�sente un arc am�lior�.
 */
public class BowUpgraded extends Item {

	public static Image img = new Image("file:src/main/resources/items/bow_upgraded.png");

    /**
     * Constructeur de la classe BowUpgraded.
     *
     * @param x La coordonn�e X de l'arc am�lior�.
     * @param y La coordonn�e Y de l'arc am�lior�.
     */
	public BowUpgraded(double x, double y) {
		super (x, y, 1, 1);
	}

    /**
     * Met � jour l'�tat de l'arc am�lior� � chaque tick.
     *
     * @param e L'environnement dans lequel se trouve l'arc am�lior�.
     */
	public void tick (Environment e) {
		super.tick(e);
		if (!status) {
			e.getPlayer().setBowIsUpgraded(true);
		}
	}

    /**
     * Renvoie l'image correspondante � l'arc am�lior�.
     *
     * @return L'image de l'arc am�lior�.
     */
	@Override
    public Image getImage() {
        return img;
    }
}
