package main.java.entity.projectile;

import main.java.Environment;
import main.java.entity.LivingEntity;
import main.java.entity.PhysicalEntity;

/**
 * Cette classe repr�sente un projectile g�n�rique.
 */
public class Projectile extends PhysicalEntity {

    protected LivingEntity owner;

    /**
     * Constructeur de la classe Projectile.
     *
     * @param x      La coordonn�e X de d�part du projectile.
     * @param y      La coordonn�e Y de d�part du projectile.
     * @param vx     La vitesse horizontale du projectile.
     * @param vy     La vitesse verticale du projectile.
     * @param w      La largeur du projectile.
     * @param h      La hauteur du projectile.
     * @param owner  L'entit� parente du projectile.
     */
    public Projectile(double x, double y, double vx, double vy, double w, double h, LivingEntity owner) {
        super(x, y, vx, vy, w, h);
        this.owner = owner;
    }

    /**
     * Met � jour l'�tat du projectile � chaque tick.
     *
     * @param environment L'environnement dans lequel �volue le projectile.
     */
    @Override
    public void tick(Environment environment) {
        super.tick(environment);
        for (int i = 0; i < environment.getEntityCount(); i++) {
            if (environment.getEntity(i) instanceof LivingEntity livingEntity) {
                if (!owner.getClass().equals(livingEntity.getClass())) {
                    if (x < livingEntity.getX() + livingEntity.getWidth() && x + w > livingEntity.getX() && y < livingEntity.getY() + livingEntity.getHeight() && y + h > livingEntity.getY()) {
                        if (onHit(livingEntity)) {
                            destroy();
                            break;
                        }
                    }
                }
            }
        }
        if (vx == 0) {
            destroy();
        }
    }

    /**
     * M�thode invoqu�e lorsqu'une entit� est touch�e par le projectile.
     *
     * @param livingEntity L'entit� touch�e par le projectile.
     * @return Vrai si l'entit� a �t� touch�e par le projectile, faux sinon.
     */
    protected boolean onHit(LivingEntity livingEntity) {
        return false;
    }
}
