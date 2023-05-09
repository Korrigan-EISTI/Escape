package main.java.entity;

import javafx.scene.image.Image;

public class NonPlayablePhysicalEntity extends PhysicalEntity{
    private static final Image image = new Image("file:src/main/resources/pnj.png");

    public NonPlayablePhysicalEntity(double x, double y) {
        super(x, y, 0, 0, (double) 20 /24, (double) 20 /24);
    }
    @Override
    public Image getImage(){
        return image;
    }
}