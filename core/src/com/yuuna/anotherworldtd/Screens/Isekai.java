package com.yuuna.anotherworldtd.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.yuuna.anotherworldtd.TowerDefenseGame;
import com.yuuna.anotherworldtd.Tools.AssetManager.IsekaiAssets;

public class Isekai extends ScreenAdapter {
	private TowerDefenseGame game;

	private Viewport viewport;
	private OrthographicCamera camera;
	private Stage stage;
	private SpriteBatch batch;
	private Table mainTable;

	public Isekai(TowerDefenseGame game){
		//set game
		this.game = game;

		//load assets
		IsekaiAssets.loadIsekai();

		//viewport
		viewport = new FitViewport(IsekaiAssets.backgroundTextureRegion.getRegionWidth(), IsekaiAssets.backgroundTextureRegion.getRegionHeight());

		//set camera stuff
		camera = new OrthographicCamera();
		camera.setToOrtho(false, viewport.getWorldWidth(), viewport.getWorldHeight());

		//stage stuff
		stage = new Stage(viewport);
		mainTable = new Table();
		mainTable.setFillParent(true);
		stage.addActor(mainTable);
		Gdx.input.setInputProcessor(stage);

		//initialize other stuff
		batch = new SpriteBatch();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1F, 1F, 1F, 1F);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | GL20.GL_STENCIL_BUFFER_BIT);

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		renderStuff();

		stage.draw();
	}

	@Override
	public void show() {
		createButton(IsekaiAssets.storyModeDrawable).addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("clicked");
			}
		});
		createButton(IsekaiAssets.endlessModeDrawable).addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("clicked");
				game.setScreen(new Endless(game));
			}
		});
		createButton(IsekaiAssets.quitGameDrawable).addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("clicked");
				Gdx.app.exit();
			}
		});
	}

	@Override
	public void hide() {
		dispose();
	}
	
	@Override
	public void dispose () {
		stage.dispose();
		batch.dispose();
		IsekaiAssets.disposeIsekai();
	}


	//my methods
	private void renderStuff(){
		batch.begin();
		//render code
		batch.draw(IsekaiAssets.backgroundTextureRegion, 0, 0, camera.viewportWidth, camera.viewportHeight);
		batch.end();
	}

	private ImageButton createButton(Drawable drawable){
		ImageButton button = new ImageButton(drawable);
		mainTable.add(button).fill().padBottom(10);
		mainTable.row();
		return button;
	}
}
