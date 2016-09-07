package com.createeternal.shapeclear;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.createeternal.shapeclear.ShapeClearGame;

public class AndroidLauncher extends AndroidApplication implements PlatformSpecific {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		ShapeClearGame game=new ShapeClearGame();
		game.platformSpecific=this;
		initialize(game, config);
	}
}
