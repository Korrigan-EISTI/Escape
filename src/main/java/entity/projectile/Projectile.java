package main.java.entity.projectile;

import main.java.Environment;
import main.java.entity.PhysicalEntity;
import main.java.entity.particle.Particle;

public class Projectile extends PhysicalEntity {
	
    public Projectile(double x, double y, double vx, double vy,double w,double h) {
        super(x, y, vx, vy, w, h);
    }
    
    @Override
    public void tick(Environment environment){
        super.tick(environment);
        if(vx == 0){
            destroy();
        }
    }
    
    protected Particle spawnParticle(double x,double y){
        return null;
    }
}