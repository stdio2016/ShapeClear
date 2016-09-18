package com.createeternal.shapeclear;

public class Shape extends Tile {
	private ShType sh;
	
	public Shape()
	{
		super();
		sh=ShType.none;
	}
	
	public Shape(ShType sh)
	{
		super();
		this.sh=sh;
	}
	
	public void setSh(ShType newSh) {
		sh=newSh;
	}
	
	public ShType getSh(){
		return sh;
	}

}
