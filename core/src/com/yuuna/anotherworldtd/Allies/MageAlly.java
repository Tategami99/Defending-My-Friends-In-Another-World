package com.yuuna.anotherworldtd.Allies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.yuuna.anotherworldtd.BaseClasses.Entity;
import com.yuuna.anotherworldtd.Tools.AssetManager.AllyAssets.MageAllyAssets;

public class MageAlly extends Entity{
    private float elapsedTime = 0;
    public static boolean resting = true, immobile = false;

    public MageAlly(float xPos, float yPos){
        setX(xPos);
        setY(yPos);

        setWidth(MageAllyAssets.mageAllyIdleTexture.getWidth()/4);
        setHeight(MageAllyAssets.mageAllyIdleTexture.getHeight()/1);
    }

    public void render(SpriteBatch batch, float deltaTime){
        elapsedTime += deltaTime;
        batch.draw(MageAllyAssets.mageAllyIdleAnimation.getKeyFrame(elapsedTime, true), getX(), getY(), 0, 0, getWidth(), getHeight(), 1, 1, 0);
    }
}
