package com.test.functional;

import com.test.core.RunnerHelper;
import com.test.core.TestRunner;

public class First extends TestRunner{	

	public First(RunnerHelper helper) {
		super(helper);
	}
	
	@Test(description= "Organic flow for small businesses")
	public void openGooglePage(){
		lp.enterURLforTest();
		lp.updateZipAndUtility();
		erp.selectEnergyPlanAndClickSignUp();
		cldp.fillInCompanyInfo();
		cldp.fillInServiceDetails();
		cldp.submitReviewAndConfirm();
		rcs.reviewAndConfirmSignUp();
		rcs.validateSignUpConfirmation();
	}
	
}
