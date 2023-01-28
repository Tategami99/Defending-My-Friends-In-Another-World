package com.yuuna.anotherworldtd.Tools;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.yuuna.anotherworldtd.TowerDefenseGame;
import com.yuuna.anotherworldtd.Allies.MageAlly;
import com.yuuna.anotherworldtd.Tools.AssetManager.AllyAssets.MageAllyAssets;

public class EntityManager {
    //game variables
    private TiledMapTileLayer gameLayer;
    private TowerDefenseGame game;
    private Stage stage;

    //allies
    public MageAlly mageAlly;
    public static enum AllySelection{
        mageAlly,
        katanaAlly
    }
    public AllySelection selectedAlly;

    public EntityManager(TiledMapTileLayer gameLayer, TowerDefenseGame game, Stage stage){
        //load ally stuff
        MageAllyAssets.mageAllyLoad();

        //variables received from game
        this.gameLayer = gameLayer;
        this.game = game;
        this.stage = stage;
    }

    public void render(SpriteBatch batch, float deltaTime){
        if(mageAlly != null && !MageAlly.resting && !MageAlly.immobile){
            mageAlly.render(batch, deltaTime);
        }
    }

    public void createAlly(AllySelection ally, float xPos, float yPos){
        switch(ally){
            case mageAlly:
                if(MageAlly.resting && !MageAlly.immobile){
                    mageAlly = new MageAlly(xPos*gameLayer.getTileWidth(), yPos*gameLayer.getTileHeight());
                    MageAlly.resting = false;
                }
                break;
        }
    }
}
