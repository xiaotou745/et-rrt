package com.renrentui.renrencore.util;

import java.util.Random;

public class RandomCodeStrGenerator {
	private final static char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J',  
        'K', 'L', 'M', 'N',  'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',  
        'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9' }; 
	
	private final static char[] codeNumber = {'0', '1','2', '3', '4', '5', '6', '7', '8', '9' }; 
	
	public static String generateCode(int count){
		Random random = new Random();
		String sRand = "";
		for (int i = 0; i < count; i++) {
			String rand = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);//String.valueOf(random.nextInt(10));  
	        sRand  += rand;
		}
		return sRand;
	}
	
	/**
	 * 数字随机数
	 * */
	public static String generateCodeNum(int count){
		Random random = new Random();
		String sRand = "";
		for (int i = 0; i < count; i++) {
			String rand = String.valueOf(codeNumber[random.nextInt(codeNumber.length)]);//String.valueOf(random.nextInt(10));  
	        sRand  += rand;
		}
		return sRand;
	}
}
