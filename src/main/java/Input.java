package main.java;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Input {
    private boolean[] keyBoardState;

    public Input(Scene scene){
        keyBoardState=new boolean[256];
        scene.setOnKeyPressed((KeyEvent event) -> {
            keyBoardState[event.getCode().ordinal()] = true;
        });
        scene.setOnKeyReleased((KeyEvent event) -> {
            keyBoardState[event.getCode().ordinal()] = false;
        });
    }
    public boolean keyPressed(KeyCode key){
        return keyBoardState[key.ordinal()];
    }
}
