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
import main.java.entity.Player;

public class Escape extends Application {
	private Canvas canvas;
	private Camera camera;
	private Renderer renderer;
	private Map map;
	private Input input;
	private Player player;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		stage.setTitle("Hello World!");
		VBox root = new VBox();
		root.getStyleClass().add("root");
		Scene scene = new Scene(root,1600,900);
		canvas = new Canvas(scene.getWidth(), scene.getHeight()-100);
		map = new Map();
		renderer = new Renderer(canvas);
		camera = new Camera(100, 100);
		player = new Player(2000,3000);
		root.getChildren().add(canvas);
		HBox bottom = new HBox();
		bottom.getStyleClass().add("bottom");
		root.getChildren().add(bottom);
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
		if(input.keyPressed(KeyCode.RIGHT)){
			player.setVx(5);
		}
		if(input.keyPressed(KeyCode.LEFT)){
			player.setVx(-5);
		}
		if(input.keyPressed(KeyCode.UP) && player.isOnGround()){
			player.setVy(10);
		}
		player.tick(map);
		camera.setTarget_x(player.getX());
		camera.setTarget_y(player.getY());
		camera.tick();
		renderer.render(map,camera,player);
	}

}