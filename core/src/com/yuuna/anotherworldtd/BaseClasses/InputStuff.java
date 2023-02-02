package com.yuuna.anotherworldtd.BaseClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.maps.MapProperties;
import com.yuuna.anotherworldtd.TowerDefenseGame;
import com.yuuna.anotherworldtd.Tools.CoolMethGames;
import com.yuuna.anotherworldtd.Tools.EntityManager;

public class InputStuff implements InputProcessor{
    private EntityManager entityManager;
    private int worldWidth, worldHeight;
    private int tileWidth, tileHeight;
    private int numOTilesVertical, numOTilesHorizontal;

    public static float mouseXworld, mouseYworld;
    public static int columnHovering, rowHovering;


    public InputStuff(EntityManager entityManager, MapProperties mapProperties){
        this.entityManager = entityManager;

        numOTilesHorizontal = mapProperties.get("width", Integer.class);
        numOTilesVertical = mapProperties.get("height", Integer.class);
        tileWidth = mapProperties.get("tilewidth", Integer.class);
        tileHeight = mapProperties.get("tileheight", Integer.class);
        worldWidth = numOTilesHorizontal*tileWidth;
        worldHeight = numOTilesVertical*tileHeight;
    }

    @Override
    public boolean keyDown(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        int columnToPlace = columnHovering;
        int rowToPlace = rowHovering;

        //get row and column
        if(CoolMethGames.isOdd(columnToPlace)){
            columnToPlace--;
        }
        if(CoolMethGames.isEven(rowToPlace)){
            rowToPlace--;
        }

        //placing and destroying
        boolean inPlaceableArea = CoolMethGames.inRange(columnToPlace, 4, 25, true) && CoolMethGames.inRange(rowToPlace, 3, 14, true);
        if(!TowerDefenseGame.paused && TowerDefenseGame.placing && entityManager.selectedAlly != null && inPlaceableArea){
            entityManager.createAlly(entityManager.selectedAlly, columnToPlace*tileWidth, rowToPlace*tileHeight);
        }
        else if (!TowerDefenseGame.paused && !TowerDefenseGame.placing && inPlaceableArea){
            entityManager.destroyAlly(columnToPlace*tileWidth, rowToPlace*tileHeight, entityManager.alliesOccupying ,true);
            TowerDefenseGame.placing = true;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        mouseXworld = CoolMethGames.screenToWorldCoordinates(screenX, true);
        mouseYworld = CoolMethGames.screenToWorldCoordinates(screenY, false);
        // System.out.println("sx " + screenX + " sy " + screenY);
        columnHovering = (int) (mouseXworld/32);
        rowHovering = (int) (mouseYworld/32);
        // Gdx.app.log("X and Y", mouseXworld + " | " + mouseYworld);
        // Gdx.app.log("Column and Row", String.valueOf(columnHovering) + " | " + String.valueOf(rowHovering));
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        // TODO Auto-generated method stub
        return false;
    }
    
}
