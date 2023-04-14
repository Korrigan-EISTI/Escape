package main.java.entity;

public class PhysicalEntity extends Entity{
    private double w;
    private double h;
    public PhysicalEntity(double x, double y, double vx, double vy, double w, double h) {
        super(x, y, vx, vy);
        this.w = w;
        this.h = h;
    }
}
