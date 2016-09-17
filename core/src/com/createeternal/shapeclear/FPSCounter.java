package com.createeternal.shapeclear;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class FPSCounter extends Actor {
	private float timer=0;
	private int FPSCount=0;
	public int fps=0;
	@Override
	public void draw(Batch batch,float parentAlpha){
		
		AssetLoader.font.getData().setScale(getScaleX());
		timer+=Gdx.graphics.getDeltaTime();
		FPSCount++;
		
		String text="FPS: "+fps;
		GlyphLayout layout=new GlyphLayout();
		layout.setText(AssetLoader.font, text);
		float height=layout.height;
		AssetLoader.font.draw(batch, text, 0, height);
		
		if(FPSCount>=60)
		{
			fps=(int) ((float)FPSCount/timer);
			timer=0;
			FPSCount=0;
		}
	}
}
