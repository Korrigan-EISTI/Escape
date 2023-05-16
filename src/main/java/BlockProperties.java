package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class BlockProperties {
	
    public record BlockProperty(String imagePath,short solid,boolean climbable){};
    private final ArrayList<BlockProperty> properties;
    
    public BlockProperties(){
        properties = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/blocks.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                properties.add(new BlockProperty(values[0], Short.valueOf(values[1]), Objects.equals(values[2], "1")));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public BlockProperty get(short i){
        return properties.get(i);
    }
    
    public int size(){
        return properties.size();
    }
}
