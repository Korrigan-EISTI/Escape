package main.java.entity;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import main.java.Environment;
import main.java.Input;

public class Player extends PhysicalEntity{
    protected boolean climbing;
    private static final Image image = new Image("file:src/main/resources/player.png");

    public Player(double x, double y) {
        super(x, y, 0, 0, (double) 20 /24, (double) 20 /24);
    }

    @Override
    public void tick(Environment environment) {
        super.tick(environment);
        climbing=false;
        for (short block : touched){
            climbing=climbing || Environment.BLOCK_PROPERTIES.get(block).climbable();
        }
        if(climbing){
            vx=0;
            vy=0;
        }
    }
    public void handleInput(Input input){

        if(input.keyPressed(KeyCode.RIGHT)){
            vx+=0.07;
        }
        if(input.keyPressed(KeyCode.LEFT)){
            vx-=0.07;
        }
        if(input.keyPressed(KeyCode.UP) && on_ground){
            vy=0.45;
        }
        if(input.keyPressed(KeyCode.UP) && climbing){
            vy=0.3;
        }
        if(input.keyPressed(KeyCode.DOWN) && climbing){
            vy=-0.3;
        }
    }
    @Override
    public Image getImage(){
        return image;
    }
}
