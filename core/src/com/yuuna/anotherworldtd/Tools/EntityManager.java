package com.yuuna.anotherworldtd.Tools;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.yuuna.anotherworldtd.TowerDefenseGame;
import com.yuuna.anotherworldtd.Entities.Allies.KatanaAlly;
import com.yuuna.anotherworldtd.Entities.Allies.MageAlly;
import com.yuuna.anotherworldtd.Tools.AssetManager.AllyAssets.KatanaAllyAssets;
import com.yuuna.anotherworldtd.Tools.AssetManager.AllyAssets.MageAllyAssets;
import com.yuuna.anotherworldtd.BaseClasses.Entity;

public class EntityManager {
    //game variables
    private TiledMapTileLayer gameLayer;
    private MapProperties mapProperties;
    private TowerDefenseGame game;
    private Stage stage;
    private int tileWidth, tileHeight;
    private int numOTilesVertical, numOTilesHorizontal;
    private int worldWidth, worldHeight;
    private boolean[][] enemiesOccupying =
    {
        {false, false, false, false, false, false, false, false, false, false, true, false},
        {false, false, false, false, false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false, false, false, false, false}
    }; // [row][column] [0][column] bottom row
    private boolean[][] alliesOccupying =
    {
        {false, false, false, false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false, false, false, false}
    }; // [row][column] [0][column] bottom row


    //allies
    public static enum AllySelection{
        mageAlly,
        katanaAlly
    }
    public AllySelection selectedAlly;
    private ArrayList<Entity> allyList = new ArrayList<>();
    private ArrayList<Entity> alliesToRemove = new ArrayList<>();


    public EntityManager(TiledMapTileLayer gameLayer, MapProperties mapProperties, TowerDefenseGame game, Stage stage){
        //load ally stuff
        MageAllyAssets.mageAllyLoad();
        KatanaAllyAssets.katanaAllyLoad();

        //variables received from game
        this.gameLayer = gameLayer;
        this.mapProperties = mapProperties;
        this.game = game;
        this.stage = stage;

        numOTilesHorizontal = mapProperties.get("width", Integer.class);
        numOTilesVertical = mapProperties.get("height", Integer.class);
        tileWidth = mapProperties.get("tilewidth", Integer.class);
        tileHeight = mapProperties.get("tileheight", Integer.class);
        worldWidth = numOTilesHorizontal*tileWidth;
        worldHeight = numOTilesVertical*tileHeight;
        System.out.println("eo " + enemiesOccupying[1][10]);
    }

    public void render(SpriteBatch batch){
        renderAllies(batch);
    }
    public void renderAllies(SpriteBatch batch){
        if(allyList.size() > 0){
            for (Entity entity : allyList) {
                entity.render(batch);
            }
        }
    }

    public void update(float deltaTime){
        updateAllies(deltaTime);
    }
    private void updateAllies(float deltaTime){
        if(allyList.size() > 0){
            for (Entity entity : allyList) {
                entity.update(deltaTime, enemiesOccupying);
                // if((enemiesAtLane1 && entity.currentLane == 1)){
                //     entity.attacking = true;
                // }
                // else{
                //     entity.attacking = false;
                // }
            }
        }
    }


    public void createAlly(AllySelection ally, float xPos, float yPos){
        String allyString = "nothing";
        switch(ally){
            case mageAlly:
                if(MageAlly.resting && !MageAlly.immobile){
                    allyList.add(new MageAlly(xPos, yPos, tileWidth, tileHeight));
                    MageAlly.resting = false;
                    allyString = "mage";
                }
                selectedAlly = null;
                break;
            case katanaAlly:
                if(KatanaAlly.resting && !KatanaAlly.immobile){
                    allyList.add(new KatanaAlly(xPos, yPos, tileWidth, tileHeight));
                    KatanaAlly.resting = false;
                    allyString = "katana";
                }
                break;
            default:
                break;
        }
        Gdx.app.log("Created", allyString);
    }
    public void destroyAlly(float xPos, float yPos, boolean onlyResting){
        for (Entity entity : allyList) {
            if(entity.getX() == xPos && entity.getY() == yPos){
                alliesToRemove.add(entity);
                updateAllyStatus(entity, true);
            }
        }
        allyList.removeAll(alliesToRemove);
    }
    private void updateAllyStatus(Entity entity, boolean onlyResting){
        switch (entity.typeOfAlly) {
            case mageAlly:
                MageAlly.resting = onlyResting;
                MageAlly.immobile = !onlyResting;
                break;
            case katanaAlly:
                KatanaAlly.resting = onlyResting;
                KatanaAlly.immobile = !onlyResting;
                break;
            default:
                break;
        }
    }

    public void dispose(){
        MageAllyAssets.mageAllyDispose();
        KatanaAllyAssets.katanaAllyDispose();
    }
}
