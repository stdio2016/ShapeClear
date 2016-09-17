package com.createeternal.shapeclear;

public class Tile {
	public int x,y;
	
	public void setSh(ShType sh){
		
	}
	
	public ShType getSh(){
		return ShType.none;
	}
	
	public void moveTo(int newx, int newy) {
		x=newx;
		y=newy;
	}
}
