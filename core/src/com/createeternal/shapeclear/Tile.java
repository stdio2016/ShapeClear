package com.createeternal.shapeclear;

public class Tile {
	public static final EmptyTile empty = new EmptyTile();
	public static final NoTile none = new NoTile();
	public int x,y;
	public int pos;
	public int speed;
	public int dir;
	public Coord oldpos;
	
	public Tile(){
		oldpos=new Coord(0,0);
	}
	
	public void setSh(ShType sh){
		
	}
	
	public ShType getSh(){
		return ShType.none;
	}
	
	public void moveTo(int newx, int newy) {
		oldpos.x=x;
		oldpos.y=y;
		x=newx;
		y=newy;
	}
	
	public void draw(BoardRenderer rend){
		
	}
}
