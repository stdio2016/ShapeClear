package com.createeternal.shapeclear;

public class Board {
	public Shape oj[][];
	private int width,height;
	public int camw, camh;
	private ShType allowColors[]={ShType.red,ShType.yellow,ShType.green,ShType.blue,
			ShType.pink,ShType.orange,ShType.purple,ShType.cyan};
	
	// note: x and y indexes are 0 based
	public Shape getOj(int x,int y){
		return oj[x][y];
	}
	
	// use this
	public void setOj(int x,int y,Shape shape){
		shape.moveTo(x,y);
		oj[x][y]=shape;
	}
	
	public Board(int width,int height){
		this.width=width;
		this.height=height;
		oj=new Shape[width][height];
		for(int x=0;x<9;x++)
		{
			for(int y=0;y<9;y++)
			{
				Shape s=new Shape();
				s.setSh(allowColors[MyRandom.nextInt(8)]);
				setOj(x,y,s);
			}
		}
		camw=width>9?9:width;
		camh=height>9?9:height;
	}
	
	public int getH(){
		return height;
	}
	
	public int getW(){
		return width;
	}
	
	// TODO: setter for height/width
	
}
