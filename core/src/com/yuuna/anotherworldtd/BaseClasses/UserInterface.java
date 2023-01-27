package com.yuuna.anotherworldtd.BaseClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ai.GdxLogger;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.yuuna.anotherworldtd.TowerDefenseGame;
import com.yuuna.anotherworldtd.Screens.Isekai;
import com.yuuna.anotherworldtd.Tools.CoolMethGames;
import com.yuuna.anotherworldtd.Tools.EntityManager;
import com.yuuna.anotherworldtd.Tools.AssetManager.UserInterfaceAssets;

public class UserInterface{
    private TowerDefenseGame game;
    private Stage stage;
    private EntityManager entityManager;
    private int worldWidth, worldHeight;
    private int tileWidth, tileHeight;
    private boolean endless;
    private UserInterface  ui = this;

    //tables
    private Table pauseMenu;
    private Table topTable;
    private Table characterSelectorEndlessTable;

    public UserInterface(TowerDefenseGame game, Stage stage, EntityManager entityManager, int worldWidth, int worldHeight, int tileWidth, int tileHeight, boolean endless){
        System.out.println("new ui");
        UserInterfaceAssets.loadUserInterface();

        //variables received from other classes
        this.game = game;
        this.stage = stage;
        this.entityManager = entityManager;
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.endless = endless;

        //pause menu stuff
        pauseMenu = new Table();

        //top table stuff
        topTable = new Table();

        //character selector stuff
        characterSelectorEndlessTable = new Table();

        createPauseMenu(pauseMenu);
        createTopTable(topTable);
        if(endless){
            createCharacterSelectionEndless(characterSelectorEndlessTable);
        }
        else{
            createCharacterSelection();
        }
    }

    private void createPauseMenu(Table table){
        table.setFillParent(true);
        table.align(Align.center | Align.center);

        Button resumeButton =  textImageButton(UserInterfaceAssets.resumDrawable, 1);
        resumeButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                TowerDefenseGame.paused = false;
                pauseMenu.remove();
            }
        });

        Button middleButton;
        if(endless){
            middleButton = textImageButton(UserInterfaceAssets.recordScoreDrawable, 1);
            middleButton.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    System.out.println("recorded");
                }
            });
        }else{
            middleButton = null;
        }
        
        Button mainMenuButton = textImageButton(UserInterfaceAssets.mainMenuDrawable, 1);
        mainMenuButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Isekai(game));
            }
        });

        table.add(resumeButton);
        table.row();
        table.add(middleButton);
        table.row();
        table.add(mainMenuButton);
    }

    private void createTopTable(Table table){
        table.setFillParent(true);
        table.align(Align.topLeft);

        Button settingsButton = makeButton(UserInterfaceAssets.settingDrawable, 1);
        settingsButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("clicked");
                TowerDefenseGame.paused = !TowerDefenseGame.paused;
                if(TowerDefenseGame.paused){
                    stage.addActor(pauseMenu);
                }
                else{
                    pauseMenu.remove();
                }
            }
        });

        table.add(settingsButton);
        stage.addActor(table);
    }

    private void createCharacterSelectionEndless(Table table){
        table.setFillParent(true);
        table.align(Align.bottomLeft);

        Button mageButton = makeButton(UserInterfaceAssets.mageAllyButtonDrawable, 1);
        mageButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("mage selected");
            }
        });

        table.add(mageButton);
        stage.addActor(table);
    }

    private void createCharacterSelection(){

    }

    //TODO convert from normal buttons to textbuttons
    private Button makeButton(Drawable drawable,float scale){
		Button button = new Button(drawable);
        button.setTransform(true);
        button.setScale(UserInterfaceAssets.baseButtonWidth/drawable.getMinWidth()*scale, UserInterfaceAssets.baseButtonHeight/drawable.getMinHeight()*scale);
		return button;
	}
    private Button textImageButton(Drawable drawable, float scale){
        Button button = new Button(drawable);
        button.setWidth(drawable.getMinWidth()*scale);
        button.setHeight(drawable.getMinHeight()*scale);
		return button;
    }
}
