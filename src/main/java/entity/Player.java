package main.java.entity;

import javafx.scene.input.KeyCode;
import main.java.BlockProperties;
import main.java.Input;
import main.java.Map;

public class Player extends PhysicalEntity{
    protected boolean climbing;

    public Player(double x, double y) {
        super(x, y, 0, 0, (double) 20 /24, (double) 20 /24);
    }

    @Override
    public void tick(Map map) {
        super.tick(map);
        climbing=false;
        for (short block : touched){
            climbing=climbing || Map.BLOCK_PROPERTIES.get(block).climbable();
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
        if(input.keyPressed(KeyCode.UP) && (on_ground || climbing)){
            vy=0.3;
        }
        if(input.keyPressed(KeyCode.DOWN) && climbing){
            vy=-0.3;
        }
    }
}