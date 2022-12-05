package util;

import java.util.Random;

public class Base {
	
	public int generateRandomNum(int num)
	{
		Random rn = new Random();
		int randomNum = rn.nextInt(num);
		return randomNum;
	}

}
