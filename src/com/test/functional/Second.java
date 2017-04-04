package com.test.functional;

import com.test.core.RunnerHelper;
import com.test.core.TestRunner;
import com.test.support.Status;

public class Second extends TestRunner{

	public Second(RunnerHelper helper) {
		super(helper);
	}
	@Test(description= "This is from Second Class")
	public void openGooglePage(){
		try{
			log.log("This is from Second Class", Status.INFO);			
		}catch(Exception e){
			log.log("This is from Second Class "+e.getMessage(),Status.FAIL);
		}
	}
}
