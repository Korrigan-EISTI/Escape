package main.java;

import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.java.entity.Arrow;
import main.java.entity.Entity;
import main.java.entity.Monster;
import main.java.entity.Player;
import main.java.entity.NonPlayableCharacter;

public class Escape extends Application {
	
	private Canvas canvas;
	private Camera camera;
	private Renderer renderer;
	private Map map;
	private Input input;
	private Player player;
	private ArrayList<Entity> entities;
	private ArrayList<Arrow> arrows;
	private ProgressBar life;

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
		
		player = new Player(9, 51);
		entities = new ArrayList<Entity>();
		entities.add(player);
		entities.add(new Monster(43,51));
		entities.add(new NonPlayableCharacter(25, 51));
		arrows = new ArrayList<Arrow>();
		
		life = new ProgressBar(1);
		life.setPrefSize(scene.getWidth(), scene.getHeight()/4);
		life.setStyle("-fx-accent: green;");
		HBox bottom = new HBox(life);
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
		Monster monster = null;
		
		for (Entity entity: entities) {
			if(entity instanceof Player) {
				((Player)entity).tick(map);
			}
			else if (entity instanceof Monster){
				monster = ((Monster)entity);
				monster.tick(map);
				
				if(monster.getShoot() == 1) arrows.add(new Arrow(monster.getX(),monster.getY(),-0.1,monster));
				else if(monster.getShoot() == 2) arrows.add(new Arrow(monster.getX(),monster.getY(),0.1,monster));
			}
		}
		
		for (Arrow arrow : arrows) {
			arrow.tick(map, player);
			if(arrow.getHit()==2) player.setLife(player.getLife()-0.1);
		}
		arrows.removeIf(arrow -> arrow.getHit()!=0); //Flèche supprimée en cas de collision
		
		camera.setTarget_x(player.getX()+player.getWidth()/2);
		camera.setTarget_y(player.getY());
		camera.tick();
		renderer.render(map,camera,entities,arrows);
		
		life.setProgress(player.getLife());
		if(life.getProgress() <= 0) System.exit(0);
		if(life.getProgress()<0.3) life.setStyle("-fx-accent: red;");
		else if(life.getProgress()<0.6) life.setStyle("-fx-accent: orange;");
	}
}