package main.java.entity.particle;

import main.java.Environment;
import main.java.entity.Entity;

public class Particle extends Entity {
    protected int duration;
    protected int fading;
    protected double alpha;


    public Particle(double x, double y, double vx, double vy,int duration,int fading) {
        super(x, y, vx, vy);
        this.duration = duration;
        this.fading = fading;
        this.alpha = 1.0;
    }
    @Override
    public void tick(Environment environment){
        x+=vx;
        y+=vy;
        duration-=1;
        alpha = (double) duration / fading + 1.0;
        if(alpha < 0.0){
            destroy();
        }
        super.tick(environment);
    }

    public double getAlpha() {
        return alpha;
    }
}
