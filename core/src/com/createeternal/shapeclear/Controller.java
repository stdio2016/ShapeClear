package com.createeternal.shapeclear;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Controller extends InputListener {
	BoardRenderer renderer;
	Board board;

	public Controller(BoardRenderer boardRenderer) {
		// TODO Auto-generated constructor stub
		renderer=boardRenderer;
		board=renderer.getBoard();
	}
	
	@Override
	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
		Coord pos=renderer.convertMousePos(x, y);
		if(pos.x>=0 && pos.x<board.getW() && pos.y>=0 && pos.y<board.getH()); else return false;
		Gdx.app.log("Controller", String.format("touchDown(%s,%d,%d,%d,%d)",""+event,pos.x,pos.y,pointer,button));
		int xi=pos.x,yi=pos.y;
		board.switchLight(xi, yi);
		return true;
	}
	
	@Override
	public void touchDragged(InputEvent event, float x, float y, int pointer) {
		Coord pos=renderer.convertMousePos(x, y);
		//if(pos.x>=0 && pos.x<board.getW() && pos.y>=0 && pos.y<board.getH())
		//	board.oj[pos.x][pos.y].setSh(ShType.none);
	}
	
	@Override
	public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
		
	}
}
