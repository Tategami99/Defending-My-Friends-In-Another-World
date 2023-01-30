package com.yuuna.anotherworldtd.BaseClasses;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Enemy {
    //position
    private float xPos, yPos, width, height;
    public int currentColumn, currentRow;
    public int currentHorizontal, currentVertical; // horizontal line  and vertical line in a 64 by 64 placeable area 

    //rendering
    private int interval;
    public boolean attacking = false;
    public float elapsedTime = 0;


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

    //other methods
    public void render(SpriteBatch batch){

    }

    public void update(float deltaTime){

    }
}
