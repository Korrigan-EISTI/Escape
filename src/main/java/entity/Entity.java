package main.java.entity;

import javafx.scene.image.Image;
import main.java.Environment;

/**
 * Abstract Class that define an Entity
 * 
 * <p>
 * 
 * An entity is all of the renderable thing that are not in the map
 * 
 * 
 */
public abstract class Entity {
	
	/** Coordinate in abscissa */
    protected double x;
    /** Coordinate in ordinate */
    protected double y;
    /** Coordinate for speed in abscissa*/
    protected double vx;
    /** Coordinate for speed in ordinate*/
    protected double vy;
    /** Boolean variable to know if the renderer need to render the entity initalise at false*/
    private boolean destroyed = false;

    /**
     * 
     * Constructor of the class
     * @param Double x Create coordinate in abscissa
     * @param Double y Create coordinate in ordinate
     * @param Double vx Create speed coordinate in abscissa
     * @param Double vy Create speed coordinate in ordinate
     */
    public Entity(double x, double y, double vx, double vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }

    /**
     * Method to get the coordinate on abscissa
     * @return Double coordinate on abscissa 
     */
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    /**
     * Method to get the coordinate on ordinate
     * @return Double coordinate on ordinate
     */
    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    /**
     * Method to get the speed coordinate on abscissa
     * @return Double speed coordinate on abscissa 
     */
    public double getVx() {
        return vx;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    /**
     * Method to get the speed coordinate on ordinate
     * @return Double speed coordinate on ordinate
     */
    public double getVy() {
        return vy;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }
    
    public void tick(Environment environment){
        if (x<-100 || x>environment.getWidth()+100 || y<-100 || y>environment.getHeight()+100){
            destroy();
        }
    }

    public boolean destroyed() {
        return destroyed;
    }

    public void destroy() {
        this.destroyed = true;
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
}