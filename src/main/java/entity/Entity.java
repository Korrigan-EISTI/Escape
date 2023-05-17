package main.java.entity;

import javafx.scene.image.Image;
import main.java.Environment;

/**
 * Classe abstraite qui d�finit une entit�.
 * 
 * <p>
 * 
 * Une entit� repr�sente tout ce qui peut �tre rendu et qui n'est pas sur la carte.
 * 
 */
public abstract class Entity {
	
	/** Coordonn�e en abscisse */
    protected double x;
    /** Coordonn�e en ordonn�e */
    protected double y;
    /** Coordonn�e de la vitesse en abscisse */
    protected double vx;
    /** Coordonn�e de la vitesse en ordonn�e */
    protected double vy;
    /** Variable bool�enne indiquant si l'entit� doit �tre rendue, initialis�e � false */
    private boolean destroyed = false;

    /**
     * Constructeur de la classe.
     *
     * @param x  La coordonn�e en abscisse
     * @param y  La coordonn�e en ordonn�e
     * @param vx La coordonn�e de la vitesse en abscisse
     * @param vy La coordonn�e de la vitesse en ordonn�e
     */
    public Entity(double x, double y, double vx, double vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }

    /**
     * Retourne la coordonn�e en abscisse de l'entit�.
     *
     * @return La coordonn�e en abscisse
     */
    public double getX() {
        return x;
    }

    /**
     * D�finit la coordonn�e en abscisse de l'entit�.
     *
     * @param x La coordonn�e en abscisse � d�finir
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Retourne la coordonn�e en ordonn�e de l'entit�.
     *
     * @return La coordonn�e en ordonn�e
     */
    public double getY() {
        return y;
    }

    /**
     * D�finit la coordonn�e en ordonn�e de l'entit�.
     *
     * @param y La coordonn�e en ordonn�e � d�finir
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Retourne la coordonn�e de la vitesse en abscisse de l'entit�.
     *
     * @return La coordonn�e de la vitesse en abscisse
     */
    public double getVx() {
        return vx;
    }

    /**
     * D�finit la coordonn�e de la vitesse en abscisse de l'entit�.
     *
     * @param vx La coordonn�e de la vitesse en abscisse � d�finir
     */
    public void setVx(double vx) {
        this.vx = vx;
    }

    /**
     * Retourne la coordonn�e de la vitesse en ordonn�e de l'entit�.
     *
     * @return La coordonn�e de la vitesse en ordonn�e
     */
    public double getVy() {
        return vy;
    }

    /**
     * D�finit la coordonn�e de la vitesse en ordonn�e de l'entit�.
     *
     * @param vy La coordonn�e de la vitesse en ordonn�e � d�finir
     */
    public void setVy(double vy) {
        this.vy = vy;
    }
    
    /**
     * Met � jour l'entit� � chaque tick du jeu.
     * V�rifie si l'entit� est en dehors des limites de l'environnement et la d�truit si c'est le cas.
     *
     * @param environment L'environnement du jeu
     */
    public void tick(Environment environment){
        if (x < -100 || x > environment.getWidth() + 100 || y < -100 || y > environment.getHeight() + 100){
            destroy(environment);
        }
    }

    /**
     * V�rifie si l'entit� est d�truite.
     *
     * @return true si l'entit� est d�truite, sinon false
     */
    public boolean destroyed() {
        return destroyed;
    }

    /**
     * D�truit l'entit�.
     */
    public void destroy(Environment environment) {
        this.destroyed = true;
    }
    
    /**
     * D�truit l'entit�.
     */
    public void destroy() {
        this.destroyed = true;
    }

    /**
     * Retourne l'image de l'entit�.
     *
     * @return L'image de l'entit�
     */
    public Image getImage(){
        return null;
    }

    /**
     * Retourne le d�calage de l'image en abscisse.
     *
     * @return Le d�calage de l'image en abscisse
     */
    public double getImageOffsetX(){
        return 0;
    }

    /**
     * Retourne le d�calage de l'image en ordonn�e.
     *
     * @return Le d�calage de l'image en ordonn�e
     */
    public double getImageOffsetY(){
        return 0;
    }

    /**
     * Retourne la taille de l'image en abscisse.
     *
     * @return La taille de l'image en abscisse
     */
    public double getImageSizeX(){
        return 1;
    }

    /**
     * Retourne la taille de l'image en ordonn�e.
     *
     * @return La taille de l'image en ordonn�e
     */
    public double getImageSizeY(){
        return 1;
    }
}
