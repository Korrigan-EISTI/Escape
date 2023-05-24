package main.java.entity;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import main.java.BlockProperties;
import main.java.Environment;
import main.java.Input;
import main.java.entity.projectile.Arrow;
import main.java.entity.projectile.ArrowUpgraded;

public class Player extends LivingEntity {

	/**
	 * L'image du joueur.
	 */
	private Image image = new Image("file:src/main/resources/living_entities/player_right.png");
	
	/**
	 * L'image du joueur vers la gauche et la droite.
	 */
    private static Image image_left = new Image("file:src/main/resources/living_entities/player_left.png");
    private static Image image_right = new Image("file:src/main/resources/living_entities/player_right.png");
    
    /**
     * Indique si le joueur est en train de grimper.
     */
    protected boolean climbing;

    /**
     * Indique si le joueur est autoris� � tirer.
     */
    protected boolean allowToShoot;

    /**
     * Indique si le joueur poss�de la cl�.
     */
    private boolean hasKey;

    /**
     * Indique si le joueur poss�de la potion murale.
     */
    private boolean hasWallPotion;

    /**
     * Indique si le joueur peut traverser les murs magiques.
     */
    private boolean canWalkThroughMagicWalls;
    
    /**
     * Indique si l'arc du joueur est am�lior�.
     */
    private boolean bowIsUpgraded;
    
    /**
     * Le score du joueur.
     */
    private double score;

    /**
     * Initialise un nouvel objet Player avec les coordonn�es sp�cifi�es.
     *
     * @param x La position horizontale du joueur.
     * @param y La position verticale du joueur.
     */
    public Player(double x, double y) {
        super(x, y, 10, 1);
        this.setLast_shot(30);
        this.setAllowToShoot(false);
        this.setHasKey(false);
        this.setHasWallPotion(false);
		this.setBowIsUpgraded(false);
		this.setCanWalkThroughMagicWalls(false);
		this.setScore(0);
    }

    /**
     * Met � jour l'�tat du joueur � chaque cycle de jeu.
     *
     * @param environment L'environnement de jeu.
     */
    @Override
    public void tick(Environment environment) {
    	
        super.tick(environment);
        climbing=false;
        
        for (short block : touched){
            climbing=climbing || Environment.BLOCK_PROPERTIES.get(block).climbable();
            if(block==15 && hasKey){
                environment.setGameProgression(Environment.Progress.POTATO);
            }
            if(block==16){
                environment.getPlayer().setLife(0);
            }
        }
        if (climbing) {
            vx = 0;
            vy = 0;
        }
    }

    /**
     * G�re les entr�es du joueur pour effectuer des actions.
     *
     * @param input       Les entr�es du joueur.
     * @param environment L'environnement de jeu.
     */
    public void handleInput(Input input, Environment environment) {

        if (input.keyPressed(KeyCode.RIGHT)) {
            vx += 0.07;
            this.image = Player.image_right;
        }
        if (input.keyPressed(KeyCode.LEFT)) {
            vx -= 0.07;
            this.image = Player.image_left;
        }
        if (input.keyPressed(KeyCode.UP)) {
            if (on_ground) {
                vy = 0.35;
            } else if (climbing) {
                vy = 0.2;
            }
        }
        if (input.keyPressed(KeyCode.DOWN) && climbing) {
            vy = -0.2;
        }
        if (allowToShoot) {

            if(input.keyPressed(KeyCode.A) && getLast_shot()<=0){
            	if(this.isBowUpgraded()) environment.addEntity(new ArrowUpgraded(x+w/2,y+2*h/3,-0.5,this));
            	else environment.addEntity(new Arrow(x+w/2,y+2*h/3,-0.5,this));
            	setLast_shot(30);
            }
            if(input.keyPressed(KeyCode.E) && getLast_shot()<=0) {
            	if (this.isBowUpgraded()) environment.addEntity(new ArrowUpgraded(x+w/2,y+2*h/3, 0.5,this));
            	else environment.addEntity(new Arrow(x+w/2,y+2*h/3, 0.5,this));
                setLast_shot(30);
            }
        }
        if (input.keyPressed(KeyCode.P)) {
            useWallPotion();
        }
    }

