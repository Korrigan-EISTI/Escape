package main.java.entity.projectile;

import javafx.scene.image.Image;
import main.java.Environment;
import main.java.entity.LivingEntity;
import main.java.entity.MonsterUpgraded;
import main.java.entity.NonPlayableCharacter;

/**
 * Cette classe repr�sente une fl�che am�lior�e.
 */
public class ArrowUpgraded extends Projectile {

	private static final Image imageLeft = new Image("file:src/main/resources/arrows/arrow_left_upgraded.png");
	private static final Image imageRight = new Image("file:src/main/resources/arrows/arrow_right_upgraded.png");


    /**
     * Constructeur de la classe ArrowUpgraded.
     *
     * @param x      La coordonn�e X de d�part de la fl�che am�lior�e.
     * @param y      La coordonn�e Y de d�part de la fl�che am�lior�e.
     * @param vx     La vitesse horizontale de la fl�che am�lior�e.
     * @param owner  L'entit� parente de la fl�che am�lior�e.
     */
    public ArrowUpgraded(double x, double y, double vx, LivingEntity owner) {
        super(x - 0.5, y, vx, 0, 1, 0.2, owner);
    }

    /**
     * Renvoie l'entit� qui a tir� la fl�che am�lior�e.
     *
     * @return L'entit� propri�taire de la fl�che am�lior�e.
     */
    public LivingEntity shotFrom() {
        return owner;
    }

    /**
     * Met � jour l'�tat de la fl�che am�lior�e � chaque tick.
     *
     * @param environment L'environnement dans lequel �volue la fl�che am�lior�e.
     */
    public void tick(Environment environment) {
        super.tick(environment);
    }

    /**
     * M�thode invoqu�e lorsqu'une entit� est touch�e par la fl�che am�lior�e.
     *
     * @param livingEntity L'entit� touch�e par la fl�che am�lior�e.
     * @return Vrai si l'entit� a �t� touch�e et endommag�e, faux sinon.
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
     * Renvoie l'image correspondante � la fl�che am�lior�e en fonction de sa direction.
     *
     * @return L'image de la fl�che am�lior�e.
     */
    public Image getImage() {
        return vx > 0 ? imageRight : imageLeft;
    }
}
