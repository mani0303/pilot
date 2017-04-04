package com.test.pageobjects;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;

import com.test.core.RunnerHelper;
import com.test.report.Logger;
import com.test.support.Identifier;

public class PageSupporter {
	public Logger log;
	WebDriver driver;
	public HashMap<String, String> data;
	Identifier identifier = Identifier.getInstance();
	public PageSupporter(RunnerHelper helper) {
		log = helper.log;
		driver=helper.driver;
		data=helper.testData;
	}
}
