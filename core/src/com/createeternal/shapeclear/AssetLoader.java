package com.createeternal.shapeclear;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.Application.ApplicationType;

public class AssetLoader {
	public static BitmapFont font;
	//private static Texture ;
	public static TextureAtlas texture;
	public static TextureRegion bg_portrait, bg_landscape,
		gameStart, gameStartPressed, bad_tex, shape[][], shapeTexture, board[];
	public static int gridSize;
	private static FreeTypeFontGenerator fontGenerator;
	private static FreeTypeFontParameter fontParam;
	public static String warning="";
	
	public static final String DEFAULT_CHAR="1234567890 "
			+"ABCDEFGHIJKLMNOPQRSTUVWXYZ"+"abcdefghijklmnopqrstuvwxyz"
			+"`~!@#$%^&*()-_=+|[]{};':,./<>?"
			+"\"\\\n\r\t";
	public static String removeDuplicateChar(String str)
	{
		Set<Character> set=new HashSet<Character>();
		for(int i=0;i<str.length();i++)
			set.add(str.charAt(i));
		StringBuilder builder=new StringBuilder();
		for(Character ch: set)
			builder.append(ch);
		return builder.toString();
	}
	
	public static void load()
	{
		if(fontGenerator!=null)
			warning="The static variables are not cleared";
		fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("NotoSansCJKtc-Regular.otf"));
		fontParam = new FreeTypeFontParameter();
		fontParam.characters = removeDuplicateChar(Localize.get("UsedChars")+DEFAULT_CHAR);
		if((Gdx.app.getType()==ApplicationType.Android || true)
			&& Gdx.graphics.getWidth()+Gdx.graphics.getHeight()<1024)
		{
			// to support my brother's Android 2.3 phone
			texture = new TextureAtlas(Gdx.files.internal("game_small.atlas"));
			//shapeTexture=new Texture("shapes_small.png");
			fontParam.size = 36;
			gridSize=32;
		}
		else
		{
			texture = new TextureAtlas(Gdx.files.internal("game.atlas"));
			//shapeTexture=new Texture("shapes.png");
			fontParam.size = 72;
			gridSize=64;
		}
		shapeTexture=texture.findRegion("shapes");
		//font = new BitmapFont();
		font = fontGenerator.generateFont(fontParam);
		font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		bad_tex=texture.findRegion("badlogic");
		bg_portrait=texture.findRegion("background_portrait");
		bg_landscape=texture.findRegion("background_landscape");
		gameStart=texture.findRegion("gameStart");
		gameStartPressed=texture.findRegion("gameStartPressed");
		final int maxx=shapeTexture.getRegionWidth()/gridSize;
		final int maxy=shapeTexture.getRegionHeight()/gridSize;
		
		shape=new TextureRegion[maxx][];
		for(int x=0;x<maxx;x++) {
			shape[x]=new TextureRegion[maxy];
			for(int y=0;y<maxy;y++) {
				shape[x][y]=new TextureRegion(shapeTexture,x*gridSize,y*gridSize,gridSize,gridSize);
			}
		}
		board=new TextureRegion[]{
			texture.findRegion("board1"),texture.findRegion("board2")
		};
	}
	
	public static void addChar(String chars)
	{
		fontParam.characters=removeDuplicateChar(fontParam.characters+chars);
	}
	
	public static void rebuildFont()
	{
		if(font!=null) font.dispose();
		font = fontGenerator.generateFont(fontParam);
		font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}
	
	public static void unload()
	{
		font.dispose();
		texture.dispose();
		fontGenerator.dispose();
	}
}
