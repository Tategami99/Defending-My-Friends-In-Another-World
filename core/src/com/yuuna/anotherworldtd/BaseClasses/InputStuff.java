package com.yuuna.anotherworldtd.BaseClasses;

import com.badlogic.gdx.InputProcessor;
import com.yuuna.anotherworldtd.Tools.CoolMethGames;

public class InputStuff implements InputProcessor{

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
        float worldX = CoolMethGames.screenToWorldCoordinates(screenX, true);
        float worldY = CoolMethGames.screenToWorldCoordinates(screenY, false);
        // System.out.println("sx " + screenX + " sy " + screenY);
        // System.out.println("x " + worldX + " y " + worldY);
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        // TODO Auto-generated method stub
        return false;
    }
    
}
