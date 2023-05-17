package main.java.entity;

import javafx.scene.image.Image;
import main.java.Environment;

/**
 * Classe représentant une entité vivante.
 * Une entité vivante est une entité physique avec une quantité de vie.
 */
public class LivingEntity extends PhysicalEntity {

	private static final Image image = new Image("file:src/main/resources/living_entities/pnj.png");

    double life;
    double maxLife;

	/**
	 * Constructeur de la classe LivingEntity.
	 *
	 * @param x     La coordonnée en abscisse de l'entité vivante.
	 * @param y     La coordonnée en ordonnée de l'entité vivante.
	 * @param life  La quantité de vie de l'entité vivante.
	 */
    public LivingEntity(double x, double y, double life, int size_multiplicator) {
        super(x, y, 0, 0, (double) size_multiplicator*20/24, (double) size_multiplicator*20/24);
        this.life = life;
        maxLife = life;
    }

    /**
     * Renvoie la quantité maximale de vie de l'entité vivante.
     *
     * @return La quantité maximale de vie.
     */
    public double getMaxLife() {
        return maxLife;
    }

    /**
     * Définit la quantité maximale de vie de l'entité vivante.
     *
     * @param maxLife La quantité maximale de vie à définir.
     */
    public void setMaxLife(double maxLife) {
        this.maxLife = maxLife;
    }

    /**
     * Renvoie la quantité de vie actuelle de l'entité vivante.
     *
     * @return La quantité de vie actuelle.
     */
    public double getLife() {
        return life;
    }

    /**
     * Définit la quantité de vie de l'entité vivante.
     *
     * @param life La quantité de vie à définir.
     */
    public void setLife(double life) {
        this.life = life;
    }

    /**
     * Inflige des dégâts à l'entité vivante en réduisant sa quantité de vie.
     *
     * @param d Les dégâts à infliger.
     */
    public void damage(double d) {
        life -= d;
    }

    /**
     * Soigne l'entité vivante en augmentant sa quantité de vie.
     *
     * @param d La quantité de vie à ajouter.
     */
    public void heal(double d) {
        setLife(Math.min(life + d, maxLife));
    }

    /**
     * Renvoie l'image associée à l'entité vivante.
     *
     * @return L'image de l'entité vivante.
     */
    @Override
    public Image getImage() {
        return image;
    }

    /**
     * Met à jour l'état de l'entité vivante à chaque itération du jeu.
     *
     * @param environment L'environnement du jeu.
     */
    @Override
    public void tick(Environment environment) {
        if (life < 0.001) {
        	if ((this instanceof Monster)) environment.getPlayer().addScore(100);
        	if ((this instanceof MonsterUpgraded)) environment.getPlayer().addScore(1000);
            destroy(environment);
        }
        vy -= 0.02;
        vx *= 0.7;
        super.tick(environment);
    }
}
