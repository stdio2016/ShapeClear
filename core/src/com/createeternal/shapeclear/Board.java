package com.createeternal.shapeclear;

public class Board {
	private Shape shapes[][];
	public Shape getOj(int x,int y){
		return shapes[x][y];
	}
	
	public Board(int width,int height){
		shapes=new Shape[width][height];
		for(int x=0;x<9;x++)
		{
			for(int y=0;y<9;y++)
			{
				shapes[x][y]=new Shape();
				shapes[x][y].moveTo(x, y);
				shapes[x][y].setSh(MyRandom.nextInt(8));
			}
		}
	}
	
	public void setOj(int x,int y,Shape shape){
		shape.moveTo(x,y);
		shapes[x][y]=shape;
		shapes[x][y].setSh(ShapeType.red);
	}
}
