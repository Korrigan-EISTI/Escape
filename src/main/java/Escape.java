package main.java;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Escape extends Application {
	private Canvas canvas;
	private Camera camera;
	private Renderer renderer;
	private Map map;
	private Input input;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		stage.setTitle("Hello World!");
		VBox root = new VBox();
		root.getStyleClass().add("root");
		canvas = new Canvas(1000, 600);
		map = new Map();
		renderer = new Renderer(canvas);
		camera = new Camera(100, 100);
		root.getChildren().add(canvas);
		HBox bottom = new HBox();
		bottom.getStyleClass().add("bottom");
		root.getChildren().add(bottom);
		Scene scene = new Scene(root,1000,700);
		input = new Input(scene);
		scene.getStylesheets().add("main/resources/test.css");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		int frameRate = 60;
		final Duration d = Duration.millis((double) 1000 / frameRate);
		final KeyFrame oneFrame = new KeyFrame(d, this::tick);
		Timeline t = new Timeline(frameRate, oneFrame);
		t.setCycleCount(Animation.INDEFINITE);
		t.playFromStart();
	}

	private void tick(ActionEvent actionEvent) {
		camera.tick();
		renderer.render(map,camera);
		if(input.keyPressed(KeyCode.RIGHT)){
			camera.setTarget_x(camera.getX()+50);
		}
		if(input.keyPressed(KeyCode.LEFT)){
			camera.setTarget_x(camera.getX()-50);
		}
		if(input.keyPressed(KeyCode.UP)){
			camera.setTarget_y(camera.getY()+50);
		}
		if(input.keyPressed(KeyCode.DOWN)){
			camera.setTarget_y(camera.getY()-50);
		}
	}

}