package com.createeternal.shapeclear;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ButtonActor extends MyActor {
	private TextureRegion texture, pressedTexture;
	private boolean clicked=false;
	public String text="";
	private ClickListener listener=null;
	public boolean draggable;

	public ButtonActor(){
		setTouchable(Touchable.enabled);
		this.addListener(new InputListener(){
			float firstX,firstY;
			boolean stillDraggable;
			Vector2 pos=new Vector2();
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log("ButtonActor", String.format("touchDown(%s,%f,%f,%d,%d)",""+event,x,y,pointer,button));
				if(button!=Input.Buttons.LEFT || pointer!=0) return false;
				stillDraggable=draggable;
				if(draggable)
				{
					pos.x=x; pos.y=y;
					Vector2 v=localToStageCoordinates(pos);
					firstX=v.x-getX();
					firstY=v.y-getY();
				}
				clicked=true;
				setTexture(pressedTexture);
				return true;
			}
			
			public void touchDragged(InputEvent event, float x, float y, int pointer) {
				//Gdx.app.log("ButtonActor", String.format("touchDragged(%s,%f,%f,%d)",""+event,x,y,pointer));
				if(stillDraggable)
				{
					if(draggable)
					{
						pos.x=x; pos.y=y;
						Vector2 v=localToStageCoordinates(pos);
						setPosition(v.x-firstX,v.y-firstY);
					}
					else
						stillDraggable=false;
				}
				if(x<getWidth() && y<getHeight() && x>0 && y>0){
					setTexture(pressedTexture);
				}
				else{
					setTexture(texture);
				}
			}
			
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				//Gdx.app.log("ButtonActor", "touchUp()");
				clicked=false;
				setTexture(texture);
				if(listener!=null && x<getWidth() && y<getHeight() && x>0 && y>0){
					listener.touchUp(event, x, y, pointer, button);
				}
			}
		});
	}
	
	public void setNormalTexture(TextureRegion tex){
		texture=tex;
		if(!clicked)
			setTexture(texture);
	}
	
	public void setPressedTexture(TextureRegion tex){
		pressedTexture=tex;
		if(clicked)
			setTexture(pressedTexture);
	}

	@Override
	public void draw(Batch batch, float delta){
		super.draw(batch, delta);
		AssetLoader.font.getData().setScale(getScaleX()*0.5f);
		GlyphLayout layout=new GlyphLayout();
		layout.setText(AssetLoader.font, text);
		float width=layout.width;
		float height=layout.height;
		AssetLoader.font.draw(batch, text, getX()+getWidth()*getScaleX()*0.5f-width*0.5f,
				getY()+getHeight()*getScaleY()*0.5f+height*0.5f);
	}
	
	public void addClickListener(ClickListener listener){
		this.listener=listener;
	}
}
