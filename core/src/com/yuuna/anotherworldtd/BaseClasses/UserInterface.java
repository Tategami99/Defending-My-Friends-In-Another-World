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
    private boolean endless;

    //tables
    private Table pauseMenu;
    private Table topTable;

    public UserInterface(TowerDefenseGame game, Stage stage, int worldWidth, int worldHeight, boolean endless){
        System.out.println("new ui");
        UserInterfaceAssets.loadUserInterface();

        //variables received from other classes
        this.game = game;
        this.stage = stage;
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.endless = endless;

        //pause menu stuff
        pauseMenu = new Table();
        pauseMenu.setFillParent(true);

        //top table stuff
        topTable = new Table();
        topTable.setFillParent(true);
        stage.addActor(topTable);
        float x = CoolMethGames.monitorToWorldCoordinates(-worldWidth + UserInterfaceAssets.baseButtonWidth*1.5f, worldWidth, worldHeight, true);
        float y = CoolMethGames.monitorToWorldCoordinates(worldHeight - UserInterfaceAssets.baseButtonHeight*2, worldWidth, worldHeight, false);
        // System.out.println(x);
        topTable.setPosition(x, y);

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
