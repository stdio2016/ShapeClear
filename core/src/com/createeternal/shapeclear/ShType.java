package com.createeternal.shapeclear;

public enum ShType{
	red(0),
	yellow(1),
	green(2),
	blue(3),
	pink(4),
	orange(5),
	purple(6),
	cyan(7),
	none(-1);
	final int index;
	public int toInt(){
		return index;
	}
	ShType(int id){
		index=id;
	}
}