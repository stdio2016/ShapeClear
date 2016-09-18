package com.createeternal.shapeclear.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.createeternal.shapeclear.AssetLoader;
import com.createeternal.shapeclear.Board;
import com.createeternal.shapeclear.BoardRenderer;
import com.createeternal.shapeclear.ButtonActor;
import com.createeternal.shapeclear.Localize;
import com.createeternal.shapeclear.ShapeClearGame;
import com.badlogic.gdx.Input.Keys;

public class GameScreen implements Screen {

	ShapeClearGame game;
	ButtonActor startb;
	Board board;
	BoardRenderer boardRenderer;
	Stage stage;
	
	class MyInputProcessor extends InputAdapter {
		@Override
		public boolean keyUp(int keycode) {
			if(keycode==Keys.ESCAPE || keycode==Keys.BACK)
			{
				Gdx.app.log("GameScreen", "quit");
				game.switchToMainMenu();
				return true;
			}
			return false;
		}
	}
	
	public GameScreen(ShapeClearGame shapeClearGame) {
		game=shapeClearGame;
		stage=game.getStage();
		
		startb=new ButtonActor();
		startb.setNormalTexture(AssetLoader.gameStart);
		startb.setPressedTexture(AssetLoader.gameStartPressed);
		startb.text=Localize.get("test001");
		startb.draggable=true;

		startb.addClickListener(new ClickListener(){
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				doUpdate=!doUpdate;
				boardRenderer.flashing=!doUpdate;
			}
		});
	}

	@Override
	public void show() {
		Gdx.app.log("GameScreen", "show()");
		Gdx.input.setCatchBackKey(true);
		Gdx.input.setInputProcessor(new InputMultiplexer(new MyInputProcessor(),stage));
		
		board=new Board(9,9);
		boardRenderer=new BoardRenderer(board);
		stage.clear();
		stage.addActor(game.background);
		stage.addActor(boardRenderer);
		stage.addActor(startb);
		stage.addActor(game.fpsCounter);
		doUpdate=false;
	}

	boolean doUpdate;
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta>0.1f ? 0.1f : delta);
		if(doUpdate)
			board.doOneStep();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log("GameScreen", String.format("resize(%d,%d)", width,height));
		game.background.moveActor(startb, 0.5f, 0.5f);
	}

	@Override
	public void pause() {
		Gdx.app.log("GameScreen", "pause()");
	}

	@Override
	public void resume() {
		Gdx.app.log("GameScreen", "resume()");
	}

	@Override
	public void hide() {
		Gdx.app.log("GameScreen", "hide()");
	}

	@Override
	public void dispose() {
		Gdx.app.log("GameScreen", "dispose()");
	}
	
}
