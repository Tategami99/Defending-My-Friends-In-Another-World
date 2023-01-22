package com.yuuna.anotherworldtd.Tools;

import com.badlogic.gdx.Gdx;

public class CoolMethGames {
    public static boolean inRange(float number, float min, float max, boolean inclusive){
        boolean isInRange;
        if(inclusive && number >= min && number <= max){
            isInRange = true;
        }
        else if(!inclusive && number > min && number < max){
            isInRange = true;
        }
        else{
            isInRange = false;
        }
        return isInRange;
    }
    public static float clampNumber(float number, float min, float max){
        return Math.max(min, Math.min(max, number));
    }
    public static float monitorToWorldCoordinates(float monitor, int worldWidth, int worldHeight, boolean isX){
        //note: monitor coords r read from the center of the screen as opposed to one of the left corners
        if(isX){
            return monitor*worldWidth/Gdx.graphics.getWidth();
        }
        else{
            return monitor*worldHeight/Gdx.graphics.getHeight();
        }
    }
    public static float worldToMonitorCoordinates(float world, int worldWidth, int worldHeight, boolean isX){
        //note: monitor coords r read from the center of the screen as opposed to one of the left corners
        if(isX){
            return world*Gdx.graphics.getWidth()/worldWidth;
        }
        else{
            return world*Gdx.graphics.getHeight()/worldHeight;
        }
    }
}
