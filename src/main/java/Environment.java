package main.java;

import main.java.entity.Entity;
import main.java.item.HealPotion;
import main.java.item.Key;
import main.java.item.WallPotion;
import main.java.entity.Monster;
import main.java.entity.Player;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Environment {
	
    public static BlockProperties BLOCK_PROPERTIES = new BlockProperties();
    private final short[][] blocks;
    private final int map_width, map_height;
    private ArrayList<Entity> entities;
    private ArrayList<Entity> addedEntities;
    private Player player;
    private int gameProgression;
    
    public Environment(){
    	gameProgression = 0;
        entities = new ArrayList<>();
        addedEntities = new ArrayList<>();
        Document doc = null;
        
        try {
            doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File("src/main/resources/maps/map.tmx"));
        }
        catch (ParserConfigurationException | SAXException | IOException ignored) {
        }
        
        Node info = doc.getElementsByTagName("map").item(0);
        map_width=Short.parseShort(info.getAttributes().getNamedItem("width").getTextContent());
        map_height=Short.parseShort(info.getAttributes().getNamedItem("height").getTextContent());
        blocks= new short[map_height][map_width];
        String data = doc.getElementsByTagName("data").item(0).getTextContent().trim();
        
        int y=map_height;
        for (String row : data.split("\n")){
            y--;
            int x=0;
            for (String block:row.split(",")){
                blocks[y][x]=Short.parseShort(block);
                x++;
            }
        }
    }
    
    
    public short getBlock(int x,int y) {
    	
        if(x<0 || y<0 || x>=map_width || y>=map_height){
            return 0;
        }
        return blocks[y][x];
    }
    
    public short getBlock(double x,double y) {
        return getBlock((int)x,(int)y);
    }
    
    public void setBlock(int x,int y,short val) {
        this.blocks[y][x] = val;
    }

    public int getWidth() {
        return map_width;
    }

    public int getHeight() {
        return map_height;
    }
    
    public void addEntity(Entity entity){
        addedEntities.add(entity);
    }
    
    public void generateMonsters(){
    	/*addedEntities.add(new Monster(39,51));
    	addedEntities.add(new Monster(56,46));
    	addedEntities.add(new Monster(75,53));
    	addedEntities.add(new Monster(74,58));
    	addedEntities.add(new Monster(82,64));
    	addedEntities.add(new Monster(67,64));
    	addedEntities.add(new Monster(69,81));
    	addedEntities.add(new Monster(75,70));
    	addedEntities.add(new Monster(81,72));
    	addedEntities.add(new Monster(81,76));
    	addedEntities.add(new Monster(86,88));
    	addedEntities.add(new Monster(72,82));
    	addedEntities.add(new Monster(78,82));
    	addedEntities.add(new Monster(75,84));
    	addedEntities.add(new Monster(115,33));*/
    }
    
    public void generateItems() {
    	addedEntities.add(new Key(168, 63));
    	addedEntities.add(new WallPotion(111, 33));
    	addedEntities.add(new HealPotion(190, 53));
    }
    
    public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
		addEntity(player);
	}


	public void tickEntities(){
    	
        entities.addAll(addedEntities);
        addedEntities.clear();
        
        for (Entity entity : entities) {
            entity.tick(this);
        }
        entities.removeIf(Entity::destroyed);
    }
    
    public int getEntityCount(){
        return entities.size();
    }
    
    public Entity getEntity(int i){
        return entities.get(i);
    }


	public int getGameProgression() {
		return gameProgression;
	}


	public void setGameProgression(int gameProgression) {
		this.gameProgression = gameProgression;
	}
    
}
