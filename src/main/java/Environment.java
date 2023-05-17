package main.java;

import main.java.entity.Entity;
import main.java.item.BowUpgraded;
import main.java.item.HealPotion;
import main.java.item.Key;
import main.java.item.WallPotion;
import main.java.entity.Monster;
import main.java.entity.MonsterUpgraded;
import main.java.entity.Player;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * La classe Environment représente l'environnement du jeu, contenant la carte, les entités et la progression du jeu.
 */
public class Environment {
	
	public enum Progress{
        START,
        WELCOME,
        BOW,
        KEY,
        POTATO,
        KING,
        WIN,
        END
    }
	
    public static BlockProperties BLOCK_PROPERTIES = new BlockProperties();
    private final short[][] blocks;
    private final int map_width, map_height;
    private ArrayList<Entity> entities;
    private ArrayList<Entity> addedEntities;
    private Player player;
    private Progress gameProgression;
    
    /**
     * Constructeur de la classe Environment.
     * Charge la carte à partir du fichier XML et initialise les paramètres du jeu.
     */
    public Environment(){
    	
    	gameProgression = Progress.START;
        entities = new ArrayList<>();
        addedEntities = new ArrayList<>();
        Document doc = null;
        
        try {
            doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File("src/main/resources/maps/map.tmx"));
        }
        catch (ParserConfigurationException | SAXException | IOException ignored) {
        }
        
        Node info = doc.getElementsByTagName("map").item(0);
        map_width = Short.parseShort(info.getAttributes().getNamedItem("width").getTextContent());
        map_height = Short.parseShort(info.getAttributes().getNamedItem("height").getTextContent());
        blocks = new short[map_height][map_width];
        String data = doc.getElementsByTagName("data").item(0).getTextContent().trim();
        
        int y = map_height;
        for (String row : data.split("\n")){
            y--;
            int x = 0;
            for (String block : row.split(",")){
                blocks[y][x] = Short.parseShort(block);
                x++;
            }
        }
    }
    
    /**
     * Récupère le type de bloc à la position spécifiée.
     *
     * @param x La position horizontale du bloc.
     * @param y La position verticale du bloc.
     * @return Le type de bloc à la position spécifiée.
     */
    public short getBlock(int x, int y) {
    	
        if (x < 0 || y < 0 || x >= map_width || y >= map_height){
            return 0;
        }
        return blocks[y][x];
    }
    
    /**
     * Récupère le type de bloc à la position spécifiée en virgule flottante.
     *
     * @param x La position horizontale du bloc.
     * @param y La position verticale du bloc.
     * @return Le type de bloc à la position spécifiée.
     */
    public short getBlock(double x, double y) {
        return getBlock((int) x, (int) y);
    }
    
    /**
     * Modifie le type de bloc à la position spécifiée.
     *
     * @param x   La position horizontale du bloc.
     * @param y   La position verticale du bloc.
     * @param val Le nouveau type de bloc.
     */
    public void setBlock(int x, int y, short val) {
        this.blocks[y][x] = val;
    }

    /**
     * Retourne la largeur de la carte.
     *
     * @return La largeur de la carte.
     */
    public int getWidth() {
        return map_width;
    }

    /**
     * Retourne la hauteur de la carte.
     *
     * @return La hauteur de la carte.
     */
    public int getHeight() {
        return map_height;
    }
    
    /**
     * Ajoute une entité à l'environnement.
     *
     * @param entity L'entité à ajouter.
     */
    public void addEntity(Entity entity){
        addedEntities.add(entity);
    }
    
    /**
     * Génère des monstres dans l'environnement.
     * Cette méthode est actuellement commentée, mais elle permettrait de générer des monstres à des positions spécifiques.
     */
    public void generateMonsters(){
    	addedEntities.add(new Monster(39,51));
    	addedEntities.add(new Monster(56,46));
    	addedEntities.add(new Monster(75,53));
    	addedEntities.add(new Monster(74,58));
    	addedEntities.add(new Monster(82,64));
    	addedEntities.add(new Monster(67,64));
    	addedEntities.add(new Monster(69,81));
    	addedEntities.add(new Monster(75,70));
    	addedEntities.add(new Monster(81,72));
    	addedEntities.add(new MonsterUpgraded(80,76));
    	addedEntities.add(new Monster(86,88));
    	addedEntities.add(new Monster(72,82));
    	addedEntities.add(new Monster(78,82));
    	addedEntities.add(new Monster(75,84));
    	addedEntities.add(new Monster(115,33));
    }
    
    /**
     * Génère des objets dans l'environnement.
     * Cette méthode ajoute des objets (clés, potions, etc.) à des positions spécifiques.
     */
    public void generateItems() {
    	addedEntities.add(new Key(168, 63));
    	addedEntities.add(new WallPotion(111, 33));
    	addedEntities.add(new HealPotion(190, 53));
    	addedEntities.add(new BowUpgraded(188, 53));
    }
    
    /**
     * Retourne le joueur actuel.
     *
     * @return Le joueur actuel.
     */
    public Player getPlayer() {
		return player;
	}

    /**
     * Définit le joueur actuel.
     *
     * @param player Le joueur à définir.
     */
	public void setPlayer(Player player) {
		this.player = player;
		addEntity(player);
	}

	/**
     * Met à jour les entités dans l'environnement.
     */
	public void tickEntities(){
    	
        entities.addAll(addedEntities);
        addedEntities.clear();
        
        for (Entity entity : entities) {
            entity.tick(this);
        }
        entities.removeIf(Entity::destroyed);
    }
    
    /**
     * Retourne le nombre total d'entités dans l'environnement.
     *
     * @return Le nombre total d'entités.
     */
    public int getEntityCount(){
        return entities.size();
    }
    
    /**
     * Récupère une entité à l'index spécifié.
     *
     * @param i L'index de l'entité.
     * @return L'entité à l'index spécifié.
     */
    public Entity getEntity(int i){
        return entities.get(i);
    }

    /**
     * Retourne la progression du jeu.
     *
     * @return La progression du jeu.
     */
	public Progress getGameProgression() {
		return gameProgression;
	}

    /**
     * Définit la progression du jeu.
     *
     * @param gameProgression La progression du jeu à définir.
     */
	public void setGameProgression(Progress gameProgression) {
		this.gameProgression = gameProgression;
	}
    
}
