 package main.java.entity.particle;

import main.java.Environment;
import main.java.entity.Entity;

public class Particle extends Entity {

    /**
     * La durée de vie de la particule.
     */
    protected int duration;

    /**
     * La vitesse de disparition de la particule.
     */
    protected int fading;

    /**
     * Le niveau d'opacité de la particule.
     */
    protected double alpha;

    /**
     * Initialise une nouvelle particule avec les coordonnées et la vitesse spécifiées,
     * ainsi que la durée de vie et la vitesse de disparition.
     *
     * @param x        La position horizontale de la particule.
     * @param y        La position verticale de la particule.
     * @param vx       La composante horizontale de la vitesse de la particule.
     * @param vy       La composante verticale de la vitesse de la particule.
     * @param duration La durée de vie de la particule.
     * @param fading   La vitesse de disparition de la particule.
     */
    public Particle(double x, double y, double vx, double vy, int duration, int fading) {
        super(x, y, vx, vy);
        this.duration = duration;
        this.fading = fading;
        this.alpha = 1.0;
    }

    /**
     * Met à jour la particule à chaque cycle de jeu.
     *
     * @param environment L'environnement de jeu.
     */
    @Override
    public void tick(Environment environment) {
        x += vx;
        y += vy;
        duration -= 1;
        alpha = (double) duration / fading + 1.0;
        if (alpha < 0.0) {
            destroy();
        }
        super.tick(environment);
    }

    /**
     * Retourne le niveau d'opacité de la particule.
     *
     * @return Le niveau d'opacité de la particule.
     */
    public double getAlpha() {
        return alpha;
    }
}
