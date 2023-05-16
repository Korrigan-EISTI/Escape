package main.java;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Inventory {
	private HBox hbox;
	private ImageView slot1 = new ImageView(new Image("file:src/main/resources/inventory.png"));;
	private ImageView slot2 = new ImageView(new Image("file:src/main/resources/inventory.png"));;
	private ImageView slot3 = new ImageView(new Image("file:src/main/resources/inventory.png"));;

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
	
	public HBox getHbox() {
		return hbox;
	}
	public void setHbox(HBox hbox) {
		this.hbox = hbox;
	}
	public ImageView getSlot1() {
		return slot1;
	}
	public void setSlot1(ImageView slot1) {
		this.slot1 = slot1;
	}
	public ImageView getSlot2() {
		return slot2;
	}
	public void setSlot2(ImageView slot2) {
		this.slot2 = slot2;
	}
	public ImageView getSlot3() {
		return slot3;
	}
	public void setSlot3(ImageView slot3) {
		this.slot3 = slot3;
	}
}
