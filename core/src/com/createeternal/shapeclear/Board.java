package com.createeternal.shapeclear;

public class Board {
	public Tile oj[][];
	private int width,height;
	public int camw, camh;
	private boolean win;
	
	private ShType allowColors[]={
		ShType.red,ShType.yellow,ShType.green,ShType.blue,
		ShType.pink,ShType.orange,ShType.purple,ShType.cyan
	};
	
	// note: x and y indexes are 0 based
	public Tile getOj(int x,int y){
		return oj[x][y];
	}
	
	// use this
	public void setOj(int x,int y,Tile shape){
		shape.moveTo(x,y);
		oj[x][y]=shape;
	}
	
	public Board(int width,int height){
		this.width=width;
		this.height=height;
		oj=new Tile[width][height];
		for(int x=0;x<9;x++)
		{
			for(int y=0;y<9;y++)
			{
				Tile s=new Shape(allowColors[MyRandom.nextInt(8)]);
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

	public boolean isWin(){
		return win;
	}
	
	public void doOneStep() {
		fall();
		match();
	}
	
	public int posPerGrid=250;
	public int accel=1;
	public int maxSpeed=posPerGrid/2;
	
	public void switchLight(int xi,int yi){
		switchOpen(xi,yi);
		switchOpen(xi,yi+1);
		switchOpen(xi,yi-1);
		switchOpen(xi+1,yi);
		switchOpen(xi-1,yi);
		boolean flg=true;
		for(int i=0;i<getW();i++)
			for(int j=0;j<getH();j++)
				if(!(oj[i][j].getSh()==ShType.red))
					flg=false;
		if(flg) win=true;
	}
	
	private void switchOpen(int x,int y) {
		if(x>=getW() || x<0) return;
		if(y>=getH() || y<0) return;
		Tile s=getOj(x,y);
		if(s.getSh()==ShType.none)
			setOj(x,y,new Shape(ShType.red));
		else
			setOj(x,y,Tile.none);
	}
	
	private void fall(){
		for(int x=0;x<getW();x++)
		{
			for(int y=0;y<getH()-1;y++)
			{
				Tile s=getOj(x,y);
				if(s.getSh()==ShType.none){
					setOj(x,y,getOj(x,y+1));
					setOj(x,y+1,Tile.empty);
				}
			}
			if(getOj(x,height-1).getSh()==ShType.none){
				setOj(x,height-1,new Shape(allowColors[MyRandom.nextInt(8)]));
			}
		}
	}
	
	private void match(){
		for(int x=0;x<9;x++)
		{
			for(int y=0;y<7;y++)
			{
				ShType sh1=getOj(x,y).getSh();
				ShType sh2=getOj(x,y+1).getSh();
				ShType sh3=getOj(x,y+2).getSh();
				if(sh1!=ShType.none && sh1==sh2 && sh1==sh3){
					setOj(x,y,Tile.empty);
					setOj(x,y+1,Tile.empty);
					setOj(x,y+2,Tile.empty);
					y+=3;
					while(y<9){
						sh3=getOj(x,y).getSh();
						if(sh1==sh3){
							setOj(x,y,Tile.empty);
							y++;
						}
						else break;
					}
				}
			}
		}
		for(int y=0;y<9;y++)
		{
			for(int x=0;x<7;x++)
			{
				ShType sh1=getOj(x,y).getSh();
				ShType sh2=getOj(x+1,y).getSh();
				ShType sh3=getOj(x+2,y).getSh();
				if(sh1!=ShType.none && sh1==sh2 && sh1==sh3){
					setOj(x,y,Tile.empty);
					setOj(x+1,y,Tile.empty);
					setOj(x+2,y,Tile.empty);
					x+=3;
					while(x<9){
						sh3=getOj(x,y).getSh();
						if(sh1==sh3){
							setOj(x,y,Tile.empty);
							x++;
						}
						else break;
					}
				}
			}
		}
	}
	
	// TODO: setter for height/width
	
}
