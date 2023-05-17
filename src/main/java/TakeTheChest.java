/**
 * @author Martin Mac� de Gastines, Louis-Alexandre Laguet, Alexis Tourrenc--Lecerf
 * @version 1.0
 * <p>
 * Main class of the program
 */

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
import main.java.entity.Player;
import main.java.entity.NonPlayableCharacter;


/**
 * <p>
 * Main class of the program
 */
public class TakeTheChest extends Application {
	/** JavaFX Canvas that will paint the render window*/
	private Canvas canvas;
	/** Boolean variable who will be true if the player is dead*/
	private boolean dead;
	/** Camera center on the player*/
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

	/**
	 * 
	 * Start the main application 
	 * <p>
	 * @param Stage This stage is the main stage of the JavaFX application
	 * 
	 * 
	 * */
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

	/**
	 * 
	 * Function tick
	 * <p> 
	 * This function actualize the game 60 times per seconds
	 * 
	 * @param ActionEvent An action that the user has input
	 * */
	
	private void tick(ActionEvent actionEvent) {
		
		environment.getPlayer().handleInput(input,environment);
		environment.tickEntities();
		
		camera.setTarget_x(environment.getPlayer().getX() + environment.getPlayer().getWidth()/2);
		camera.setTarget_y(environment.getPlayer().getY());
		camera.tick();
		
		renderer.render(camera,environment);
		
		if (environment.getPlayer().isAllowToShoot()) {
			inventory.getSlot1().setImage(new Image("file:src/main/resources/inventory_bow.png"));
		}
		if (environment.getPlayer().isBowUpgraded()) {
			inventory.getSlot1().setImage(new Image("file:src/main/resources/inventory_bow_upgraded.png"));
		}
		if (environment.getPlayer().hasKey()) {
			inventory.getSlot2().setImage(new Image("file:src/main/resources/inventory_key.png"));
		}
		if (environment.getPlayer().hasWallPotion()) {
			inventory.getSlot3().setImage(new Image("file:src/main/resources/inventory_wall_potion.png"));
		}
		if (environment.getPlayer().canWalkThroughMagicWalls()) {
			inventory.getSlot3().setImage(new Image("file:src/main/resources/inventory_wall_potion_used.png"));
		}
		if(environment.getPlayer().getLife()<=0.1 && !dead) gameOver();
		if (environment.getGameProgression() == 6) victory();
	}
	
	/**
	 * 
	 * Method that create the dead scene where the player is dead
	 * */
	
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
	
	/**
	 * 
	 * Method that create the dead scene where the player is dead
	 * */
	private void victory() {
		dead = true;
		BorderPane winPane = new BorderPane();
		ImageView imgView = new ImageView(new Image("file:src/main/resources/game_over.png", 500, 500, false, false));
		winPane.setCenter(imgView);
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
		winPane.setBottom(bottom);
		winPane.setPrefSize(1000, 750);
		Scene win = new Scene(winPane, 1000, 750);
		winPane.setPrefSize(win.getWidth(), win.getHeight());
		stage.setScene(win);
		stage.setTitle("You Win");
		stage.show();
	}
}