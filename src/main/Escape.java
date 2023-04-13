package main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Escape extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage stage) {
		stage.setTitle("Hello World!");
		VBox root = new VBox();
		root.getStyleClass().add("root");
		TextField text = new TextField();
		text.getStyleClass().add("text");
		root.getChildren().add(text);
		ListView<String> list = new ListView<>(FXCollections.observableArrayList("set","tes"));
		list.getStyleClass().add("list");
		root.getChildren().add(list);
		Scene scene = new Scene(root,300,300);
		scene.getStylesheets().add("test.css");
		stage.setScene(scene);
		stage.setResizable(true);
		stage.show();
	}
}