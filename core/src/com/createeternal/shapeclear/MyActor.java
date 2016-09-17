package com.createeternal.shapeclear;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class MyActor extends Actor {
	private TextureRegion it;
	
	public MyActor(TextureRegion sprite)
	{
		setTexture(sprite);
		this.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log("MyActor", String.format("touchDown(%s,%f,%f,%d,%d)",""+event,x,y,pointer,button));
				if(button!=0) return false;
				return true;
			}
		});
	}
	
	public MyActor()
	{
		it=null;
	}
	
	public void setTexture(TextureRegion tex){
		it=tex;
		setWidth(it.getRegionWidth());
		setHeight(it.getRegionHeight());
	}
	
	@Override
	public void draw(Batch batch,float parentAlpha){
		if(it!=null)
		{
			batch.draw(it, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
		}
	}
	
}
