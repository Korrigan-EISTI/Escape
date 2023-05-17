package main.java.entity.projectile;

import javafx.scene.image.Image;
import main.java.entity.LivingEntity;
import main.java.entity.particle.Particle;
import main.java.entity.particle.Tracer;

/**
 * Cette classe repr�sente une balle.
 */
public class Bullet extends Projectile {

    private static final Image image = new Image("file:src/main/resources/bullet.png");

    /**
     * Constructeur de la classe Bullet.
     *
     * @param x      La coordonn�e X de d�part de la balle.
     * @param y      La coordonn�e Y de d�part de la balle.
     * @param vx     La vitesse horizontale de la balle.
     * @param vy     La vitesse verticale de la balle.
     * @param owner  L'entit� parente de la balle.
     */
    public Bullet(double x, double y, double vx, double vy, LivingEntity owner) {
        super(x, y, vx, vy, 0.2, 0.2, owner);
    }

    /**
     * Permet de cr�er une particule de trace � l'endroit sp�cifi�.
     *
     * @param x La coordonn�e X de la particule.
     * @param y La coordonn�e Y de la particule.
     * @return La particule de trace cr��e.
     */
    @Override
    protected Particle spawnParticle(double x, double y) {
        return new Tracer(x, y, 0, 0);
    }

    /**
     * Renvoie l'image correspondante � la balle.
     *
     * @return L'image de la balle.
     */
    public Image getImage() {
        return image;
    }
}
