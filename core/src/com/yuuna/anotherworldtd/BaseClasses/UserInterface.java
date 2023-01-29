package com.yuuna.anotherworldtd.BaseClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.yuuna.anotherworldtd.TowerDefenseGame;
import com.yuuna.anotherworldtd.Screens.Isekai;
import com.yuuna.anotherworldtd.Tools.EntityManager;
import com.yuuna.anotherworldtd.Tools.AssetManager.UserInterfaceAssets;
import com.yuuna.anotherworldtd.Tools.EntityManager.AllySelection;

public class UserInterface{
    private TowerDefenseGame game;
    private Stage stage;
    private EntityManager entityManager;
    private int worldWidth, worldHeight;
    private int tileWidth, tileHeight;
    private int numOTilesVertical, numOTilesHorizontal;
    private boolean endless;
    private UserInterface  ui = this;

    //tables
    private Table pauseMenu;
    private Table topTable;
    private Table characterSelectorEndlessTable;
    private Table otherTable;

    public UserInterface(TowerDefenseGame game, Stage stage, EntityManager entityManager, MapProperties mapProperties, boolean endless){
        System.out.println("new ui");
        UserInterfaceAssets.loadUserInterface();

        //variables received from other classes
        this.game = game;
        this.stage = stage;
        this.entityManager = entityManager;
        numOTilesHorizontal = mapProperties.get("width", Integer.class);
        numOTilesVertical = mapProperties.get("height", Integer.class);
        tileWidth = mapProperties.get("tilewidth", Integer.class);
        tileHeight = mapProperties.get("tileheight", Integer.class);
        worldWidth = numOTilesHorizontal*tileWidth;
        worldHeight = numOTilesVertical*tileHeight;
        this.endless = endless;

        //pause menu stuff
        pauseMenu = new Table();

        //top table stuff
        topTable = new Table();

        //character selector stuff
        characterSelectorEndlessTable = new Table();

        //other table stuff
        otherTable = new Table();

        createPauseMenu(pauseMenu);
        createTopTable(topTable);
        if(endless){
            createCharacterSelectionEndless(characterSelectorEndlessTable);
        }
        else{
            createCharacterSelection();
        }
        createOtherTable(otherTable);
    }

    private void createPauseMenu(Table table){
        table.setFillParent(true);
        table.align(Align.center | Align.center);

        Button resumeButton =  non64by64Button(UserInterfaceAssets.resumDrawable, 1);
        resumeButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                TowerDefenseGame.paused = false;
                pauseMenu.remove();
            }
        });

        Button middleButton;
        if(endless){
            middleButton = non64by64Button(UserInterfaceAssets.recordScoreDrawable, 1);
            middleButton.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    System.out.println("recorded");
                }
            });
        }else{
            middleButton = null;
        }
        
        Button mainMenuButton = non64by64Button(UserInterfaceAssets.mainMenuDrawable, 1);
        mainMenuButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Isekai(game));
            }
        });

        table.add(resumeButton).padBottom(UserInterfaceAssets.baseButtonHeight/8);
        table.row();
        table.add(middleButton).padBottom(UserInterfaceAssets.baseButtonHeight/8);
        table.row();
        table.add(mainMenuButton).padBottom(UserInterfaceAssets.baseButtonHeight/8);
    }

    private void createTopTable(Table table){
        table.setFillParent(true);
        table.align(Align.topLeft);
        table.setDebug(true);

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
        table.setDebug(true);

        Button mageButton = makeButton(UserInterfaceAssets.mageAllyButtonDrawable, 1);
        mageButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(entityManager.selectedAlly != AllySelection.mageAlly){
                    entityManager.selectedAlly = AllySelection.mageAlly;
                    Gdx.app.log("Ally", "mage");
                }
                else{
                    entityManager.selectedAlly = null;
                    Gdx.app.log("Ally", "null");
                }
            }
        });

        Button katanaButton = makeButton(UserInterfaceAssets.katanaAllyButtonDrawable, 1);
        katanaButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(entityManager.selectedAlly != AllySelection.katanaAlly){
                    entityManager.selectedAlly = AllySelection.katanaAlly;
                    Gdx.app.log("Ally", "katana");
                }
                else{
                    entityManager.selectedAlly = null;
                    Gdx.app.log("Ally", "null");
                }
            }
        });

        table.setSize(worldWidth, UserInterfaceAssets.baseButtonHeight);
        table.add(mageButton).size(UserInterfaceAssets.baseButtonWidth, UserInterfaceAssets.baseButtonHeight).padRight(UserInterfaceAssets.baseButtonWidth/8);
        table.add(katanaButton).size(UserInterfaceAssets.baseButtonWidth, UserInterfaceAssets.baseButtonHeight).padRight(UserInterfaceAssets.baseButtonWidth/8);
        stage.addActor(table);
    }

    private void createCharacterSelection(){

    }
    
    private void createOtherTable(Table table){
        table.setFillParent(true);
        table.align(Align.right | Align.center);
        table.setDebug(true);

        Button restButton = makeButton(UserInterfaceAssets.restButtonDrawable, 1);
        restButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                
            }
        });

        table.setSize(UserInterfaceAssets.baseButtonWidth, worldHeight);
        table.add(restButton).size(UserInterfaceAssets.baseButtonWidth, UserInterfaceAssets.baseButtonHeight);
        stage.addActor(table);
    }

    //TODO convert from normal buttons to textbuttons
    private Button makeButton(Drawable drawable,float scale){
		Button button = new Button(drawable);
        button.setTransform(true);
        // button.setScale(UserInterfaceAssets.baseButtonWidth/drawable.getMinWidth()*scale, UserInterfaceAssets.baseButtonHeight/drawable.getMinHeight()*scale);
		return button;
	}
    private Button non64by64Button(Drawable drawable, float scale){
        Button button = new Button(drawable);
        button.setWidth(drawable.getMinWidth()*scale);
        button.setHeight(drawable.getMinHeight()*scale);
		return button;
    }
}
