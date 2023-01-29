package com.yuuna.anotherworldtd.Tools;

import com.badlogic.gdx.Gdx;

public class CoolMethGames {
    public static float cameraX;
    public static float cameraY;
    public static float cameraWidth;
    public static float cameraHeight;

    public static void cameraInfo(float width, float height){
        cameraWidth = width;
        cameraHeight = height;
        cameraX = cameraWidth/2;
        cameraY = cameraHeight/2;
    }

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
    
    public static float worldToCameraCoordinates(float world, boolean isX){
        if(isX){
            float camX = world - cameraX;
            return camX;
        }
        else{
            float camY = world - cameraY;
            return camY;
        }
    }
    public static float screenToWorldCoordinates(float screen, boolean isX){
        if(isX){
            float worldX = screen*cameraWidth/Gdx.graphics.getWidth();
            return worldX;
        }
        else{
            float worldY = cameraHeight - (screen*cameraHeight/Gdx.graphics.getHeight());
            return worldY;
        }
    }

    public static boolean isOdd(int number){
        if(number % 2 == 0){
            return false;
        }
        return true;
    }
    public static boolean isEven(int number){
        if(number % 2 == 0){
            return true;
        }
        return false;
    }
}
