package main.java;

import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.java.entity.Entity;
import main.java.entity.Monster;
import main.java.entity.NonPlayableCharacter;
import main.java.entity.Player;
import main.java.entity.projectile.Bullet;

public class Escape extends Application {
	
	private Canvas canvas;
	private Camera camera;
	private Renderer renderer;
	private Environment environment;
	private Input input;
	private Player player;
	private Monster monster;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		
		VBox root = new VBox();
		root.getStyleClass().add("root");
		Scene scene = new Scene(root,1000,750);
		
		canvas = new Canvas(scene.getWidth(), scene.getHeight()-100);
		canvas.setFocusTraversable(true);
		environment = new Environment();
		renderer = new Renderer(canvas);
		camera = new Camera(9, 51);
		root.getChildren().add(canvas);

		player = new Player(9, 51);
		monster = new Monster(9,51);
		environment.addEntity(player);
		environment.addEntity(monster);
		environment.addEntity(new NonPlayableCharacter(9,51));
		
		ProgressBar life = new ProgressBar(1);
		life.setPrefSize(scene.getWidth()/2, scene.getHeight()/4);
		life.setStyle("-fx-accent: green;");
		HBox inventory = new HBox();
		for (int i = 0; i < 3; i++) {
			Button inv = new Button();
			inv.setPrefSize(50, 50);
			inventory.getChildren().add(inv);
		}
		inventory.setAlignment(Pos.CENTER);
		inventory.setPrefWidth(scene.getWidth()/2);
		inventory.setSpacing(75);
		HBox bottom = new HBox(life, inventory);
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
		player.handleInput(input);
		environment.tickEntities();
		camera.setTarget_x(player.getX()+player.getWidth()/2);
		camera.setTarget_y(player.getY());
		camera.tick();
		renderer.render(environment,camera);
	}
}