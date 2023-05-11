package main.java.entity;

import javafx.scene.image.Image;
import main.java.Environment;

public abstract class Entity {
    protected double x;
    protected double y;
    protected double vx;
    protected double vy;
    private boolean destroy=false;
    static public final String image_path = null;
    static public final double image_offset_x = 0;
    static public final double image_offset_y = 0;
    static public final double image_scale = 1;


    public Entity(double x, double y, double vx, double vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
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

    public void tick(Environment environment){
        if(x<-100 || x> environment.getWidth()+100 || y<-100 || y> environment.getHeight()+100){
            destroy();
        }
    }
    public Image getImage(){
        return null;
    }
    public double getImageOffsetX(){
        return 0;
    }
    public double getImageOffsetY(){
        return 0;
    }
    public double getImageSizeX(){
        return 1;
    }
    public double getImageSizeY(){
        return 1;
    }
    public boolean destroyed() {
        return destroy;
    }
    public void destroy() {
        this.destroy = true;
    }
}