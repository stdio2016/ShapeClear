package com.createeternal.shapeclear;

public class Shape {
	private int sh;
	private int x,y;
	
	public Shape()
	{
		
	}

	public void setSh(int newSh) {
		sh=newSh;
	}
	
	public int getSh(){
		return sh;
	}

	public void moveTo(int newx, int newy) {
		newx=x;
		newy=y;
	}
}
