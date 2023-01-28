package com.yuuna.anotherworldtd.BaseClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.yuuna.anotherworldtd.Tools.CoolMethGames;

public class InputStuff implements InputProcessor{
    public static float mouseXworld, mouseYworld;
    public static int columnHovering, rowHovering;

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
        // TODO Auto-generated method stub
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
        Gdx.app.log("X and Y", mouseXworld + " | " + mouseYworld);
        columnHovering = (int) (mouseXworld/32);
        rowHovering = (int) (mouseYworld/32);
        Gdx.app.log("Column and Row", String.valueOf(columnHovering) + " | " + String.valueOf(rowHovering));
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        // TODO Auto-generated method stub
        return false;
    }
    
}
