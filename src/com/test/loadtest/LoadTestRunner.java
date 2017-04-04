/*
package com.test.loadtest;

import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.sampler.HTTPSampler;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.SetupThreadGroup;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
*//** File name   :LoadTestRunner.java
 * Description  : 
 * Date created :13 Sep 2016
 * Author 		:Veera
 *//*
public class LoadTestRunner {
	public static void main(String[] args){
        JMeterUtils.loadJMeterProperties("C:/Programs/jmeter/apache-jmeter-2.13/bin/jmeter.properties");
        JMeterUtils.initLogging();// you can comment this line out to see extra log messages of i.e. DEBUG level
        JMeterUtils.initLocale();
		StandardJMeterEngine jm = new StandardJMeterEngine();
		HashTree hashTree = new HashTree();     

		// HTTP Sampler
		HTTPSampler httpSampler = new HTTPSampler();
		httpSampler.setProtocol("http");
		httpSampler.setDomain("www.google.com");
		httpSampler.setPort(80);
		httpSampler.setPath("/");
		httpSampler.setMethod("GET");

		// Loop Controller
		TestElement loopCtrl = new LoopController();
		((LoopController)loopCtrl).setLoops(1);
		((LoopController)loopCtrl).addTestElement(httpSampler);
		((LoopController)loopCtrl).setFirst(true);

		// Thread Group
		SetupThreadGroup threadGroup = new SetupThreadGroup();
		threadGroup.setNumThreads(1);
		threadGroup.setRampUp(1);
		threadGroup.setSamplerController((LoopController)loopCtrl);

		// Test plan
		TestPlan testPlan = new TestPlan("MY TEST PLAN");

		hashTree.add("testPlan", testPlan);
		hashTree.add("loopCtrl", loopCtrl);
		hashTree.add("threadGroup", threadGroup);
		hashTree.add("httpSampler", httpSampler);       

		Summariser summer = null;
         String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");
         if (summariserName.length() > 0) {
             summer = new Summariser(summariserName);
         }
         // Store execution results into a .jtl file
         String logFile = jmeterHome + slash + "example.jtl";
         ResultCollector logger = new ResultCollector(summer);
         logger.setFilename(logFile);
         testPlan.add(testPlan.getArray()[0], logger);
         
		
		jm.configure(hashTree);
		jm.run();
	}
}
*/