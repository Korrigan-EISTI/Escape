package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
		Canvas canvas = new Canvas(1000, 600);

		final GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.GRAY);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

		root.getChildren().add(canvas);
		HBox bottom = new HBox();
		bottom.getStyleClass().add("bottom");
		root.getChildren().add(bottom);
		Scene scene = new Scene(root,1000,700);
		scene.getStylesheets().add("resources/test.css");
		stage.setScene(scene);
		stage.show();
	}
}