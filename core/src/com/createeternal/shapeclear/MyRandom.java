package com.createeternal.shapeclear;

import java.util.Random;

public class MyRandom {
	static Random random=new Random(System.currentTimeMillis());
	
	public static int nextInt(int max){
		return random.nextInt(max);
	}
}
