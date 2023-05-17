package main.java;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * La classe Input g�re les entr�es clavier dans une application JavaFX.
 */
public class Input {

    private final boolean[] keyBoardState;

    /**
     * Initialise une nouvelle instance de la classe Input.
     *
     * @param scene la sc�ne JavaFX � surveiller pour les �v�nements clavier
     */
    public Input(Scene scene) {
        keyBoardState = new boolean[KeyCode.values().length];
        
        // Gestion des �v�nements de touche enfonc�e
        scene.setOnKeyPressed((KeyEvent event) -> {
            keyBoardState[event.getCode().ordinal()] = true;
        });
        
        // Gestion des �v�nements de touche rel�ch�e
        scene.setOnKeyReleased((KeyEvent event) -> {
            keyBoardState[event.getCode().ordinal()] = false;
        });
    }

    /**
     * V�rifie si une touche est enfonc�e.
     *
     * @param key la touche � v�rifier
     * @return true si la touche est enfonc�e, false sinon
     */
    public boolean keyPressed(KeyCode key) {
        return keyBoardState[key.ordinal()];
    }
}
