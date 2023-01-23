package com.yuuna.anotherworldtd.BaseClasses;

import com.yuuna.anotherworldtd.Tools.CoolMethGames;

public class Entity {
    private float xPos, yPos, width, height;
    public int maxHealth, originalAttack, originalSpeed, originalDefense;
    public int health, attack, speed, defense;

    public void initialize(int entityHealth, int entityAttack, int entitySpeed, int entityDefense){
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

    //set methods
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
}
