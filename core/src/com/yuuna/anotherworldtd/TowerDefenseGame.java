package com.yuuna.anotherworldtd;

import com.badlogic.gdx.Game;
import com.yuuna.anotherworldtd.Screens.Isekai;

public class TowerDefenseGame extends Game{
    //game state variables
    public static boolean paused = false;
    public static boolean placing = true;
    
    @Override
    public void create() {
        setScreen(new Isekai(this));
    }
}
