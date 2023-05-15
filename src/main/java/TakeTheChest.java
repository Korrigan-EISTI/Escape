package main.java;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.java.entity.Key;
import main.java.entity.Player;
import main.java.entity.NonPlayableCharacter;

public class TakeTheChest extends Application {

	private Canvas canvas;
	private boolean dead;
	private Camera camera;
	private Renderer renderer;
	private Environment environment;
	private Input input;
	private Stage stage;
	private Inventory inventory;
	private Timeline time;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		
		if (dead) {
			dead = false;
			time.stop();
		}
		VBox root = new VBox();
		root.getStyleClass().add("root");
		Scene scene = new Scene(root,1000,750);

		canvas = new Canvas(scene.getWidth(), scene.getHeight()-100);
		canvas.setFocusTraversable(true);
		environment = new Environment();
		renderer = new Renderer(canvas);
		camera = new Camera(100, 200);
		root.getChildren().add(canvas);
		
		environment.setPlayer(new Player(9, 51));
		environment.generateMonsters();
		environment.addEntity(new NonPlayableCharacter(25, 51));

		inventory = new Inventory();
		inventory.getHbox().setPrefWidth(scene.getWidth());
		inventory.getHbox().setAlignment(Pos.CENTER);
		inventory.getHbox().setSpacing(75);
		inventory.getHbox().setMinHeight(100);
		HBox bottom = new HBox(inventory.getHbox());
		environment.generateItems();
		root.getChildren().add(bottom);

		input = new Input(scene);
		scene.getStylesheets().add("main/resources/test.css");

		stage.setTitle("Take the Chest");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		this.stage = stage;

		int frameRate = 60;
		final Duration d = Duration.millis((double) 1000 / frameRate);
		final KeyFrame oneFrame = new KeyFrame(d, this::tick);

		Timeline t = new Timeline(frameRate, oneFrame);
		t.setCycleCount(Animation.INDEFINITE);
		t.playFromStart();
		time = t;
	}

	private void tick(ActionEvent actionEvent) {
		environment.getPlayer().handleInput(input,environment);
		environment.tickEntities();
		camera.setTarget_x(environment.getPlayer().getX() + environment.getPlayer().getWidth()/2);
		camera.setTarget_y(environment.getPlayer().getY());
		camera.tick();
		renderer.render(camera,environment);
		if (environment.getPlayer().hasKey()) {
			inventory.getBtn2().setGraphic(new ImageView(Key.img));			
		}
		if(environment.getPlayer().getLife()<=0.1 && !dead) gameOver();
	}
	
	private void gameOver() {
		dead = true;
		BorderPane dead = new BorderPane();
		ImageView imgView = new ImageView(new Image("file:src/main/resources/game_over.png", 500, 500, false, false));
		dead.setCenter(imgView);
		Button quit = new Button ("Quitter");
		quit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				System.exit(0);
			}
		});
		Button relaunch = new Button ("Relancer");
		relaunch.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				start(stage);
			}
		});
		HBox bottom = new HBox (quit, relaunch);
		dead.setBottom(bottom);
		dead.setPrefSize(1000, 750);
		Scene death = new Scene(dead, 1000, 750);
		dead.setPrefSize(death.getWidth(), death.getHeight());
		stage.setScene(death);
		stage.setTitle("Game Over");
		stage.show();
	}
}