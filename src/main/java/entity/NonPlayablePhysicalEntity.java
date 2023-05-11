package main.java.entity;

import javafx.scene.image.Image;
import main.java.Environment;
import main.java.entity.projectile.Bullet;

import java.util.Random;

public class NonPlayablePhysicalEntity extends PhysicalEntity{
    private static final Image image = new Image("file:src/main/resources/pnj.png");

    public NonPlayablePhysicalEntity(double x, double y,double life) {
        super(x, y, 0, 0, (double) 20 /24, (double) 20 /24,life);
    }
    @Override
    public Image getImage(){
        return image;
    }
    @Override
    public void tick(Environment environment){
        int rand = new Random().nextInt(100);
        if(rand==99 || rand== 98 || rand == 97){
            vy=0.3;
        }
        if(rand>0 && rand<5){
            vx=-0.3;
        }
        if(rand>=5 && rand<10){
            vx=0.3;
        }
        super.tick(environment);
    }
}