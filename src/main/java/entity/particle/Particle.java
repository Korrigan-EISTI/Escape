 package main.java.entity.particle;

import main.java.Environment;
import main.java.entity.Entity;

public class Particle extends Entity {

    /**
     * La dur�e de vie de la particule.
     */
    protected int duration;

    /**
     * La vitesse de disparition de la particule.
     */
    protected int fading;

    /**
     * Le niveau d'opacit� de la particule.
     */
    protected double alpha;

    /**
     * Initialise une nouvelle particule avec les coordonn�es et la vitesse sp�cifi�es,
     * ainsi que la dur�e de vie et la vitesse de disparition.
     *
     * @param x        La position horizontale de la particule.
     * @param y        La position verticale de la particule.
     * @param vx       La composante horizontale de la vitesse de la particule.
     * @param vy       La composante verticale de la vitesse de la particule.
     * @param duration La dur�e de vie de la particule.
     * @param fading   La vitesse de disparition de la particule.
     */
    public Particle(double x, double y, double vx, double vy, int duration, int fading) {
        super(x, y, vx, vy);
        this.duration = duration;
        this.fading = fading;
        this.alpha = 1.0;
    }

    /**
     * Met � jour la particule � chaque cycle de jeu.
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
     * Retourne le niveau d'opacit� de la particule.
     *
     * @return Le niveau d'opacit� de la particule.
     */
    public double getAlpha() {
        return alpha;
    }
}
