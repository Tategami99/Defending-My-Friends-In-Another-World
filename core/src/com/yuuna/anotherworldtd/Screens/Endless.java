package com.yuuna.anotherworldtd.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.yuuna.anotherworldtd.TowerDefenseGame;
import com.yuuna.anotherworldtd.Tools.AssetManager.EndlessAssets;

public class Endless extends ScreenAdapter {
	private TowerDefenseGame game;

	private Viewport viewport;
	private OrthographicCamera camera;
	private Stage stage;
	private SpriteBatch batch;
	private Table mainTable;

	public Endless(TowerDefenseGame game){
		//set game
		this.game = game;

		//load assets
		EndlessAssets.loadEndless();

		//viewport
		int worldWidth = EndlessAssets.endlessMapProperties.get("width", Integer.class)*EndlessAssets.endlessMapProperties.get("tilewidth", Integer.class);
		int worldHeight = EndlessAssets.endlessMapProperties.get("height", Integer.class)*EndlessAssets.endlessMapProperties.get("tileheight", Integer.class);
		viewport = new FitViewport(worldWidth, worldHeight);

		//set camera stuff
		camera = new OrthographicCamera();
		camera.setToOrtho(false, viewport.getWorldWidth(), viewport.getWorldHeight());

		//stage stuff
		stage = new Stage(viewport);
		mainTable = new Table();
		mainTable.setFillParent(true);
		stage.addActor(mainTable);
		Gdx.input.setInputProcessor(stage);

		//batch stuff
		batch = new SpriteBatch();

		//tilemap stuff
		EndlessAssets.endlessRenderer.setView(camera);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1F, 1F, 1F, 1F);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | GL20.GL_STENCIL_BUFFER_BIT);

		camera.update();
		EndlessAssets.endlessRenderer.getBatch().setProjectionMatrix(camera.combined);
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
		EndlessAssets.disposeEndless();
	}


	//my methods
	private void renderStuff(){
		batch.begin();
		//render background
        batch.draw(EndlessAssets.backgroundTextureRegion, 0, 0, camera.viewportWidth, camera.viewportHeight);
		batch.end();
		EndlessAssets.endlessRenderer.render();
		batch.begin();
		//render other stuff
		batch.end();
	}

	private ImageButton createButton(Drawable drawable){
		ImageButton button = new ImageButton(drawable);
		mainTable.add(button).fill().padBottom(10);
		mainTable.row();
		return button;
	}
}