package com.yuuna.anotherworldtd.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.yuuna.anotherworldtd.TowerDefenseGame;
import com.yuuna.anotherworldtd.BaseClasses.UserInterface;
import com.yuuna.anotherworldtd.Tools.EntityManager;
import com.yuuna.anotherworldtd.Tools.AssetManager.EndlessAssets;
import com.yuuna.anotherworldtd.Tools.AssetManager.UserInterfaceAssets;
import com.yuuna.anotherworldtd.Tools.EntityManager.AllySelection;

public class Endless extends ScreenAdapter {
	private TowerDefenseGame game;

	//game variables
	private int worldWidth;
	private int worldHeight;
	private Viewport viewport;
	private OrthographicCamera camera;
	private Stage stage;
	private SpriteBatch batch;
	private Table mainTable;
	
	//managers
	private EntityManager entityManager;

	public Endless(TowerDefenseGame game){
		//set game
		this.game = game;

		//load assets
		EndlessAssets.loadEndless();

		//viewport
		worldWidth = EndlessAssets.endlessMapProperties.get("width", Integer.class)*EndlessAssets.endlessMapProperties.get("tilewidth", Integer.class);
		worldHeight = EndlessAssets.endlessMapProperties.get("height", Integer.class)*EndlessAssets.endlessMapProperties.get("tileheight", Integer.class);
		viewport = new FitViewport(worldWidth, worldHeight);

		//set camera stuff
		camera = new OrthographicCamera();
		camera.setToOrtho(false, viewport.getWorldWidth(), viewport.getWorldHeight());
		// camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		//stage stuff
		stage = new Stage(viewport);
		mainTable = new Table();
		mainTable.setFillParent(true);
		stage.addActor(mainTable);
		Gdx.input.setInputProcessor(stage);

		//user interface stuff
		new UserInterface(game, stage, worldWidth, worldHeight, true);

		//batch stuff
		batch = new SpriteBatch();

		//tilemap stuff
		EndlessAssets.endlessRenderer.setView(camera);

		//manager stuff
		entityManager = new EntityManager((TiledMapTileLayer) EndlessAssets.endlessMap.getLayers().get(0), game, stage);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1F, 1F, 1F, 1F);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | GL20.GL_STENCIL_BUFFER_BIT);

		camera.update();
		EndlessAssets.endlessRenderer.getBatch().setProjectionMatrix(camera.combined);
		batch.setProjectionMatrix(camera.combined);

		renderStuff(delta);

		stage.draw();
	}

	@Override
	public void show() {
		entityManager.createAlly(AllySelection.mageAlly, 4.5f, 9.5f);
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
		UserInterfaceAssets.disposeUserInterface();
	}


	//my methods
	private void renderStuff(float delta){
		batch.begin();
		//render background
        batch.draw(EndlessAssets.backgroundTextureRegion, 0, 0, camera.viewportWidth, camera.viewportHeight);
		batch.end();
		EndlessAssets.endlessRenderer.render();
		batch.begin();
			entityManager.render(batch, delta);
		batch.end();
	}

	private ImageButton createButton(Drawable drawable){
		ImageButton button = new ImageButton(drawable);
		mainTable.add(button).fill().padBottom(10);
		mainTable.row();
		return button;
	}
}
