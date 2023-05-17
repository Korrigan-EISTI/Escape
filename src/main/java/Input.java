package main.java;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * La classe Input gére les entrées clavier dans une application JavaFX.
 */
public class Input {

    private final boolean[] keyBoardState;

    /**
     * Initialise une nouvelle instance de la classe Input.
     *
     * @param scene la scène JavaFX à surveiller pour les événements clavier
     */
    public Input(Scene scene) {
        keyBoardState = new boolean[KeyCode.values().length];
        
        // Gestion des événements de touche enfoncée
        scene.setOnKeyPressed((KeyEvent event) -> {
            keyBoardState[event.getCode().ordinal()] = true;
        });
        
        // Gestion des événements de touche relâchée
        scene.setOnKeyReleased((KeyEvent event) -> {
            keyBoardState[event.getCode().ordinal()] = false;
        });
    }

    /**
     * Vérifie si une touche est enfoncée.
     *
     * @param key la touche à vérifier
     * @return true si la touche est enfoncée, false sinon
     */
    public boolean keyPressed(KeyCode key) {
        return keyBoardState[key.ordinal()];
    }
}
