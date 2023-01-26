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
import com.yuuna.anotherworldtd.TowerDefenseGame;
import com.yuuna.anotherworldtd.Screens.Isekai;
import com.yuuna.anotherworldtd.Tools.CoolMethGames;
import com.yuuna.anotherworldtd.Tools.AssetManager.UserInterfaceAssets;

public class UserInterface implements InputProcessor{
    private TowerDefenseGame game;
    private Stage stage;
    private int worldWidth, worldHeight;
    private int tileWidth, tileHeight;
    private boolean endless;
    private UserInterface  ui = this;

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
        topTable.setTransform(true);
        topTable.setWidth(worldWidth);
        topTable.setHeight(UserInterfaceAssets.baseButtonHeight);
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
        // characterSelectorEndlessTable.setPosition(csX, csY);

        createPauseMenu();
        createTopTable();
        if(endless){
            createCharacterSelectionEndless();
        }
        else{
            createCharacterSelection();
        }
    }

    private void createPauseMenu(){
        createButton(UserInterfaceAssets.resumDrawable, pauseMenu, true, 1).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                TowerDefenseGame.paused = false;
                pauseMenu.remove();
            }
        });
        if(endless){
            createButton(UserInterfaceAssets.recordScoreDrawable, pauseMenu, true, 1).addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    System.out.println("recorded");
                }
            });
        }
        createButton(UserInterfaceAssets.mainMenuDrawable, pauseMenu, true, 1).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Isekai(game));
            }
        });
    }

    private void createTopTable(){
        Button settingsButton = createButton(UserInterfaceAssets.settingDrawable, topTable, false, 1);
        settingsButton.setPosition(64, 0);
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
    }

    private void createCharacterSelectionEndless(){
        Button mageButton = characterButton(UserInterfaceAssets.mageAllyButtonDrawable, characterSelectorEndlessTable, false);
        mageButton.setPosition(0, 0);
        mageButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("mage selected");
                Gdx.input.setInputProcessor(ui);
            }
        });
    }

    private void createCharacterSelection(){

    }

    private Button createButton(Drawable drawable, Table table, boolean newRow, float scale){
        System.out.println("button created at " + table.getX() + " and " + table.getY());
		Button button = new Button(drawable);
        button.setTransform(true);
        button.setScale(scale, scale);
		table.add(button).fill().padBottom(10);
		if(newRow){
            table.row();
        }
		return button;
	}
    private Button characterButton(Drawable drawable, Table table, boolean newRow){
        System.out.println("c button created at " + table.getX() + " and " + table.getY());
		Button button = new Button(drawable);
        button.setTransform(true);
        button.setScale(0.5f, 0.5f);
		table.add(button).fill().padBottom(10);
		if(newRow){
            table.row();
        }
		return button;
	}

    @Override
    public boolean keyDown(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        float worldX = CoolMethGames.screenToWorldCoordinates(screenX, true);
        float worldY = CoolMethGames.screenToWorldCoordinates(screenY, false);
        System.out.println("sx " + screenX + " sy " + screenY);
        System.out.println("x " + worldX + " y " + worldY);
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        // TODO Auto-generated method stub
        return false;
    }
}
