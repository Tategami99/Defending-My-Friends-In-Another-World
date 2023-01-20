package com.yuuna.anotherworldtd;

import com.badlogic.gdx.Game;
import com.yuuna.anotherworldtd.Screens.Isekai;

public class TowerDefenseGame extends Game{
    @Override
    public void create() {
        setScreen(new Isekai(this));
    }
}
