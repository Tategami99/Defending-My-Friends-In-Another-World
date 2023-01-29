package com.yuuna.anotherworldtd.Entities;

import com.yuuna.anotherworldtd.BaseClasses.Entity;
import com.yuuna.anotherworldtd.Tools.AssetManager.AllyAssets.KatanaAllyAssets;
import com.yuuna.anotherworldtd.Tools.AssetManager.AllyAssets.MageAllyAssets;
import com.yuuna.anotherworldtd.Tools.EntityManager.AllySelection;

public class Allies {
    public static class MageAlly extends Entity{
        public static boolean resting = true, immobile = false;
        public static AllySelection allyType = AllySelection.mageAlly;

        public MageAlly(float xPos, float yPos, int tileWidth, int tileHeight){
            typeOfAlly = allyType;

            initializePosition(
            (int) xPos/tileWidth, 
            (int) yPos/tileHeight, 
            xPos, 
            yPos
            );

            initializeRenderingInfo(
                MageAllyAssets.mageAllyIdleTexture.getWidth()/4, 
                MageAllyAssets.mageAllyIdleTexture.getHeight()/1, 
                MageAllyAssets.mageAllyIdleAnimation
            );
        }

        public static void resetAllyStatus(){
            resting = true;
            immobile = false;
        }
    }
    public static class KatanaAlly extends Entity{
        public static boolean resting = true, immobile = false;
        public static AllySelection allyType = AllySelection.katanaAlly;

        public KatanaAlly(float xPos, float yPos, int tileWidth, int tileHeight){
            typeOfAlly = allyType;

            initializePosition(
            (int) xPos/tileWidth, 
            (int) yPos/tileHeight, 
            xPos, 
            yPos
            );

            initializeRenderingInfo(
                KatanaAllyAssets.katanaAllyIdleTexture.getWidth()/4, 
                KatanaAllyAssets.katanaAllyIdleTexture.getHeight()/1, 
                KatanaAllyAssets.katanaAllyIdleAnimation
            );
        }
    }
}