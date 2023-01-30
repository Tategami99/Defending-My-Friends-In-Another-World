package com.yuuna.anotherworldtd.BaseClasses;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Attack {
    private float xPos, yPos;
    private float elapsedTime = 0, scale;
    private int attackPwr;
    private boolean isProjectile;
    private Animation<TextureRegion> projectileAnimation;
    
    public Attack(int attack, boolean isProjectile, Animation<TextureRegion> projectileAnimation, float scale){
        attackPwr = attack;
        this.isProjectile = isProjectile;
        this.projectileAnimation = projectileAnimation;
        this.scale = scale;
    }

    //set methods
    public void setX(float x){
        xPos = x;
    }
    public void setY(float y){
        yPos = y;
    }

    //get methods
    public float getX(){
        return xPos;
    }
    public float getY(){
        return yPos;
    }

    //other methods
    public void render(SpriteBatch batch, float deltaTime){
        elapsedTime += deltaTime;
        if(isProjectile){
            batch.draw(projectileAnimation.getKeyFrame(elapsedTime, true), getX(), getY(), 0, 0, projectileAnimation.getKeyFrame(elapsedTime, true).getRegionWidth(), projectileAnimation.getKeyFrame(elapsedTime, true).getRegionHeight(), scale, scale, 0);
        }
        
    }
}
