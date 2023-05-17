package main.java.entity;

import javafx.scene.image.Image;
import main.java.Environment;

public class LivingEntity extends PhysicalEntity{

	private static final Image image = new Image("file:src/main/resources/living_entities/pnj.png");
    double life;
    double maxLife;
    
    public LivingEntity(double x, double y, double life, int size_multiplicator) {
        super(x, y, 0, 0, (double) size_multiplicator*20/24, (double) size_multiplicator*20/24);
        this.life = life;
        maxLife = life;
    }

    public double getMaxLife() {
        return maxLife;
    }

    public void setMaxLife(double maxLife) {
        this.maxLife = maxLife;
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
    	setLife(Math.min(life+d, maxLife));
    }


    @Override
    public Image getImage(){
        return image;
    }

    @Override
    public void tick(Environment environment){

        if(life < 0.001) destroy();
        vy-=0.02;
        vx*=0.7;
        super.tick(environment);
    }
}