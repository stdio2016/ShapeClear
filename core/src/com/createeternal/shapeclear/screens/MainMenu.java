package com.createeternal.shapeclear.screens;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.createeternal.shapeclear.*;

public class MainMenu implements Screen {
	private final ShapeClearGame game;
	private Stage stage;
	private ButtonActor startButton, helpButton
		, creditsButton;
	private String message;
	private boolean receivedMessage;
	
	public MainMenu(final ShapeClearGame game)
	{
		this.game=game;
		stage=game.getStage();
		
		startButton=new ButtonActor();
		startButton.setNormalTexture(AssetLoader.gameStart);
		startButton.setPressedTexture(AssetLoader.gameStartPressed);
		startButton.text=Localize.get("startGame");
		startButton.addClickListener(new ClickListener(){
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				game.switchToGameScreen();
			}
		});
		helpButton=new ButtonActor();
		helpButton.setNormalTexture(AssetLoader.gameStart);
		helpButton.setPressedTexture(AssetLoader.gameStartPressed);
		helpButton.text=Localize.get("help");
		helpButton.addClickListener(new ClickListener(){
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				
			}
		});
		creditsButton=new ButtonActor();
		creditsButton.setNormalTexture(AssetLoader.gameStart);
		creditsButton.setPressedTexture(AssetLoader.gameStartPressed);
		creditsButton.text=Localize.get("credits");
		
		message=Localize.get("shapeClear");
	}
	
	class MyInputProcessor extends InputAdapter{
		@Override
		public boolean keyUp(int keycode) {
			if(keycode==Keys.ESCAPE)
			{
				Gdx.app.log("MainMenu", "quit");
				if(Gdx.app.getType()!=ApplicationType.iOS)
					Gdx.app.exit();
				return true;
			}
			return false;
		}
	}
	
	@Override
	public void show() {
		
		Gdx.app.log("MainMenu", "show()");
		Gdx.input.setCatchBackKey(false);
		//resize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		Gdx.input.setInputProcessor(new InputMultiplexer(new MyInputProcessor(),stage));
		stage.clear();
		stage.addActor(game.background);
		stage.addActor(startButton);
		stage.addActor(helpButton);
		stage.addActor(creditsButton);
		stage.addActor(game.fpsCounter);
	}

	@Override
	public void render(float delta) {
		float scale=game.background.getScale();
		Batch batch=game.getBatch();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClearDepthf(1f);
		Gdx.gl.glClear(GL20.GL_DEPTH_BUFFER_BIT | GL20.GL_COLOR_BUFFER_BIT);
		
		Gdx.gl.glDisable(GL20.GL_DEPTH_TEST);
		
		stage.act(delta);
		stage.draw();
		batch.begin();
		
		if(receivedMessage)
		{
			AssetLoader.addChar(message);
			AssetLoader.rebuildFont();
			if(message=="exit")
				game.setScreen(new MainMenu(game));
			receivedMessage=false;
		}
		AssetLoader.font.setColor(Color.BLACK);
		AssetLoader.font.getData().setScale(scale*1);
		float height;
		float ratio=(float)game.background.screenHeight()/game.background.screenWidth();
		if(ratio>16f/9f) ratio=16f/9f;
		if(ratio>1)
			height=0.55f+0.25f*ratio;
		else
			height=0.8f;
		drawTextCentered(batch,message,0.5f,height);

		AssetLoader.font.getData().setScale(scale*0.5f);

		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		float ratio=(float)width/(float)height;
		if(ratio>0.75)
		{
			game.background
			.moveActor(startButton, 0.5f, 0.5f)
			.moveActor(helpButton, 0.3f, 0.3f)
			.moveActor(creditsButton, 0.7f, 0.3f);
		}
		else
		{
			game.background
			.moveActor(startButton, 0.5f, 0.5f)
			.moveActor(helpButton, 0.5f, 0.35f)
			.moveActor(creditsButton, 0.5f, 0.2f);
		}
	}

	private Vector2 getScaledPos(float x,float y)
	{
		float screenx=Gdx.graphics.getWidth();
		float screeny=Gdx.graphics.getHeight();
		float realx,realy;
		if(screenx>screeny)
		{
			realy=screeny*y;
			realx=screenx*0.5f+screeny*(x-0.5f);
		}
		else
		{
			realx=screenx*x;
			realy=screeny*0.5f+screenx*(y-0.5f);
		}
		return new Vector2(realx,realy);
	}
	
	private void drawTextCentered(Batch batch, String text, float x, float y)
	{
		GlyphLayout layout=new GlyphLayout();
		layout.setText(AssetLoader.font, text);
		Vector2 pos=getScaledPos(x,y);
		float width=layout.width;
		float height=layout.height;
		AssetLoader.font.draw(batch, text, pos.x-width*0.5f, pos.y+height*0.5f);
	}
	
	@Override
	public void pause() {
		Gdx.app.log("MainMenu", "pause()");
	}

	@Override
	public void resume() {
		Gdx.app.log("MainMenu", "resume()");
	}

	@Override
	public void hide() {
		Gdx.app.log("MainMenu", "hide()");
	}

	@Override
	public void dispose() {
		Gdx.app.log("MainMenu", "dispose()");
	}
}
