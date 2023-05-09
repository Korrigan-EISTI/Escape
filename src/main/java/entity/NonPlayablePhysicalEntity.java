package main.java.entity;

public class NonPlayablePhysicalEntity extends PhysicalEntity{

    public NonPlayablePhysicalEntity(double x, double y) {
        super(x, y, 0, 0, (double) 20 /24, (double) 20 /24);
    }
}