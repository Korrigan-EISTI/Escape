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
 * La classe Environment repr�sente l'environnement du jeu, contenant la carte, les entit�s et la progression du jeu.
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
     * Charge la carte � partir du fichier XML et initialise les param�tres du jeu.
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
     * R�cup�re le type de bloc � la position sp�cifi�e.
     *
     * @param x La position horizontale du bloc.
     * @param y La position verticale du bloc.
     * @return Le type de bloc � la position sp�cifi�e.
     */
    public short getBlock(int x, int y) {
    	
        if (x < 0 || y < 0 || x >= map_width || y >= map_height){
            return 0;
        }
        return blocks[y][x];
    }
    
    /**
     * R�cup�re le type de bloc � la position sp�cifi�e en virgule flottante.
     *
     * @param x La position horizontale du bloc.
     * @param y La position verticale du bloc.
     * @return Le type de bloc � la position sp�cifi�e.
     */
    public short getBlock(double x, double y) {
        return getBlock((int) x, (int) y);
    }
    
    /**
     * Modifie le type de bloc � la position sp�cifi�e.
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
     * Ajoute une entit� � l'environnement.
     *
     * @param entity L'entit� � ajouter.
     */
    public void addEntity(Entity entity){
        addedEntities.add(entity);
    }
    
    /**
     * G�n�re des monstres dans l'environnement.
     * Cette m�thode est actuellement comment�e, mais elle permettrait de g�n�rer des monstres � des positions sp�cifiques.
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
     * G�n�re des objets dans l'environnement.
     * Cette m�thode ajoute des objets (cl�s, potions, etc.) � des positions sp�cifiques.
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
     * D�finit le joueur actuel.
     *
     * @param player Le joueur � d�finir.
     */
	public void setPlayer(Player player) {
		this.player = player;
		addEntity(player);
	}

	/**
     * Met � jour les entit�s dans l'environnement.
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
     * Retourne le nombre total d'entit�s dans l'environnement.
     *
     * @return Le nombre total d'entit�s.
     */
    public int getEntityCount(){
        return entities.size();
    }
    
    /**
     * R�cup�re une entit� � l'index sp�cifi�.
     *
     * @param i L'index de l'entit�.
     * @return L'entit� � l'index sp�cifi�.
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
     * D�finit la progression du jeu.
     *
     * @param gameProgression La progression du jeu � d�finir.
     */
	public void setGameProgression(Progress gameProgression) {
		this.gameProgression = gameProgression;
	}
    
}
