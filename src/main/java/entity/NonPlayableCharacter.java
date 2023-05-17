package main.java.entity;

import main.java.Environment;

/**
 * Classe repr�sentant un personnage non jouable (PNJ).
 * Un PNJ est une entit� vivante qui interagit avec le joueur et peut d�clencher des �v�nements dans le jeu.
 */
public class NonPlayableCharacter extends LivingEntity {
	
	private int cooldown;

	/**
	 * Constructeur de la classe NonPlayableCharacter.
	 *
	 * @param x La coordonn�e en abscisse du PNJ.
	 * @param y La coordonn�e en ordonn�e du PNJ.
	 */	
	public NonPlayableCharacter(double x, double y) {
		super(x,y, 100000, 1);
		cooldown = 0;
	}
	
	/**
     * Met � jour l'�tat du PNJ � chaque it�ration du jeu.
     *
     * @param e L'environnement du jeu.
     */
	public void tick (Environment e) {
		super.tick(e);
		for (int i = 0;i<e.getEntityCount();i++){
			if(e.getEntity(i) instanceof Player){
				Player player = (Player)e.getEntity(i);
				if(x - 5<=player.getX() && player.getX()<= x + 1 && player.getY() >= y && player.getY()<= y + 1){
					if (e.getGameProgression() == 0) {
						e.setGameProgression(1);
						cooldown = 100;
					}
					if (e.getGameProgression() == 2) {
						player.setAllowToShoot(true);
					}
					if (e.getGameProgression() == 5) {
						e.setGameProgression(6);
						cooldown = 100;
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
     * D�finit le temps de recharge du PNJ.
     *
     * @param cooldown Le temps de recharge du PNJ.
     */
    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

}
