package main.java.entity.projectile;

import main.java.Environment;
import main.java.entity.Entity;
import main.java.entity.particle.Particle;

public class Projectile extends Entity {
    public Projectile(double x, double y, double vx, double vy) {
        super(x, y, vx, vy);
    }
    //@Override
    public void tick(Environment environment){
        x+=vx;
        y+=vy;
        if(Environment.BLOCK_PROPERTIES.get(environment.getBlock(x,y)).solid()) {
            destroy();
        }
        else{
            Particle particle = spawnParticle(x, y);
            if(particle != null){
                environment.addEntity(particle);
            }
        }
        super.tick(environment);
    }
    protected Particle spawnParticle(double x,double y){
        return null;
    }
}
