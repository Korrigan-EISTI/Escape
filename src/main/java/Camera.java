package main.java;

/**
 * 
 * <p> 
 * Class that create a camera with at reference point the player
 *
 */
public class Camera {
	
    private double x,y;
    private double target_x,target_y;

    public Camera(double x, double y) {
        this.x = x;
        this.y = y;
        this.target_x = x;
        this.target_y = y;
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

    public double getTarget_x() {
        return target_x;
    }

    
    public void setTarget_x(double target_x) {
        this.target_x = target_x;
    }

    public double getTarget_y() {
        return target_y;
    }

    public void setTarget_y(double target_y) {
        this.target_y = target_y;
    }
    public void tick(){
        x = 0.9 * x + 0.1 * target_x;
        y = 0.9 * y + 0.1 * target_y;
    }
}
