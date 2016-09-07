package com.createeternal.shapeclear;

import java.util.Locale;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.I18NBundle;

public class Localize {
	public static I18NBundle localize;
	
	public static void setLanguage(Locale locale)
	{
		FileHandle baseFileHandle = Gdx.files.internal("locale/locale");
		localize = I18NBundle.createBundle(baseFileHandle, locale);
	}
	
	public static String get(String key){
		return localize.get(key);
	}
}
