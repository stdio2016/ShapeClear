package com.createeternal.shapeclear;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BoardRenderer extends Actor {
	private Board board;
	private Controller controller;
	public BoardRenderer(Board board) {
		this.board=board;
		controller=new Controller(this);
		addListener(controller);
	}
	private float startX, startY;
	private float gridW, gridH;
	
	private float timer=0;
	private final float flashTime=1f;
	@Override
	public void draw(Batch batch,float alpha){
		currentBatch=batch;
		scaleBoard();
		timer+=Gdx.graphics.getDeltaTime()/flashTime;
		timer=(float) (timer-Math.floor(timer));
		float light;
		if(timer>0.5)
			light=2-timer*2;
		else
			light=timer*2;
		
		batch.setColor(new Color(1,1,1,0.8f));
		for(int x=0;x<board.getW();x++){
			for(int y=0;y<board.getH();y++){
				batch.draw(AssetLoader.board[0], startX+x*gridW, startY+y*gridH, gridW*1.02f, gridH*1.02f);
			}
		}
		for(int x=0;x<board.getW();x++){
			for(int y=0;y<board.getH();y++){
				currentShape=board.oj[x][y];
				if(currentShape.getSh()!=ShType.none)
				{
					batch.setColor(new Color(1,1,1,1));
					draw(AssetLoader.shape[currentShape.getSh().toInt()][1],0,0);
					batch.setColor(new Color(1,1,1,light));
					draw(AssetLoader.shape[currentShape.getSh().toInt()][0],0,0);
				}
			}
		}

		batch.setColor(new Color(1,1,1,1));
		currentBatch=null;
	}
	
	private final static float gridSize=70;
	
	private void scaleBoard() {
		float screenw=Gdx.graphics.getWidth();
		float screenh=Gdx.graphics.getHeight();
		
		float aspect=screenw/screenh;
		if(aspect>1) {
			if(aspect>4f/3){
				gridW=gridH=gridSize*(screenh/720);
				startY=screenh/2;
				startY-=gridH*4.5;
				if(aspect>16f/9)
				{
					startX=screenw-(screenw-screenh*16f/9)*0.5f;
					startX-=gridW*9.5;
				}
				else
				{
					startX=screenw;
					startX-=gridW*9.5;
				}
			}
			else{
				gridW=gridH=gridSize*(screenw*3f/4f/720);
				startY=screenh/2;
				startY-=gridH*4.5;
				startX=screenw;
				startX-=gridW*9.5;
			}
		}
		else {
			if(aspect<3f/4){
				gridW=gridH=gridSize*(screenw/720);
				startX=screenw/2;
				startX-=gridW*4.5;
				if(aspect<9f/16f)
				{
					startY=screenh*0.5f;
					startY-=gridH*4;
				}
				else
				{
					startY=screenh*0.5f;
					startY-=gridH*4;
				}
			}
			else{
				gridW=gridH=70*(screenh*3f/4f/720);
				startX=screenw/2;
				startX-=gridW*4.5;
				startY=screenh*0.5f;
				startY-=gridH*4;
			}
		}
		this.setPosition(startX, startY);
		this.setSize(gridW*board.camw,gridH*board.camh);
	}
	public Board getBoard() {
		return board;
	}
	
	public Coord convertMousePos(float x,float y){
		return new Coord((int)Math.floor(x/gridW),(int)Math.floor(y/gridH));
	}
	
	private Shape currentShape;
	private Batch currentBatch;
	
	public void draw(TextureRegion texture,float x,float y){
		if(currentBatch==null || currentShape==null)
			throw new IllegalStateException("No shape to draw");
		float shx=startX+currentShape.x*gridW;
		float shy=startY+currentShape.y*gridH;
		currentBatch.draw(texture,shx,shy,gridW,gridH);
	}
}