package com.createeternal.shapeclear;

import java.util.Locale;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.createeternal.shapeclear.screens.GameScreen;
import com.createeternal.shapeclear.screens.MainMenu;

public class ShapeClearGame extends Game {
	public PlatformSpecific platformSpecific;
	private Stage stage;
	private Batch batch;
	private MainMenu mainMenu;
	private GameScreen gameScreen;
	private ScreenViewport viewport;

	@Override
	public void create () {
		Gdx.app.log("ShapeClearGame", "create()");
		Localize.setLanguage(Locale.getDefault());
		AssetLoader.load();
		
		viewport=new ScreenViewport();
		stage=new Stage(viewport);
		resize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		
		mainMenu=new MainMenu(this);
		gameScreen=new GameScreen(this);
		setScreen(mainMenu);
	}

	@Override
	public void dispose () {
		Gdx.app.log("ShapeClearGame", "dispose()");
		super.dispose();
		stage.dispose();
		AssetLoader.unload();
	}
	
	@Override
	public void render()
	{
		super.render();
	}
	
	@Override 
	public void resize(int width,int height){
		batch=stage.getBatch();
		viewport.update(width, height,true);
		super.resize(width, height);
	}
	
	public Stage getStage(){
		return stage;
	}
	
	public Batch getBatch(){
		return batch;
	}

	public void switchToGameScreen() {
		setScreen(gameScreen);
	}

	public void switchToMainMenu() {
		setScreen(mainMenu);
	}
	
}
