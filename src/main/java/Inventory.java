package main.java;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class Inventory {
	private HBox hbox;
	private ImageView btn1;
	private ImageView btn2;
	private ImageView btn3;
	
	public Inventory() {
		hbox = new HBox();
		btn1 = new ImageView(new Image("file:src/main/resources/inventory.png"));
		btn2 = new ImageView(new Image("file:src/main/resources/inventory.png"));
		btn3 = new ImageView(new Image("file:src/main/resources/inventory.png"));
		hbox = new HBox(btn1, btn2, btn3);
	}
	
	public HBox getHbox() {
		return hbox;
	}
	public void setHbox(HBox hbox) {
		this.hbox = hbox;
	}
	public ImageView getBtn1() {
		return btn1;
	}
	public void setBtn1(ImageView btn1) {
		this.btn1 = btn1;
	}
	public ImageView getBtn2() {
		return btn2;
	}
	public void setBtn2(ImageView btn2) {
		this.btn2 = btn2;
	}
	public ImageView getBtn3() {
		return btn3;
	}
	public void setBtn3(ImageView btn3) {
		this.btn3 = btn3;
	}
}
