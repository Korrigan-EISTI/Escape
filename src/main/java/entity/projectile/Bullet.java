package main.java.entity.projectile;

import javafx.scene.image.Image;
import main.java.entity.particle.Particle;
import main.java.entity.particle.Tracer;

public class Bullet extends Projectile{
    private static final Image image = new Image("file:src/main/resources/bullet.png");

    public Bullet(double x, double y, double vx, double vy) {
        super(x, y, vx, vy,0.1);
    }
    @Override
    protected Particle spawnParticle(double x, double y){
        return new Tracer(x,y,0,0);
    }
    public Image getImage(){
        return image;
    }
    public double getImageOffsetX(){
        return -0.1;
    }
    public double getImageOffsetY(){
        return -0.1;
    }
    public double getImageSizeX(){
        return 0.2;
    }
    public double getImageSizeY(){
        return 0.2;
    }
}
