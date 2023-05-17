package main.java;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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

/**
 * La classe TakeTheChest représente l'application principale du jeu.
 * Elle étend la classe Application de JavaFX et est responsable de la configuration de l'environnement du jeu,
 * de la gestion des ticks du jeu, du rendu du jeu et de la gestion des états de fin de partie (game over et victoire).
 * @author Macé de Gastines Martin, Laguet Louis-Alexandre, Tourrenc--Lecerf Alexis
 * @version 1.0
 */
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
    private Text score;
    private HBox score_hbox;

    /**
     * Le point d'entrée principal de l'application.
     *
     * @param args Les arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initialise le jeu et configure la fenêtre du jeu.
     *
     * @param stage Le stage principal de l'application sur lequel la scène peut être définie.
     */
    @Override
    public void start(Stage stage) {
    	
        if (dead) {
            dead = false;
            time.stop();
        }

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 1000, 750);
        
		canvas = new Canvas(scene.getWidth(), scene.getHeight()-100);
		canvas.setFocusTraversable(true);
		environment = new Environment();
		renderer = new Renderer(canvas);
		camera = new Camera(100, 200);
		root.setCenter(canvas);
		
		environment.generateMonsters();
		environment.generateItems();
		
		score = new Text();
		score.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		score_hbox = new HBox(score);
		score_hbox.setMaxWidth(scene.getWidth());
		score_hbox.setPrefHeight(25);
		score_hbox.setAlignment(Pos.CENTER);
        root.setTop(score_hbox);

        inventory = new Inventory();
        inventory.getHbox().setPrefWidth(scene.getWidth());
        inventory.getHbox().setAlignment(Pos.CENTER);
        inventory.getHbox().setSpacing(75);
        inventory.getHbox().setMinHeight(75);
        HBox bottom = new HBox(inventory.getHbox());
        root.setBottom(bottom);
        
        input = new Input(scene);

        stage.setTitle("Take the Chest");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        this.stage = stage;

        int frameRate = 60;
        final Duration d = Duration.millis((double) 1000 / frameRate);
        final KeyFrame oneFrame = new KeyFrame(d, this::tick);

        time = new Timeline(frameRate, oneFrame);
		time.setCycleCount(Animation.INDEFINITE);
		time.playFromStart();
    }

	/**
	 * Effectue une itération du jeu lors d'un tick d'horloge.
	 *
	 * @param actionEvent L'événement d'action déclenché lors du tick d'horloge.
	 */
	
	private void tick(ActionEvent actionEvent) {

		score.setText(String.valueOf((int)environment.getPlayer().getScore()));
		
		environment.getPlayer().handleInput(input,environment);
		environment.tickEntities();
		
		camera.setTarget_x(environment.getPlayer().getX() + environment.getPlayer().getWidth()/2);
		camera.setTarget_y(environment.getPlayer().getY());
		camera.tick();
		
		renderer.render(camera,environment);
		
		if (environment.getPlayer().isAllowToShoot()) {
			inventory.getSlot1().setImage(new Image("file:src/main/resources/inventory/inventory_bow.png"));
		}
		if (environment.getPlayer().isBowUpgraded()) {
			inventory.getSlot1().setImage(new Image("file:src/main/resources/inventory/inventory_bow_upgraded.png"));
		}
		if (environment.getPlayer().hasKey()) {
			inventory.getSlot2().setImage(new Image("file:src/main/resources/inventory/inventory_key.png"));
		}
		if (environment.getPlayer().hasWallPotion()) {
			inventory.getSlot3().setImage(new Image("file:src/main/resources/inventory/inventory_wall_potion.png"));
		}
		if (environment.getPlayer().canWalkThroughMagicWalls()) {
			inventory.getSlot3().setImage(new Image("file:src/main/resources/inventory/inventory_wall_potion_used.png"));
		}
		if(environment.getPlayer().getLife()<=0.1 && !dead) gameOver();
		if (environment.getGameProgression() == Environment.Progress.POTATO) inventory.getSlot2().setImage(new Image("file:src/main/resources/inventory/inventory_potato.png"));
		if (environment.getGameProgression() == Environment.Progress.WIN && !dead) victory();
	}
	
	/**
	 * Affiche l'écran de fin de partie en cas de victoire.
	 */
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
		bottom.setAlignment(Pos.CENTER);
		bottom.setPrefSize(750, 100);
		dead.setBottom(bottom);
		dead.setPrefSize(1000, 750);
		Scene death = new Scene(dead, 1000, 750);
		dead.setPrefSize(death.getWidth(), death.getHeight());
		stage.setScene(death);
		stage.setTitle("Game Over");
		stage.show();
	}
	
	/**
     * Affiche l'écran de fin de partie en cas de victoire.
     */
	private void victory() {
		
		environment.getPlayer().addScore(environment.getPlayer().getLife()*50);
		score.setText(String.valueOf((int)environment.getPlayer().getScore()));
		dead = true;
		
		BorderPane winPane = new BorderPane();
		ImageView imgView = new ImageView(new Image("file:src/main/resources/win.jpg", 500, 500, false, false));
		
		Text score_display = new Text("Score : " + score.getText());
		score_display.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		
		VBox img_and_score = new VBox(imgView,score_display);
		img_and_score.setPrefWidth(750);
		img_and_score.setSpacing(50);
		img_and_score.setAlignment(Pos.CENTER);
		
		winPane.setCenter(img_and_score);
		
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
		bottom.setAlignment(Pos.CENTER);
		bottom.setPrefSize(750, 100);
		winPane.setBottom(bottom);
		
		Scene win = new Scene(winPane, 1000, 750);
		winPane.setPrefSize(win.getWidth(), win.getHeight());
		
		stage.setScene(win);
		stage.setTitle("You Win");
		stage.show();
	}
}
