package com.yuuna.anotherworldtd.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
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
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.yuuna.anotherworldtd.TowerDefenseGame;
import com.yuuna.anotherworldtd.BaseClasses.InputStuff;
import com.yuuna.anotherworldtd.BaseClasses.UserInterface;
import com.yuuna.anotherworldtd.Tools.CoolMethGames;
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

	//ui and input stuff
	private UserInterface ui;
	private InputStuff input;
	
	//managers
	private EntityManager entityManager;

	public Endless(TowerDefenseGame game){
		//set game
		this.game = game;

		//load assets
		EndlessAssets.loadEndless();

		//viewport
		int numOTilesHorizontal = EndlessAssets.endlessMapProperties.get("width", Integer.class);
		int numOTilesVertical = EndlessAssets.endlessMapProperties.get("height", Integer.class);
		int tileWidth = EndlessAssets.endlessMapProperties.get("tilewidth", Integer.class);
		int tileHeight = EndlessAssets.endlessMapProperties.get("tileheight", Integer.class);
		worldWidth = numOTilesHorizontal*tileWidth;
		worldHeight = numOTilesVertical*tileHeight;
		viewport = new StretchViewport(worldWidth, worldHeight);

		//set camera stuff
		camera = new OrthographicCamera();
		camera.setToOrtho(false, viewport.getWorldWidth(), viewport.getWorldHeight());
		// camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		CoolMethGames.cameraInfo(viewport.getWorldWidth(), viewport.getWorldHeight());

		//stage stuff
		stage = new Stage(viewport);
		mainTable = new Table();
		mainTable.setFillParent(true);
		stage.addActor(mainTable);
		
		//batch stuff
		batch = new SpriteBatch();

		//manager stuff
		entityManager = new EntityManager((TiledMapTileLayer) EndlessAssets.endlessMap.getLayers().get(0), EndlessAssets.endlessMapProperties, game, stage);

		//user interface stuff
		ui = new UserInterface(game, stage, entityManager, EndlessAssets.endlessMapProperties, true);
		input = new InputStuff(entityManager, EndlessAssets.endlessMapProperties);

		//tilemap stuff
		EndlessAssets.endlessRenderer.setView(camera);

		//input
		InputMultiplexer im = new InputMultiplexer(stage, input);
		Gdx.input.setInputProcessor(im);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1F, 1F, 1F, 1F);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | GL20.GL_STENCIL_BUFFER_BIT);

		camera.update();
		EndlessAssets.endlessRenderer.getBatch().setProjectionMatrix(camera.combined);
		batch.setProjectionMatrix(camera.combined);

		entityManager.update(delta);
		renderStuff(delta);

		stage.act(Gdx.graphics.getDeltaTime());
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
		UserInterfaceAssets.disposeUserInterface();
		entityManager.dispose();
	}


	//my methods
	private void renderStuff(float delta){
		batch.begin();
		//render background
        batch.draw(EndlessAssets.backgroundTextureRegion, 0, 0, camera.viewportWidth, camera.viewportHeight);
		batch.end();
		EndlessAssets.endlessRenderer.render();
		batch.begin();
			entityManager.render(batch);
		batch.end();
	}

	private ImageButton createButton(Drawable drawable){
		ImageButton button = new ImageButton(drawable);
		mainTable.add(button).fill().padBottom(10);
		mainTable.row();
		return button;
	}
}
