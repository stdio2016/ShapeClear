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
	
	@Override
	public void setSh(ShType newSh) {
		sh=newSh;
	}
	
	@Override
	public ShType getSh(){
		return sh;
	}

	@Override
	public void draw(BoardRenderer rend){
		if(sh==ShType.none)
			return;
		rend.drawOn(AssetLoader.shape[sh.toInt()][1],this);
	}
}
