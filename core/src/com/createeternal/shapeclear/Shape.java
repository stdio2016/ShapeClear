package com.createeternal.shapeclear;

public class Shape extends Tile {
	private ShType sh;
	
	
	public Shape()
	{
		
	}

	public void setSh(ShType newSh) {
		sh=newSh;
	}
	
	public ShType getSh(){
		return sh;
	}

}
