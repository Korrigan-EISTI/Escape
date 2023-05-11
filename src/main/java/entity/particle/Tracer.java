package main.java.entity.particle;

import javafx.scene.image.Image;

public class Tracer extends Particle{

    private static final Image image = new Image("file:src/main/resources/tracer.png");

    public Tracer(double x, double y, double vx, double vy) {
        super(x, y, vx, vy, 0, 10);
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
