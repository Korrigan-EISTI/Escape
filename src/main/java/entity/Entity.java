package main.java.entity;

import main.java.Map;

public abstract class Entity {
	
    protected double x;
    protected double y;
    protected double vx;
    protected double vy;
    protected final double w;
    protected final double h;

    public Entity(double x, double y, double vx, double vy, double w, double h) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.w = w;
        this.h = h;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getVx() {
        return vx;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public double getVy() {
        return vy;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public double getWidth() {
        return w;
    }

    public double getHeight() {
        return h;
    }
    
    public void tick(Map map){
        x += vx;
        y += vy;
    }
}