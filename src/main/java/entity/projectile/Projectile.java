package main.java.entity.projectile;

import main.java.Environment;
import main.java.entity.LivingEntity;
import main.java.entity.PhysicalEntity;
import main.java.entity.particle.Particle;

public class Projectile extends PhysicalEntity {
    protected LivingEntity owner;
    public Projectile(double x, double y, double vx, double vy,double w,double h,LivingEntity owner) {
        super(x, y, vx, vy, w, h);
        this.owner = owner;
    }
    
    @Override
    public void tick(Environment environment){
        super.tick(environment);
        for (int i = 0;i<environment.getEntityCount();i++){
            if(environment.getEntity(i) instanceof LivingEntity livingEntity){
                if(!owner.getClass().equals(livingEntity.getClass())){
                    if(x<livingEntity.getX() + livingEntity.getWidth() && x+w>livingEntity.getX() && y<livingEntity.getY() + livingEntity.getHeight() && y+h>livingEntity.getY()){
                        if(onHit(livingEntity))
                        {
                            destroy();
                            break;
                        }
                    }
                }
            }
        }
        if(vx == 0){
            destroy();
        }
    }
    protected boolean onHit(LivingEntity livingEntity){
        return false;
    }
    protected Particle spawnParticle(double x,double y){
        return null;
    }
}