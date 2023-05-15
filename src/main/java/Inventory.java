package main.java;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class Inventory {
	private HBox hbox;
	private Button btn1;
	private Button btn2;
	private Button btn3;
	
	public Inventory() {
		hbox = new HBox();
		btn1 = new Button();
		btn2 = new Button();
		btn3 = new Button();
		btn1.setPrefSize(50, 50);
		btn2.setPrefSize(50, 50);
		btn3.setPrefSize(50, 50);
		hbox = new HBox(btn1, btn2, btn3);
	}
	
	public HBox getHbox() {
		return hbox;
	}
	public void setHbox(HBox hbox) {
		this.hbox = hbox;
	}
	public Button getBtn1() {
		return btn1;
	}
	public void setBtn1(Button btn1) {
		this.btn1 = btn1;
	}
	public Button getBtn2() {
		return btn2;
	}
	public void setBtn2(Button btn2) {
		this.btn2 = btn2;
	}
	public Button getBtn3() {
		return btn3;
	}
	public void setBtn3(Button btn3) {
		this.btn3 = btn3;
	}
}
