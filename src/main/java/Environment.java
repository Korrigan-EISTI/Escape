package main.java;

import main.java.entity.Entity;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Environment {
    public static BlockProperties BLOCK_PROPERTIES=new BlockProperties();
    private final short[][] blocks;
    private final int w,h;
    private ArrayList<Entity> entities;
    private ArrayList<Entity> addedEntities;
    public Environment(){
        entities = new ArrayList<>();
        addedEntities = new ArrayList<>();
        Document doc = null;
        try {
            doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File("src/main/resources/maps/map.tmx"));
        }
        catch (ParserConfigurationException | SAXException | IOException ignored) {
        }
        Node info = doc.getElementsByTagName("map").item(0);
        w=Short.parseShort(info.getAttributes().getNamedItem("width").getTextContent());
        h=Short.parseShort(info.getAttributes().getNamedItem("height").getTextContent());
        blocks= new short[h][w];
        String data = doc.getElementsByTagName("data").item(0).getTextContent().trim();
        int y=h;
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
        if(x<0 || y<0 || x>=w || y>=h){
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
        return w;
    }

    public int getHeight() {
        return h;
    }
    public void addEntity(Entity entity){
        addedEntities.add(entity);
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
}
