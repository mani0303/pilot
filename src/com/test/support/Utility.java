package com.test.support;


public class Utility {
	private static Utility utility;
	private Utility(){};
	public static Utility getInstance(){
		if(utility==null){
			utility= new Utility();
		}
		return utility;
	}
}


