package main.java;

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
import main.java.entity.Monster;
import main.java.entity.Player;
import main.java.entity.NonPlayableCharacter;

public class TakeTheChest extends Application {

	private Canvas canvas;
	private Camera camera;
	private Renderer renderer;
	private Environment environment;
	private Input input;
	private Player player;
<<<<<<< HEAD:src/main/java/TakeTheChest.java
	private ProgressBar life;
=======
	private LifeBar playerLifeBar;
>>>>>>> 7a2c048 (Inventaire):src/main/java/Escape.java

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
		camera = new Camera(100, 200);
		root.getChildren().add(canvas);

		player = new Player(9, 51);
		environment.addEntity(player);
		environment.addEntity(new Monster(43,51));
		environment.addEntity(new NonPlayableCharacter(25, 51));
		playerLifeBar = new LifeBar(player);
		playerLifeBar.getLifeBar().setPrefSize(scene.getWidth()/2, scene.getHeight()/4);
		playerLifeBar.setLifeBarColor("green");
		HBox inventory = new HBox();
		for (int i = 0; i < 3; i++) {
			Button inv = new Button("");
			inv.setPrefSize(50, 50);
			inventory.getChildren().add(inv);
		}
		inventory.setPrefWidth(scene.getWidth()/2);
		inventory.setAlignment(Pos.CENTER);
		inventory.setSpacing(75);
		HBox bottom = new HBox(playerLifeBar.getLifeBar(), inventory);
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
		renderer.render(camera,environment);

		playerLifeBar.getLifeBar().setProgress(player.getLife());
		if(playerLifeBar.getLifeBar().getProgress() < (double)0.01) System.exit(0);
		if(playerLifeBar.getLifeBar().getProgress()<0.3) playerLifeBar.setLifeBarColor("red");
		else if(playerLifeBar.getLifeBar().getProgress()<0.6) playerLifeBar.setLifeBarColor("orange");
	}
}