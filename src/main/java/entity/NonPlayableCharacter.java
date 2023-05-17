package main.java.entity;

import main.java.Environment;

/**
 * Classe représentant un personnage non jouable (PNJ).
 * Un PNJ est une entité vivante qui interagit avec le joueur et peut déclencher des événements dans le jeu.
 */
public class NonPlayableCharacter extends LivingEntity {
	
	private int cooldown;

	/**
	 * Constructeur de la classe NonPlayableCharacter.
	 *
	 * @param x La coordonnée en abscisse du PNJ.
	 * @param y La coordonnée en ordonnée du PNJ.
	 */	
	public NonPlayableCharacter(double x, double y) {
		super(x,y, 100000, 1);
		cooldown = 0;
	}
	
	/**
     * Met à jour l'état du PNJ à chaque itération du jeu.
     *
     * @param e L'environnement du jeu.
     */
	public void tick (Environment e) {
		super.tick(e);
		for (int i = 0;i<e.getEntityCount();i++){
			if(e.getEntity(i) instanceof Player){
				Player player = (Player)e.getEntity(i);
				if(x - 5<=player.getX() && player.getX()<= x + 1 && player.getY() >= y && player.getY()<= y + 1){
					if (e.getGameProgression() == Environment.Progress.START) {
						e.setGameProgression(Environment.Progress.WELCOME);
						cooldown = 300;
					}
					if (e.getGameProgression() == Environment.Progress.BOW) {
						player.setAllowToShoot(true);
					}
					if (e.getGameProgression() == Environment.Progress.POTATO) {
						e.setGameProgression(Environment.Progress.KING);
						cooldown = 200;
					}
					cooldown--;
				}
			}
		}
	}

    /**
     * Renvoie le temps de recharge du PNJ.
     *
     * @return Le temps de recharge du PNJ.
     */
    public int getCooldown() {
        return cooldown;
    }

    /**
     * Définit le temps de recharge du PNJ.
     *
     * @param cooldown Le temps de recharge du PNJ.
     */
    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

}
