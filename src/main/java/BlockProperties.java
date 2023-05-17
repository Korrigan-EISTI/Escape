package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * La classe BlockProperties repr�sente les propri�t�s des blocs du jeu.
 * Elle charge les propri�t�s des blocs � partir d'un fichier CSV et fournit des m�thodes pour y acc�der.
 */
public class BlockProperties {
    /**
     * Le record BlockProperty repr�sente les propri�t�s d'un bloc.
     * Il contient le chemin d'acc�s � l'image du bloc, une valeur indiquant si le bloc est solide et un indicateur de grimpabilit�.
     */
    public record BlockProperty(String imagePath, boolean solid, boolean climbable) {}
    private final ArrayList<BlockProperty> properties;

    /**
     * Constructeur de la classe BlockProperties.
     * Charge les propri�t�s des blocs � partir d'un fichier CSV.
     */
    public BlockProperties() {
        properties = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/blocks.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                properties.add(new BlockProperty(values[0], Objects.equals(values[1], "1"), Objects.equals(values[2], "1")));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retourne les propri�t�s du bloc � l'indice sp�cifi�.
     *
     * @param i L'indice du bloc.
     * @return Les propri�t�s du bloc.
     */
    public BlockProperty get(short i) {
        return properties.get(i);
    }
    public void set(short i, BlockProperty property){
        properties.set(i,property);
    }
    /**
     * Retourne le nombre de propri�t�s de blocs disponibles.
     *
     * @return Le nombre de propri�t�s de blocs.
     */
    public int size(){

    
        return properties.size();
    }
}
