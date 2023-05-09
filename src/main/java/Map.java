package main.java;

import javafx.scene.image.Image;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Map {
    public static BlockProperties BLOCK_PROPERTIES=new BlockProperties();
    private short[][] blocks;
    private final int w,h;
    public Map(){
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
}
