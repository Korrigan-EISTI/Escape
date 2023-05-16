package main.java.entity;

import javafx.scene.image.Image;
import main.java.Environment;
//import main.java.entity.projectile.Bullet;
//import java.util.Random;

public class LivingEntity extends PhysicalEntity{

    double life;
    public LivingEntity(double x, double y) {
        super(x, y, 0, 0, (double) 20/24, (double) 20/24);
        life = 1;
    }
    public double getLife() {
        return life;
    }
    public void setLife(double life) {
        this.life = life;
    }
    public void damage(double d) {
        life-=d;
    }
    public void heal(double d) {
        life+=d;
    }
    private static final Image image = new Image("file:src/main/resources/pnj.png");

    
    @Override
    public Image getImage(){
        return image;
    }
    
    @Override
    public void tick(Environment environment){

        if(life < 0.001) destroy();
    	super.tick(environment);
//        int rand = new Random().nextInt(100);
//        if(rand>=10 && rand<20){
//            double dir = new Random().nextDouble(360);
//            environment.addEntity(new Bullet(x+w/2,y+2*h/3,Math.cos(dir),Math.sin(dir)));
//        }
        
    }
}