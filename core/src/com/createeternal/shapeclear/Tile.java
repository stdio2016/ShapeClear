package com.createeternal.shapeclear;

public class Tile {
	public static final EmptyTile empty = new EmptyTile();
	public static final NoTile none = new NoTile();
	public int x,y;
	public int pos;
	public int speed;
	public int dir;
	public int oldx,oldy;
	
	public Tile(){
		oldx=0; oldy=0;
	}
	
	public void setSh(ShType sh){
		
	}
	
	public ShType getSh(){
		return ShType.none;
	}
	
	public void moveTo(int newx, int newy) {
		oldx=x;
		oldy=y;
		x=newx;
		y=newy;
	}
	
	public void draw(BoardRenderer rend){
		
	}
}
