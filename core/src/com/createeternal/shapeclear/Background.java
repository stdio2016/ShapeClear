package com.createeternal.shapeclear;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Background extends Actor {
	public Sprite landscape, portrait;
	private float scale;
	private Sprite background;
	private int screenw,screenh;
	
	public Background()
	{
		landscape=new Sprite(AssetLoader.bg_landscape);
		portrait=new Sprite(AssetLoader.bg_portrait);
	}
	
	public void update(int width,int height){
		screenw=width;
		screenh=height;
		float texRatio;
		if(width>height) // landscape
		{
			background=landscape;
			background.setOrigin(0, 0);
			texRatio=background.getWidth()/background.getHeight();
			float idealWidth=height*texRatio;
			background.setPosition((width-idealWidth)/2f, 0);
			scale=height/background.getHeight();
			background.setScale(scale);
		}
		else // portrait
		{
			background=portrait;
			background.setOrigin(0, 0);
			texRatio=background.getWidth()/background.getHeight();
			float idealHeight=width/texRatio;
			background.setPosition(0, (height-idealHeight)/2f);
			scale=width/background.getWidth();
			background.setScale(scale);
		}
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha){
		if(isLandscape())
		{
			landscape.draw(batch);
		}
		else
		{
			portrait.draw(batch);
		}
	}

	public boolean isLandscape() {
		return screenw>screenh;
	}
	
	public float getScale(){
		return scale;
	}
	
	public int screenWidth(){
		return screenw;
	}
	
	public int screenHeight(){
		return screenh;
	}
	
	public Background moveActor(Actor actor, float x,float y){
		// allow chaining
		float screenx=screenw;
		float screeny=screenh;
		float width=actor.getWidth()*scale;
		float height=actor.getHeight()*scale;
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
		actor.setPosition(realx-width*0.5f, realy-height*0.5f);
		actor.setOrigin(0,0);
		actor.setScale(scale);
		return this;
	}
	public Background moveActor(Actor actor, float x,float y, float anotherscale){
		// allow chaining
		float screenx=screenw;
		float screeny=screenh;
		float width=actor.getWidth()*scale;
		float height=actor.getHeight()*scale;
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
		actor.setPosition(realx-width*0.5f, realy-height*0.5f);
		actor.setOrigin(0,0);
		actor.setScale(scale*anotherscale);
		return this;
	}
}