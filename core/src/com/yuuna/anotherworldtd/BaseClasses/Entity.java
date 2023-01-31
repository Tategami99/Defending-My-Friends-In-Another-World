package com.yuuna.anotherworldtd.BaseClasses;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;

public abstract class Entity {
    //determining actions
    public float elapsedTime = 0;
    public Timer timer = new Timer();
    public boolean defeated = false, resting = true, attacking = false;

    //position
    public float xPos, yPos, width, height;
    public int currentColumn, currentRow;
    public int currentHorizontal, currentVertical; // horizontal line  and vertical line in a 64 by 64 placeable area 
    public abstract void initializePosition(int column, int row, float x, float y);

    //stats
    public int maxHealth, originalAttack, originalSpeed, originalDefense;
    public int health, attack, speed, defense;
    public int timeBetweenAttacks;
    public abstract void intializeStats(int entityHealth, int entityAttack, int entitySpeed, int entityDefense);
    public abstract void scaleStat(int stat, int scale);
    public abstract void changeStat(int stat, int numToAddOrSub);

    //rendering
    public Animation<TextureRegion> idleAnimation, attackAnimation;
    public abstract void initializeRenderingInfo(float spriteWidth, float spriteHeight, Animation<TextureRegion> animationIdle, Animation<TextureRegion> animationAttack, int attackInterval);
    public abstract void render(SpriteBatch batch);

    //updating
    public abstract void update(float deltaTime, boolean[][] enemyPositions);
    public abstract void updatePosition();
    public abstract boolean detectOpp(int horizontal, int vertical, boolean[][] enemyPositions);
}
