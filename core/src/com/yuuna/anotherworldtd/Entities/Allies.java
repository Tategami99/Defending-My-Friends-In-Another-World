package com.yuuna.anotherworldtd.Entities;

import com.yuuna.anotherworldtd.Tools.AssetManager.AllyAssets.KatanaAllyAssets;
import com.yuuna.anotherworldtd.Tools.AssetManager.AllyAssets.MageAllyAssets;
import com.yuuna.anotherworldtd.Tools.EntityManager.AllySelection;

public class Allies {
    public static class MageAlly extends Ally{
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
                MageAllyAssets.mageAllyIdleAnimation,
                MageAllyAssets.mageAllyAttackAnimation,
                2
            );
        }

        public static void resetAllyStatus(){
            resting = true;
            immobile = false;
        }
        //detects everything in front of it in that lane
        @Override
        public boolean detectOpp(int horizontal, int vertical, boolean[][] enemyPositions) {
            boolean detected = false;
            for (int i = vertical; i < 11; i++) {
                detected |= enemyPositions[horizontal][i];
            }
            return detected;
        }
    }
    public static class KatanaAlly extends Ally{
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
                KatanaAllyAssets.katanaAllyIdleAnimation,
                KatanaAllyAssets.katanaAllyAttackAnimation,
                2
            );
        }
        public static void resetAllyStatus(){
            resting = true;
            immobile = false;
        }
        //detects the three spaces in front of it
        @Override
        public boolean detectOpp(int horizontal, int vertical, boolean[][] enemyPositions) {
            boolean detected = false;
            for (int i = horizontal - 1; i < horizontal + 1; i++) {
                int oldI = i;
                if(i<0){
                    i = 0;
                }
                detected |= enemyPositions[i][vertical + 1];
                i = oldI;
            }
            return detected;
        }
    }
}
