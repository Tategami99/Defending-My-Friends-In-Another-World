package com.yuuna.anotherworldtd.Entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.yuuna.anotherworldtd.BaseClasses.Entity;
import com.yuuna.anotherworldtd.Tools.AssetManager.AllyAssets;
import com.yuuna.anotherworldtd.Tools.EntityManager.AllySelection;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;

public class Ally extends Entity{
    //ally specific
    public AllySelection typeOfAlly;

    //position
    @Override
    public void initializePosition(int tileWidth, int tileHeight, int column, int row, float x, float y){
        currentColumn = column;
        currentRow = row;
        xPos = x;
        yPos = y;
    }

    //rendering
    @Override
    public void initializeRenderingInfo(float spriteWidth, float spriteHeight, Animation<TextureRegion> animationIdle, Animation<TextureRegion> animationAttack, int attackInterval){
        width = spriteWidth;
        height = spriteHeight;
        idleAnimation = animationIdle;
        attackAnimation = animationAttack;
        timeBetweenAttacks = attackInterval;
    }
    @Override
    public void render(SpriteBatch batch){
        if(!attacking){
            batch.draw(idleAnimation.getKeyFrame(elapsedTime, true), xPos, yPos, 0, 0, AllyAssets.baseAllyWidth, AllyAssets.baseAllyHeight, 1, 1, 0);
        }
        else{
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    System.out.println("attacked");
                    timer.clear();
                }
            }, timeBetweenAttacks);
            batch.draw(attackAnimation.getKeyFrame(elapsedTime, true), xPos, yPos, 0, 0, AllyAssets.baseAllyWidth, AllyAssets.baseAllyHeight, 1, 1, 0);
        }
    }

    //stats
    @Override
    public void intializeStats(int entityHealth, int entityAttack, int entitySpeed, int entityDefense){
        maxHealth = entityHealth;
        health = maxHealth;
        originalAttack = entityAttack;
        attack = originalAttack;
        originalSpeed = entitySpeed;
        speed = originalSpeed;
        originalDefense = entityDefense;
        defense = originalDefense;
    }
    @Override
    public void scaleStat(int stat, int scale) {
        stat *= scale;
    }
    @Override
    public void changeStat(int stat, int numToAddOrSub) {
        stat += numToAddOrSub;
    }

    //updating
    @Override
    public void update(float deltaTime, boolean[][] oppPositions, boolean[][] goodPositions){
        elapsedTime += deltaTime;
        updatePosition(goodPositions);
        attacking = detectOpp(currentHorizontal, currentVertical, oppPositions);
    }
    @Override
    public void updatePosition(boolean[][] goodPositions) {
        oldHorizontal = currentHorizontal;
        oldVertical = currentVertical;
        move();

        if(tileWidth != 0 && tileHeight != 0){
            currentRow = (int) xPos/tileWidth;
            currentColumn = (int) yPos/tileHeight;
        }
        currentHorizontal = (currentRow/2) - 1;
        currentVertical = (currentColumn/2) - 2;
        // Gdx.app.log("H and V", Integer.toString(currentHorizontal) + " | " + Integer.toString(currentVertical));
        if(oldHorizontal != currentHorizontal && oldVertical != currentVertical){
            goodPositions[oldHorizontal][oldVertical] = false;
        }
        goodPositions[currentHorizontal][currentVertical] = true;
        System.out.println(Boolean.toString(goodPositions[5][10]));
    }
    @Override
    public boolean detectOpp(int horizontal, int vertical, boolean[][] oppPositions) {
        //todo make the default one space to the left of the thing
        return false;
    }

    @Override
    public void move() {
        // TODO Auto-generated method stub
        
    }
}
