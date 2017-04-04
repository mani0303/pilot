/*package com.test.core;

import java.io.FileOutputStream;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;

public class Launch {

	public static void main(String[] args) {
		static final Logger logger = LogManager.getLogger(Logger.class.getName());
		BrowserMobProxy proxy = new BrowserMobProxyServer();
		proxy.start(0);
		// get the JVM-assigned port and get to work!
		int port = proxy.getPort();
		Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
		seleniumProxy.setSslProxy("trustAllSSLCertificates");
		// configure it as a desired capability
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
		String chromeDriver="C:\\Users\\450405\\.jenkins\\workspace\\TestAutomation\\resources\\driver-exe\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromeDriver);
		WebDriver wd = new ChromeDriver();
		// enable more detailed HAR capture, if desired (see CaptureType for the complete list)
		proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
		// create a new HAR with the label "yahoo.com"
		proxy.newHar("yahoo.com");
		wd.get("http://yahoo.come");
		// get the HAR data
		Har har = proxy.getHar();
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("C:\\Users\\450405\\Desktop\\yahoo.har");
			har.writeTo(fos);
			proxy.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
*/