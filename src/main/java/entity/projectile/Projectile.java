package main.java.entity.projectile;

import main.java.Environment;
import main.java.entity.Entity;
import main.java.entity.particle.Particle;

public class Projectile extends Entity {
    protected boolean hit;
    protected double nextParticle=0;
    protected double particleInterval;
    public Projectile(double x, double y, double vx, double vy,double particleInterval) {
        super(x, y, vx, vy);
        this.particleInterval=particleInterval;
    }
    //@Override
    public void tick(Environment environment){
        //see https://lodev.org/cgtutor/raycasting.html for implementation details
        int mapX = (int)Math.floor(x);
        int mapY = (int)Math.floor(y);

        //length of ray from current position to next x or y-side
        double sideDistX;
        double sideDistY;

        //length of ray from one x or y-side to next x or y-side

        double deltaDistX = (vx == 0) ? 1e30 : Math.abs(1 / vx);
        double deltaDistY = (vy == 0) ? 1e30 : Math.abs(1 / vy);

        double dist;

        //what direction to step in x or y-direction (either +1 or -1)
        int stepX;
        int stepY;

        int side = 0; //was a NS or a EW wall hit?
        //calculate step and initial sideDist
        if(vx < 0)
        {
            stepX = -1;
            sideDistX = (x - mapX) * deltaDistX;
        }
        else
        {
            stepX = 1;
            sideDistX = (mapX + 1.0 - x) * deltaDistX;
        }
        if(vy < 0)
        {
            stepY = -1;
            sideDistY = (y - mapY) * deltaDistY;
        }
        else
        {
            stepY = 1;
            sideDistY = (mapY + 1.0 - y) * deltaDistY;
        }
        hit = false; //was there a wall hit?
        //perform DDA
        while(!hit && (sideDistX<1 || sideDistY<1))
        {
            //jump to next map square, either in x-direction, or in y-direction
            if(sideDistX < sideDistY)
            {
                sideDistX += deltaDistX;
                mapX += stepX;
                side = 0;
            }
            else
            {
                sideDistY += deltaDistY;
                mapY += stepY;
                side = 1;
            }
            //Check if ray has hit a wall
            if(Environment.BLOCK_PROPERTIES.get(environment.getBlock(mapX,mapY)).solid()){
                hit = true;
            }
        }
        if(side == 0) dist = (sideDistX - deltaDistX);
        else          dist = (sideDistY - deltaDistY);
        dist=hit?dist:1;
        x+=vx*dist;
        y+=vy*dist;
        nextParticle+=dist*Math.sqrt(vx*vx+vy*vy);
        while (nextParticle>particleInterval){
            nextParticle-=particleInterval;
            Particle particle = spawnParticle(x+nextParticle*vx,y+nextParticle*vy);
            if(particle!=null){
                environment.addEntity(particle);
            }
        }
        if(hit) {
            destroy();
        }
        super.tick(environment);
    }
    protected Particle spawnParticle(double x,double y){
        return null;
    }
}
