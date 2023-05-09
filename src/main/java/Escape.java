package main.java;

import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.java.entity.Entity;
import main.java.entity.Monster;
import main.java.entity.Player;

public class Escape extends Application {
	
	private Canvas canvas;
	private Camera camera;
	private Renderer renderer;
	private Map map;
	private Input input;
	private Player player;
	private Monster monster;
	private ArrayList<Entity> entities;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		
		VBox root = new VBox();
		root.getStyleClass().add("root");
		Scene scene = new Scene(root,1600,900);
		
		canvas = new Canvas(scene.getWidth(), scene.getHeight()-100);
		map = new Map();
		renderer = new Renderer(canvas);
		camera = new Camera(100, 200);
		root.getChildren().add(canvas);
		
		player = new Player(100,200);
		monster = new Monster(122,56);
		entities = new ArrayList<Entity>();
		entities.add(player);
		entities.add(monster);
		
		HBox bottom = new HBox();
		bottom.getStyleClass().add("bottom");
		root.getChildren().add(bottom);
		
		input = new Input(scene);
		scene.getStylesheets().add("main/resources/test.css");
		
		stage.setTitle("Escape");
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
			player.setVx(player.getVx()+0.1);
		}
		if(input.keyPressed(KeyCode.LEFT)){
			player.setVx(player.getVx()-0.1);
		}
		if(input.keyPressed(KeyCode.UP) && player.isOnGround()){
			player.setVy(0.5);
		}
		player.tick(map);
		camera.setTarget_x(player.getX()+player.getWidth()/2);
		camera.setTarget_y(player.getY());
		camera.tick();
		renderer.render(map,camera,entities);
	}

}