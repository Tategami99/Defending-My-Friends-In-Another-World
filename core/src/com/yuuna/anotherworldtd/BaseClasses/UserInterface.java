package com.yuuna.anotherworldtd.BaseClasses;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.yuuna.anotherworldtd.Tools.AssetManager.UserInterfaceAssets;

public class UserInterface {
    private Stage stage;
    private Table topTable;

    public UserInterface(Stage stage, int worldWidth, int worldHeight){
        UserInterfaceAssets.loadUserInterface();

        //variables received from other classes
        this.stage = stage;

        //top table stuff
        topTable = new Table();
        topTable.setFillParent(true);
        stage.addActor(topTable);
        topTable.setPosition(worldWidth/2, worldHeight/2);

        onCreation();
    }

    private void onCreation(){
        System.out.println("created");
        //top table stuff
        createButton(null, topTable, false).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("ui clicked");
            }
        });
    }

    private ImageButton createButton(Drawable drawable, Table table, boolean newRow){
        System.out.println("button created");
		ImageButton button = new ImageButton(drawable);
		table.add(button).fill().padBottom(10);
		if(newRow){
            table.row();
        }
		return button;
	}
}
