package com.createeternal.shapeclear.screens;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.createeternal.shapeclear.AssetLoader;
import com.createeternal.shapeclear.Background;
import com.createeternal.shapeclear.Board;
import com.createeternal.shapeclear.ButtonActor;
import com.createeternal.shapeclear.Localize;
import com.createeternal.shapeclear.MyActor;
import com.createeternal.shapeclear.MyRandom;
import com.createeternal.shapeclear.Shape;
import com.createeternal.shapeclear.ShapeClearGame;
import com.badlogic.gdx.Input.Keys;

public class GameScreen implements Screen {

	ShapeClearGame game;
	Background background;
	ButtonActor startb;
	Board board;
	Stage stage;
	Label label1,label2;
	TextField field;
	
	class MyInputProcessor extends InputAdapter {
		@Override
		public boolean keyDown(int keycode) {
			if(keycode==Keys.ESCAPE)
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
		background=new Background();
		
		startb=new ButtonActor();
		startb.setNormalTexture(AssetLoader.gameStart);
		startb.setPressedTexture(AssetLoader.gameStartPressed);
		startb.text=Localize.get("test001");
		startb.draggable=true;
		
		Skin skin=new Skin();
		LabelStyle labelStyle=new Label.LabelStyle();
		labelStyle.font=AssetLoader.font;
		labelStyle.fontColor=Color.BLUE;
		skin.add("default", labelStyle);
		TextFieldStyle fieldStyle=new TextFieldStyle();
		fieldStyle.font=AssetLoader.font;
		fieldStyle.cursor=new NinePatchDrawable(new NinePatch(AssetLoader.bad_tex,1,1,1,1));
		fieldStyle.background=new NinePatchDrawable(new NinePatch(AssetLoader.bg_landscape,0,0,0,0));
		fieldStyle.selection=new TextureRegionDrawable(AssetLoader.gameStartPressed);
		fieldStyle.fontColor=Color.RED;
		skin.add("default", fieldStyle);
		label1 = new Label("This text is scaled 2x.", skin);
		label1.setFontScale(2);
		label2 = new Label(
			"This text is scaled. This text is scaled. This text is scaled. This text is scaled. This text is scaled. ", skin);
		label2.setWrap(true);
		label2.setFontScale(0.75f, 0.75f);
		label1.setPosition(200, 65);
		field=new TextField("Hello!stdio2016",skin);
		if(!AssetLoader.warning.equals(""))
			field.setText(AssetLoader.warning);
		field.setMaxLength(5);

	}

	@Override
	public void show() {
		Gdx.app.log("GameScreen", "show()");
		Gdx.input.setCatchBackKey(true);
		Gdx.input.setInputProcessor(new InputMultiplexer(new MyInputProcessor(),stage));
		
		board=new Board(9,9);
		stage.clear();
		stage.addActor(background);
		stage.addActor(startb);

		stage.addActor(label1);
		stage.addActor(label2);
		stage.addActor(field);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta>0.1f ? 0.1f : delta);
		/*for(int x=0;x<9;x++)
		{
			for(int y=0;y<9;y++)
			{
				// 64/720   360-256/720
				background.moveActor(myActors[x][y],(360-256+x*64)/720f,
					(360-256+y*64)/720f)
				.moveActor(myActors2[x][y], (360-256+x*64)/720f,
					(360-256+y*64)/720f);
				myActors[x][y].setScale(myActors[x][y].getScaleX()*1.02f);
			}
		}*/
		field.invalidate();
		field.setPasswordMode(true);
		field.setPasswordMode(false);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log("GameScreen", String.format("resize(%d,%d)", width,height));
		background.update(width, height);
		field.setSize(999,background.getScale()*48);
		background.moveActor(startb, 0.5f, 0.5f)
		.moveActor(background, 1/2, 1/2);
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
