package com.createeternal.shapeclear;

import java.util.Random;

import com.badlogic.gdx.utils.TimeUtils;;

public class MyRandom {
	static Random random=new Random(TimeUtils.millis());
	
	public static int nextInt(int max){
		return random.nextInt(max);
	}
}
