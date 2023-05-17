package main.java;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


public class Inventory {
	private HBox hbox;
	private ImageView slot1 = new ImageView(new Image("file:src/main/resources/inventory/inventory.png"));
	private ImageView slot2 = new ImageView(new Image("file:src/main/resources/inventory/inventory.png"));
	private ImageView slot3 = new ImageView(new Image("file:src/main/resources/inventory/inventory.png"));
    
	/** 
	 * Initialise une nouvelle instance de la classe Inventory.
	 */
    public Inventory() {
        hbox = new HBox();
        slot1.setFitHeight(50);
        slot1.setFitWidth(50);
        slot2.setFitHeight(50);
        slot2.setFitWidth(50);
        slot3.setFitHeight(50);
        slot3.setFitWidth(50);
        hbox = new HBox(slot1, slot2, slot3);
    }

    /**
     * Retourne la boîte horizontale (HBox) de l'inventaire.
     *
     * @return la boîte horizontale (HBox) de l'inventaire
     */
    public HBox getHbox() {
        return hbox;
    }

    /**
     * Définit la boîte horizontale (HBox) de l'inventaire.
     *
     * @param hbox la boîte horizontale (HBox) de l'inventaire à définir
     */
    public void setHbox(HBox hbox) {
        this.hbox = hbox;
    }

    /**
     * Retourne l'ImageView du slot 1 de l'inventaire.
     *
     * @return l'ImageView du slot 1 de l'inventaire
     */
    public ImageView getSlot1() {
        return slot1;
    }

    /**
     * Définit l'ImageView du slot 1 de l'inventaire.
     *
     * @param slot1 l'ImageView du slot 1 de l'inventaire à définir
     */
    public void setSlot1(ImageView slot1) {
        this.slot1 = slot1;
    }

    /**
     * Retourne l'ImageView du slot 2 de l'inventaire.
     *
     * @return l'ImageView du slot 2 de l'inventaire
     */
    public ImageView getSlot2() {
        return slot2;
    }

    /**
     * Définit l'ImageView du slot 2 de l'inventaire.
     *
     * @param slot2 l'ImageView du slot 2 de l'inventaire à définir
     */
    public void setSlot2(ImageView slot2) {
        this.slot2 = slot2;
    }

    /**
     * Retourne l'ImageView du slot 3 de l'inventaire.
     *
     * @return l'ImageView du slot 3 de l'inventaire
     */
    public ImageView getSlot3() {
        return slot3;
    }

    /**
     * Définit l'ImageView du slot 3 de l'inventaire.
     *
     * @param slot3 l'ImageView du slot 3 de l'inventaire à définir
     */
    public void setSlot3(ImageView slot3) {
        this.slot3 = slot3;
    }
}
