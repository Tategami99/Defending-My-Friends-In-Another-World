package com.yuuna.anotherworldtd.Tools;

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
}
