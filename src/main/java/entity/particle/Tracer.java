 package main.java.entity.particle;

import javafx.scene.image.Image;

public class Tracer extends Particle {

    /**
     * L'image du tracer.
     */
    private static final Image image = new Image("file:src/main/resources/tracer.png");

    /**
     * Initialise un nouvel objet Tracer avec les coordonnées et vitesses spécifiées.
     *
     * @param x  La position horizontale du tracer.
     * @param y  La position verticale du tracer.
     * @param vx La vitesse horizontale du tracer.
     * @param vy La vitesse verticale du tracer.
     */
    public Tracer(double x, double y, double vx, double vy) {
        super(x, y, vx, vy, 0, 10);
    }

    /**
     * Retourne l'image du tracer.
     *
     * @return L'image du tracer.
     */
    public Image getImage() {
        return image;
    }

    /**
     * Retourne le décalage horizontal de l'image du tracer.
     *
     * @return Le décalage horizontal de l'image du tracer.
     */
    public double getImageOffsetX() {
        return -0.1;
    }

    /**
     * Retourne le décalage vertical de l'image du tracer.
     *
     * @return Le décalage vertical de l'image du tracer.
     */
    public double getImageOffsetY() {
        return -0.1;
    }

    /**
     * Retourne la taille horizontale de l'image du tracer.
     *
     * @return La taille horizontale de l'image du tracer.
     */
    public double getImageSizeX() {
        return 0.2;
    }

    /**
     * Retourne la taille verticale de l'image du tracer.
     *
     * @return La taille verticale de l'image du tracer.
     */
    public double getImageSizeY() {
        return 0.2;
    }
}
