package com.yuuna.anotherworldtd.BaseClasses;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.yuuna.anotherworldtd.Tools.CoolMethGames;
import com.yuuna.anotherworldtd.Tools.AssetManager.AllyAssets;
import com.yuuna.anotherworldtd.Tools.EntityManager.AllySelection;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;

public class Entity {
    //atack area stuff
    public enum AttackArea{
        InFront
    }
    public AttackArea attackArea = null;

    //position
    private float xPos, yPos, width, height;
    public int currentColumn, currentRow;
    public int currentHorizontal, currentVertical; // horizontal line  and vertical line in a 64 by 64 placeable area 

    //rendering
    private int interval;
    public boolean attacking = false;
    public float elapsedTime = 0;

    //stats
    public int maxHealth, originalAttack, originalSpeed, originalDefense;
    public int health, attack, speed, defense;
    private Animation<TextureRegion> idleAnimation, attackAnimation;
    public AllySelection typeOfAlly;

    private Timer timer = new Timer();
    private boolean timerCalled;

    //initialize methods
    public void initializePosition(int column, int row, float x, float y){
        currentColumn = column;
        currentRow = row;
        xPos = x;
        yPos = y;
    }
    public void initializeRenderingInfo(float spriteWidth, float spriteHeight, Animation<TextureRegion> animationIdle, Animation<TextureRegion> animationAttack, int attackInterval){
        width = spriteWidth;
        height = spriteHeight;
        idleAnimation = animationIdle;
        attackAnimation = animationAttack;
        interval = attackInterval;
    }
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

    //return methods
    public int getColumn(){
        return currentColumn;
    }
    public int getRow(){
        return currentRow;
    }
    public float getX(){
        return xPos;
    }
    public float getY(){
        return yPos;
    }
    public float getWidth(){
        return width;
    }
    public float getHeight(){
        return height;
    }
    public int getAttackInterval(){
        return interval;
    }

    //set methods
    public void setColumn(int column){
        currentColumn = column;
    }
    public void setRow(int row){
        currentRow = row;
    }
    public void setX(float x){
        xPos = x;
    }
    public void setY(float y){
        yPos = y;
    }
    public void setWidth(float w){
        width = w;
    }
    public void setHeight(float h){
        height = h;
    }
    public void setAttackInterval(int attackInterval){
        interval = attackInterval;
    }

    //stat altering methods
    public void takeDamage(int enemyAttack){
        health -= enemyAttack + defense;
    }
    public void scaleSpeed(float scale){
        speed *= scale;
    }
    public void scaleDefense(float scale){
        defense *= scale;
    }
    public void scaleAttack(float scale){
        attack *= scale;
    }

    //collisions
    public boolean checkOtherEntityCollisions(float otherX, float otherY, float otherWidth, float otherHeight){
        boolean collided = false;
        collided |= CoolMethGames.inRange(otherX, xPos, xPos + width, true) && ((CoolMethGames.inRange(otherY, yPos, yPos + height, true)) || (CoolMethGames.inRange(otherY + otherHeight, yPos, yPos + height, true)));
        collided |= CoolMethGames.inRange(otherX + otherWidth, xPos, xPos + width, true) && ((CoolMethGames.inRange(otherY, yPos, yPos + height, true)) || (CoolMethGames.inRange(otherY + otherHeight, yPos, yPos + height, true)));

        return collided;
    }

    //other methods
    public void render(SpriteBatch batch){
        if(!attacking){
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    attacking = true;
                    timerCalled = false;
                }
            }, interval);
            batch.draw(idleAnimation.getKeyFrame(elapsedTime, true), getX(), getY(), 0, 0, AllyAssets.baseAllyWidth, AllyAssets.baseAllyHeight, 1, 1, 0);
        }
        else{
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    attacking = false;
                    timerCalled = false;
                }
            }, interval);
            batch.draw(attackAnimation.getKeyFrame(elapsedTime, true), getX(), getY(), 0, 0, AllyAssets.baseAllyWidth, AllyAssets.baseAllyHeight, 1, 1, 0);
        }
    }

    public void update(float deltaTime, boolean[][] enemyPositions){
        elapsedTime += deltaTime;
        updatePosition();
        detectEnemies(currentHorizontal, currentVertical, enemyPositions);
        if(attacking){
            System.out.println("truuuu");
        }
    }
    private void updatePosition(){
        // Gdx.app.log("Column and Row", Integer.toString(currentColumn) + " | " + Integer.toString(currentRow));
        currentHorizontal = (currentRow/2) - 1;
        currentVertical = (currentColumn/2) - 2;
        Gdx.app.log("H and V", Integer.toString(currentHorizontal) + " | " + Integer.toString(currentVertical));

    }
    private void detectEnemies(int horiziontal, int vertical, boolean[][] enemyPositions) {
        switch (attackArea) {
            case InFront:
                for (int i = vertical; i < 11; i++) {
                    attacking = enemyPositions[horiziontal][i];
                    if(attacking){
                        break;
                    }
                }
                break;
        
            default:
                break;
        }
    }


    //might need it later or not
    // if(!attacking && !timerCalled){
        //     timerCalled = true;
        //     timer.scheduleTask(new Timer.Task() {
        //         @Override
        //         public void run() {
        //             attacking = true;
        //             timerCalled = false;
        //             Gdx.app.log("Timer", "called");
        //         }
        //     }, interval);
        // }
        // else if (attacking && !timerCalled){
        //     timerCalled = true;
        //     timer.scheduleTask(new Timer.Task() {
        //         @Override
        //         public void run() {
        //             attacking = false;
        //             timerCalled = false;
        //         }
        //     }, interval);
        // }
        // else if(!attacking){
        //     batch.draw(idleAnimation.getKeyFrame(elapsedTime, true), getX(), getY(), 0, 0, AllyAssets.baseAllyWidth, AllyAssets.baseAllyHeight, 1, 1, 0);
        // }
        // else if(attacking){
        //     batch.draw(attackAnimation.getKeyFrame(elapsedTime, true), getX(), getY(), 0, 0, AllyAssets.baseAllyWidth, AllyAssets.baseAllyHeight, 1, 1, 0);
        // }
}
