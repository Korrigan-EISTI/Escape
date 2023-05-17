package main.java.entity.projectile;

import javafx.scene.image.Image;
import main.java.Environment;
import main.java.entity.LivingEntity;
import main.java.entity.MonsterUpgraded;
import main.java.entity.NonPlayableCharacter;

/**
 * Cette classe représente une flèche améliorée.
 */
public class ArrowUpgraded extends Projectile {

	private static final Image imageLeft = new Image("file:src/main/resources/arrows/arrow_left_upgraded.png");
	private static final Image imageRight = new Image("file:src/main/resources/arrows/arrow_right_upgraded.png");


    /**
     * Constructeur de la classe ArrowUpgraded.
     *
     * @param x      La coordonnée X de départ de la flèche améliorée.
     * @param y      La coordonnée Y de départ de la flèche améliorée.
     * @param vx     La vitesse horizontale de la flèche améliorée.
     * @param owner  L'entité parente de la flèche améliorée.
     */
    public ArrowUpgraded(double x, double y, double vx, LivingEntity owner) {
        super(x - 0.5, y, vx, 0, 1, 0.2, owner);
    }

    /**
     * Renvoie l'entité qui a tiré la flèche améliorée.
     *
     * @return L'entité propriétaire de la flèche améliorée.
     */
    public LivingEntity shotFrom() {
        return owner;
    }

    /**
     * Met à jour l'état de la flèche améliorée à chaque tick.
     *
     * @param environment L'environnement dans lequel évolue la flèche améliorée.
     */
    public void tick(Environment environment) {
        super.tick(environment);
    }

    /**
     * Méthode invoquée lorsqu'une entité est touchée par la flèche améliorée.
     *
     * @param livingEntity L'entité touchée par la flèche améliorée.
     * @return Vrai si l'entité a été touchée et endommagée, faux sinon.
     */
    @Override
    protected boolean onHit(LivingEntity livingEntity) {
        if (!(livingEntity instanceof NonPlayableCharacter) || !(livingEntity instanceof MonsterUpgraded)) {
            livingEntity.damage(2);
            destroy();
            return true;
        }
        return false;
    }

    /**
     * Renvoie l'image correspondante à la flèche améliorée en fonction de sa direction.
     *
     * @return L'image de la flèche améliorée.
     */
    public Image getImage() {
        return vx > 0 ? imageRight : imageLeft;
    }
}
