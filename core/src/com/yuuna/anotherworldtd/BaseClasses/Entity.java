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
    private float xPos, yPos, width, height;
    private int interval;
    private boolean attacking = false;
    public int maxHealth, originalAttack, originalSpeed, originalDefense;
    public int health, attack, speed, defense;
    public int currentColumn, currentRow;
    public float elapsedTime = 0;
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
    public void render(SpriteBatch batch, float deltaTime){
        elapsedTime += deltaTime;
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
