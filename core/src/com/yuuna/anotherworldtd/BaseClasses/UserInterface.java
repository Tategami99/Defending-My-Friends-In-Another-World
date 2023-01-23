package com.yuuna.anotherworldtd.BaseClasses;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.yuuna.anotherworldtd.TowerDefenseGame;
import com.yuuna.anotherworldtd.Screens.Isekai;
import com.yuuna.anotherworldtd.Tools.CoolMethGames;
import com.yuuna.anotherworldtd.Tools.AssetManager.UserInterfaceAssets;

public class UserInterface {
    private TowerDefenseGame game;
    private Stage stage;
    private int worldWidth, worldHeight;
    private int tileWidth, tileHeight;
    private boolean endless;

    //tables
    private Table pauseMenu;
    private Table topTable;
    private Table characterSelectorEndlessTable;

    public UserInterface(TowerDefenseGame game, Stage stage, int worldWidth, int worldHeight, int tileWidth, int tileHeight, boolean endless){
        System.out.println("new ui");
        UserInterfaceAssets.loadUserInterface();

        //variables received from other classes
        this.game = game;
        this.stage = stage;
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.endless = endless;

        //pause menu stuff
        pauseMenu = new Table();
        pauseMenu.setFillParent(true);

        //top table stuff
        topTable = new Table();
        topTable.setFillParent(true);
        stage.addActor(topTable);
        float topX = CoolMethGames.worldToCameraCoordinates(0 + tileWidth, true);
        float topY = CoolMethGames.worldToCameraCoordinates(worldHeight - tileHeight, false);
        // System.out.println(x);
        //sets the position of the center of the table
        topTable.setPosition(topX, topY);

        //character selector stuff
        characterSelectorEndlessTable = new Table();
        characterSelectorEndlessTable.setFillParent(true);
        stage.addActor(characterSelectorEndlessTable);
        float csX = CoolMethGames.worldToCameraCoordinates(0 + tileWidth, true);
        float csY = CoolMethGames.worldToCameraCoordinates(0 + tileHeight, false);
        characterSelectorEndlessTable.setPosition(csX, csY);

        createPauseMenu();
        createTopTable();
        if(endless){
            createCharacterPlacerEndless();
        }
        else{
            createCharacterPlacer();
        }
    }

    private void createPauseMenu(){
        createButton(UserInterfaceAssets.resumDrawable, pauseMenu, true).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                TowerDefenseGame.paused = false;
                pauseMenu.remove();
            }
        });
        if(endless){
            createButton(UserInterfaceAssets.recordScoreDrawable, pauseMenu, true).addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    System.out.println("recorded");
                }
            });
        }
        createButton(UserInterfaceAssets.mainMenuDrawable, pauseMenu, true).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Isekai(game));
            }
        });
    }

    private void createTopTable(){
        createButton(UserInterfaceAssets.settingDrawable, topTable, false).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                TowerDefenseGame.paused = !TowerDefenseGame.paused;
                if(TowerDefenseGame.paused){
                    stage.addActor(pauseMenu);
                }
                else{
                    pauseMenu.remove();
                }
            }
        });
    }

    private void createCharacterPlacerEndless(){
        createButton(UserInterfaceAssets.mageAllyButtonDrawable, characterSelectorEndlessTable, false).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("mage selected");
            }
        });
    }

    private void createCharacterPlacer(){

    }

    private ImageButton createButton(Drawable drawable, Table table, boolean newRow){
        System.out.println("button created at " + table.getX() + " and " + table.getY());
		ImageButton button = new ImageButton(drawable);
		table.add(button).fill().padBottom(10);
		if(newRow){
            table.row();
        }
		return button;
	}
}
