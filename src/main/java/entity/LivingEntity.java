package main.java.entity;

import javafx.scene.image.Image;
import main.java.Environment;

/**
 * Classe repr�sentant une entit� vivante.
 * Une entit� vivante est une entit� physique avec une quantit� de vie.
 */
public class LivingEntity extends PhysicalEntity {

	private static final Image image = new Image("file:src/main/resources/living_entities/pnj.png");

    double life;
    double maxLife;

	/**
	 * Constructeur de la classe LivingEntity.
	 *
	 * @param x     La coordonn�e en abscisse de l'entit� vivante.
	 * @param y     La coordonn�e en ordonn�e de l'entit� vivante.
	 * @param life  La quantit� de vie de l'entit� vivante.
	 */
    public LivingEntity(double x, double y, double life, int size_multiplicator) {
        super(x, y, 0, 0, (double) size_multiplicator*20/24, (double) size_multiplicator*20/24);
        this.life = life;
        maxLife = life;
    }

    /**
     * Renvoie la quantit� maximale de vie de l'entit� vivante.
     *
     * @return La quantit� maximale de vie.
     */
    public double getMaxLife() {
        return maxLife;
    }

    /**
     * D�finit la quantit� maximale de vie de l'entit� vivante.
     *
     * @param maxLife La quantit� maximale de vie � d�finir.
     */
    public void setMaxLife(double maxLife) {
        this.maxLife = maxLife;
    }

    /**
     * Renvoie la quantit� de vie actuelle de l'entit� vivante.
     *
     * @return La quantit� de vie actuelle.
     */
    public double getLife() {
        return life;
    }

    /**
     * D�finit la quantit� de vie de l'entit� vivante.
     *
     * @param life La quantit� de vie � d�finir.
     */
    public void setLife(double life) {
        this.life = life;
    }

    /**
     * Inflige des d�g�ts � l'entit� vivante en r�duisant sa quantit� de vie.
     *
     * @param d Les d�g�ts � infliger.
     */
    public void damage(double d) {
        life -= d;
    }

    /**
     * Soigne l'entit� vivante en augmentant sa quantit� de vie.
     *
     * @param d La quantit� de vie � ajouter.
     */
    public void heal(double d) {
        setLife(Math.min(life + d, maxLife));
    }

    /**
     * Renvoie l'image associ�e � l'entit� vivante.
     *
     * @return L'image de l'entit� vivante.
     */
    @Override
    public Image getImage() {
        return image;
    }

    /**
     * Met � jour l'�tat de l'entit� vivante � chaque it�ration du jeu.
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
