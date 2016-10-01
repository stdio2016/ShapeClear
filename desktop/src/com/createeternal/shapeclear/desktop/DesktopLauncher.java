package com.createeternal.shapeclear.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;
import com.createeternal.shapeclear.ShapeClearGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		final boolean needToPackTexture=false;
		if(needToPackTexture)
		{
			// for high resolution (1280x720)
			Settings settings=new Settings();
			settings.maxHeight=2048;
			settings.maxWidth=2048;
			settings.filterMin=TextureFilter.MipMapLinearLinear;
			settings.filterMag=TextureFilter.Linear;
			settings.fast=true;
			settings.duplicatePadding=true;
			settings.scale=new float[]{1,0.5f};
			settings.scaleSuffix=new String[]{"e","e_small"};
			TexturePacker.process(settings, "../../images", ".", "gam");
			
			// for lower resolution
			settings.maxHeight=1024;
			settings.maxWidth=1024;
			
			//TexturePacker.process(settings, "../../images_small", ".", "game_small");
		}
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=482;
		config.height=320;
		
		//config.width=320;
		//config.height=480;
		config.useGL30 = false;
		ShapeClearGame game=new ShapeClearGame();
		game.platformSpecific=new DesktopSpecific();
		new LwjglApplication(game, config);
	}
}
