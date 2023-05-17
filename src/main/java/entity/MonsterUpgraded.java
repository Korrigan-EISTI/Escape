package main.java.entity;

import javafx.scene.image.Image;
import main.java.Environment;
import main.java.entity.projectile.ArrowUpgraded;

public class MonsterUpgraded extends LivingEntity {


	/**
	 * L'image du monstre am�lior�.
	 */
	private static final Image image = new Image("file:src/main/resources/living_entities/monster_upgraded.png");

    /**
     * Initialise un nouvel objet MonsterUpgraded avec les coordonn�es sp�cifi�es.
     *
     * @param x La position horizontale du monstre.
     * @param y La position verticale du monstre.
	 */
	public MonsterUpgraded(double x, double y) {
		super(x, y, 12, 2);
	}

    /**
     * Met � jour l'�tat du monstre � chaque cycle de jeu.
     *
     * @param environment L'environnement de jeu.
     */
    @Override
    public void tick(Environment environment){
        super.tick(environment);

        if(getLast_shot()<=0){
            setLast_shot(90);
            environment.addEntity(new ArrowUpgraded(x-w/2,y+2*h/3,-0.5,this));
            environment.addEntity(new ArrowUpgraded(x-w/2,y+h/3,-0.5,this));
        }
    }

    /**
     * Retourne l'image du monstre am�lior�.
     *
     * @return L'image du monstre am�lior�.
     */
    @Override
    public Image getImage(){
        return image;
    }
}