    /**
     * Retourne l'image du joueur.
     *
     * @return L'image du joueur.
     */
    @Override
    public Image getImage() {
        return image;
    }

    /**
     * Indique si le joueur poss�de la cl�.
     *
     * @return `true` si le joueur poss�de la cl�, sinon `false`.
     */
    public boolean hasKey() {
        return hasKey;
    }

    /**
     * D�finit si le joueur poss�de la cl�.
     *
     * @param hasKey `true` si le joueur poss�de la cl�, sinon `false`.
     */
    public void setHasKey(boolean hasKey) {
        this.hasKey = hasKey;
    }

    /**
     * Indique si le joueur poss�de la potion murale.
     *
     * @return `true` si le joueur poss�de la potion murale, sinon `false`.
     */
    public boolean hasWallPotion() {
        return hasWallPotion;
    }

    /**
     * D�finit si le joueur poss�de la potion murale.
     *
     * @param hasWallPotion `true` si le joueur poss�de la potion murale, sinon `false`.
     */
    public void setHasWallPotion(boolean hasWallPotion) {
        this.hasWallPotion = hasWallPotion;
    }

    /**
     * Indique si le joueur peut traverser les murs magiques.
     *
     * @return `true` si le joueur peut traverser les murs magiques, sinon `false`.
     */
    public boolean canWalkThroughMagicWalls() {
        return canWalkThroughMagicWalls;
    }
    
    /**
     * D�finit si le joueur peut traverser les murs.
     *
     * @param condition `true` si le joueur peut traverser les murs, sinon `false`.
     */
    public void setCanWalkThroughMagicWalls(boolean condition) {
    	this.canWalkThroughMagicWalls = condition;
    }
    
	/**
	 * Utilise la potion pour traverser les murs.
	 *
	 */
	public void useWallPotion() {
        canWalkThroughMagicWalls=true;
		if(hasWallPotion) {
			Environment.BLOCK_PROPERTIES.set((short)14,new BlockProperties.BlockProperty("magic_wall.png",false,false));
			this.setHasWallPotion(false);
		}
	}

    /**
     * Indique si le joueur est autoris� � tirer.
     *
     * @return `true` si le joueur est autoris� � tirer, sinon `false`.
     */
    public boolean isAllowToShoot() {
        return allowToShoot;
    }

    /**
     * D�finit si le joueur est autoris� � tirer.
     *
     * @param allowToShoot `true` si le joueur est autoris� � tirer, sinon `false`.
     */
    public void setAllowToShoot(boolean allowToShoot) {
        this.allowToShoot = allowToShoot;
    }

    /**
     * Indique si l'arc du joueur est am�lior�.
     *
     * @return `true` si l'arc du joueur est am�lior�, sinon `false`.
     */
    public boolean isBowUpgraded() {
        return bowIsUpgraded;
    }

    /**
     * D�finit si l'arc du joueur est am�lior�.
     *
     * @param bowIsUpgraded `true` si l'arc du joueur est am�lior�, sinon `false`.
     */
    public void setBowIsUpgraded(boolean bowIsUpgraded) {
        this.bowIsUpgraded = bowIsUpgraded;
    }

    /**
     * Renvoie le score du joueur.
	 *
     */
	public double getScore() {
		return score;
	}

	/**
     * D�finit le score du joueur.
     *
     * @param score Le score � d�finir.
     */
	public void setScore(int score) {
		this.score = score;
	} 
	
	/**
     * Incr�mente le score du joueur.
     *
     * @param d Les points de score � ajouter au score du joueur.
     */
	public void addScore(double d) {
		this.score += d;
	}
}
