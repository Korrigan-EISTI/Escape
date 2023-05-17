package main.java.entity;

import main.java.Environment;

import java.util.Arrays;

/**
 * Classe abstraite repr�sentant une entit� physique.
 * Une entit� physique poss�de une taille, peut se d�placer et interagir avec l'environnement du jeu.
 */
public abstract class PhysicalEntity extends Entity {

    protected double w; // Largeur de l'entit�
    protected double h; // Hauteur de l'entit�
    protected boolean on_ground; // Indique si l'entit� est au sol
    protected short[] touched; // Tableau des blocs touch�s par l'entit�
    private int last_shot; // Temps depuis le dernier tir effectu� par l'entit�

    /**
     * Constructeur de la classe PhysicalEntity.
     *
     * @param x  La coordonn�e en abscisse de l'entit�.
     * @param y  La coordonn�e en ordonn�e de l'entit�.
     * @param vx La vitesse horizontale de l'entit�.
     * @param vy La vitesse verticale de l'entit�.
     * @param w  La largeur de l'entit�.
     * @param h  La hauteur de l'entit�.
     */
    public PhysicalEntity(double x, double y, double vx, double vy, double w, double h) {
        super(x, y, vx, vy);
        this.w = w;
        this.h = h;
        this.on_ground = false;
        this.touched = new short[(int) (Math.ceil(w + 1) * Math.ceil(h + 10))];
    }

    /**
     * Renvoie le temps �coul� depuis le dernier tir effectu� par l'entit�.
     *
     * @return Le temps �coul� depuis le dernier tir.
     */
    public int getLast_shot() {
        return last_shot;
    }

    /**
     * D�finit le temps �coul� depuis le dernier tir effectu� par l'entit�.
     *
     * @param last_shot Le temps �coul� depuis le dernier tir.
     */
    public void setLast_shot(int last_shot) {
        this.last_shot = last_shot;
    }

    /**
     * Met � jour l'�tat de l'entit� � chaque it�ration du jeu.
     *
     * @param environment L'environnement du jeu.
     */
    @Override
    public void tick(Environment environment) {

        setLast_shot(getLast_shot() - 1);
        on_ground = false;

        // D�tection de collision avec les blocs horizontaux lors du d�placement horizontal
        if (vx < 0) {
            int scan_x = (int) Math.floor(x);
            int end_x = (int) Math.floor(x + vx);
            int start_y = (int) Math.floor(y);
            int end_y = (int) Math.ceil(y + h) - 1;
            boolean hit = false;
            while (scan_x>end_x && !hit){
            	scan_x-=1;
                for (int i = start_y;i<=end_y;i++){
                    hit = hit || Environment.BLOCK_PROPERTIES.get(environment.getBlock(scan_x,i)).solid();
                }
            }
            if (hit) {
                vx = 0;
                x = scan_x + 1;
            } else {
                x += vx;
            }
        } else {
            int scan_x = (int) Math.ceil(x + w) - 1;
            int end_x = (int) Math.floor(x + w + vx);
            int start_y = (int) Math.floor(y);
            int end_y = (int) Math.ceil(y + h) - 1;
            boolean hit = false;
            while (scan_x<end_x && !hit){
                scan_x+=1;
                for (int i = start_y;i<=end_y;i++){
                    hit = hit || Environment.BLOCK_PROPERTIES.get(environment.getBlock(scan_x,i)).solid();
            
                }
            }
            if (hit) {
                vx = 0;
                x = scan_x - w;
            } else {
                x += vx;
            }
        }

        // D�tection de collision avec les blocs verticaux lors du d�placement vertical
        if (vy < 0) {
            int scan_y = (int) Math.floor(y);
            int end_y = (int) Math.floor(y + vy);
            int start_x = (int) Math.floor(x);
            int end_x = (int) Math.ceil(x + w) - 1;
            boolean hit = false;
            while (scan_y>end_y && !hit){
                scan_y-=1;
                for (int i = start_x;i<=end_x;i++){
                    hit = hit || Environment.BLOCK_PROPERTIES.get(environment.getBlock(i,scan_y)).solid();
           
                }
            }
            if (hit) {
                on_ground = true;
                vy = 0;
                y = scan_y + 1;
            } else {
                y += vy;
            }
        }
        else {
            int scan_y = (int)Math.ceil(y+h)-1;
            int end_y = (int)Math.floor(y+h+vy);
            int start_x = (int)Math.floor(x);
            int end_x = (int)Math.ceil(x+w)-1;
            boolean hit = false;
            while (scan_y<end_y && !hit){
                scan_y+=1;
                for (int i = start_x;i<=end_x;i++){
                    hit = hit || Environment.BLOCK_PROPERTIES.get(environment.getBlock(i,scan_y)).solid();
                }
            }
            if(hit){
                vy=0;
                y=scan_y-h;
            }
            else{
                y+=vy;
            }
        }

        // Mise � jour des blocs touch�s par l'entit�
        int bottom_x = (int) Math.floor(x);
        int top_x = (int) Math.ceil(x + w) - 1;
        int bottom_y = (int) Math.floor(y);
        int top_y = (int) Math.ceil(y + h) - 1;
        Arrays.fill(touched, (short) 0);
        int i = 0;
        for (int j = bottom_x; j <= top_x; j++) {
            for (int k = bottom_y; k <= top_y; k++) {
                touched[i] = environment.getBlock(j, k);
                i++;
            }
        }
    }

    /**
     * V�rifie si l'entit� est au sol.
     *
     * @return Vrai si l'entit� est au sol, sinon faux.
     */
    public boolean isOnGround() {
        return on_ground;
    }

    /**
     * Renvoie la largeur de l'entit�.
     *
     * @return La largeur de l'entit�.
     */
    public double getWidth() {
        return w;
    }

    /**
     * Renvoie la hauteur de l'entit�.
     *
     * @return La hauteur de l'entit�.
     */
    public double getHeight() {
        return h;
    }

    /**
     * Renvoie la taille de l'image associ�e � l'entit� selon l'axe des abscisses.
     *
     * @return La taille de l'image selon l'axe des abscisses.
     */
    @Override
    public double getImageSizeX() {
        return w;
    }

    /**
     * Renvoie la taille de l'image associ�e � l'entit� selon l'axe des ordonn�es.
     *
     * @return La taille de l'image selon l'axe des ordonn�es.
     */
    @Override
    public double getImageSizeY() {
        return h;
    }
}
