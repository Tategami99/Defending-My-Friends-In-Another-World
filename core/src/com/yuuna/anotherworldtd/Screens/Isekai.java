package com.yuuna.anotherworldtd.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.yuuna.anotherworldtd.TowerDefenseGame;
import com.yuuna.anotherworldtd.Tools.AssetManager.IsekaiScreen;;

public class Isekai extends ScreenAdapter {
	private TowerDefenseGame game;

	private Viewport viewport;
	private OrthographicCamera camera;
	private Stage stage;
	private SpriteBatch batch;

	public Isekai(TowerDefenseGame game){
		//set game
		this.game = game;

		//load assets
		IsekaiScreen.loadIsekai();

		//viewport
		viewport = new FitViewport(IsekaiScreen.backgroundTextureRegion.getRegionWidth(), IsekaiScreen.backgroundTextureRegion.getRegionHeight());

		//set camera stuff
		camera = new OrthographicCamera();
		camera.setToOrtho(false, viewport.getWorldWidth(), viewport.getWorldHeight());

		//initialize other stuff
		batch = new SpriteBatch();
		stage = new Stage(viewport);
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

	}

	@Override
	public void hide() {
		dispose();
	}
	
	@Override
	public void dispose () {
		stage.dispose();
		batch.dispose();
		IsekaiScreen.disposeIsekai();
	}


	//my methods
	private void renderStuff(){
		batch.begin();
		//render code
		batch.draw(IsekaiScreen.backgroundTextureRegion, 0, 0, camera.viewportWidth, camera.viewportHeight);
		batch.end();
	}
}
