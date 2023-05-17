package main.java.entity.projectile;

import javafx.scene.image.Image;
import main.java.entity.LivingEntity;
import main.java.entity.particle.Particle;
import main.java.entity.particle.Tracer;

/**
 * Cette classe représente une balle.
 */
public class Bullet extends Projectile {

    private static final Image image = new Image("file:src/main/resources/bullet.png");

    /**
     * Constructeur de la classe Bullet.
     *
     * @param x      La coordonnée X de départ de la balle.
     * @param y      La coordonnée Y de départ de la balle.
     * @param vx     La vitesse horizontale de la balle.
     * @param vy     La vitesse verticale de la balle.
     * @param owner  L'entité parente de la balle.
     */
    public Bullet(double x, double y, double vx, double vy, LivingEntity owner) {
        super(x, y, vx, vy, 0.2, 0.2, owner);
    }

    /**
     * Permet de créer une particule de trace à l'endroit spécifié.
     *
     * @param x La coordonnée X de la particule.
     * @param y La coordonnée Y de la particule.
     * @return La particule de trace créée.
     */
    @Override
    protected Particle spawnParticle(double x, double y) {
        return new Tracer(x, y, 0, 0);
    }

    /**
     * Renvoie l'image correspondante à la balle.
     *
     * @return L'image de la balle.
     */
    public Image getImage() {
        return image;
    }
}
