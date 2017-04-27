package vocaltec.test;


import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import io.appium.java_client.android.AndroidDriver;
import utility.Log;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class StartApplication {
 
		private static AndroidDriver driver;
						
		@BeforeTest
			public void setUp() throws MalformedURLException {
			DOMConfigurator.configure("log4j.xml");
			
			//File app = installApp();
			
			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.BROWSER_NAME, "Android");
			capabilities.setCapability("deviceName", "4d00355be00d910f"); // GT-I9500 4d00355be00d910f
			capabilities.setCapability("platformVersion", "5.0.1");
			capabilities.setCapability("platformName", "Android");
			//capabilities.setCapability("app", app.getAbsolutePath());
			capabilities.setCapability("appPackage", "com.magicjack.connect");
			System.out.println("Pass 'appPackage' step");
			Log.info("Pass 'Pass 'appPackage' step");
			
			capabilities.setCapability("appActivity", "com.magicjack.Main"); //t13580
			System.out.println("Pass 'setCapability' step");
			
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			System.out.println("Pass 'AndroidDriver' step");
			Log.info("Pass 'AndroidDriver' step");
		
			}

		private File installApp() {
			File classpathRoot = new File(System.getProperty("user.dir"));
			File appDir = new File(classpathRoot, "/Apps/vocaltec/");
			File app = new File(appDir, "app-master-release-1.1.6-50-ga60f206.apk");
			System.out.println("Pass 'appDir' step");
			return app;
		}
			
	
		
		@Test(priority=1)
			public void testMessageNew() throws MalformedURLException, InterruptedException {
				
			//logIn();		
			createNewMessage();
			chooseContactInChooseContactTab(new MessageData("0682142908", null));
			typeAndSendMessage(new MessageData("This is a test 1.", null));
			goToMessageTabFromChooseContactTab();
			System.out.println("-------------------");
			Log.info("-------------------");
			
			}

		
		 @Test (priority=2)
			public void testMessageExisted() throws MalformedURLException, InterruptedException {
								
			sendMessageToPersonFromConversations();
			chooseContactInMessageTab();
			typeAndSendMessage(new MessageData("This is a test 2.", null));
			System.out.println("-------------------");
			Log.info("-------------------");
		}
		
		private void createNewMessage() {
			driver.findElementById("com.magicjack.connect:id/more_messages_container").click();
			System.out.println("Click on Messages button");
			Log.info("Click on Messages button");
			
			driver.findElementById("com.magicjack.connect:id/messages_threads_new_message").click();
			System.out.println("Click on + button");
			Log.info("Click on + button");
					
		}

		private void chooseContactInChooseContactTab(MessageData message) {
			driver.findElementById("com.magicjack.connect:id/contacts_multiple_search").sendKeys(message.wifePhoneNumber); 
			System.out.println("Click on Search");
			Log.info("Click on Search");
			
			driver.findElementById("com.magicjack.connect:id/contacts_row_name").click(); //sendKeys(""); //0681108612
			System.out.println("Click on the first row name");
			Log.info("Click on the first row name");
			
			driver.findElementById("com.magicjack.connect:id/contacts_invite_container").click(); 
			System.out.println("Click on -> 'invite container'");
			Log.info("Click on -> 'invite container'");
		}

		private void goToMessageTabFromChooseContactTab() {
			driver.findElementByClassName("android.widget.ImageButton").click(); 
			System.out.println("Click on <- 'ImageButton'");
			Log.info("Click on <- 'ImageButton'");
		}

		private void typeAndSendMessage(MessageData message) {
			driver.findElementById("com.magicjack.connect:id/edt_message_content").sendKeys(message.message1); 
			System.out.println("Type 'This is a test 2.'");
			Log.info("Type 'This is a test 2.'");
			
			driver.findElementById("com.magicjack.connect:id/btn_message_send").click(); 
			System.out.println("Click on > 'btn_message_send'");
			Log.info("Click on > 'btn_message_send'");
						
		}

		private void sendMessageToPersonFromConversations() {
			
			driver.findElementByClassName("android.widget.ImageButton").click(); 
			System.out.println("Click on Menu");
			Log.info("Click on Menu");
			
			driver.findElementById("com.magicjack.connect:id/more_messages_container").click();
			System.out.println("Click on Messages button");
			Log.info("Click on Messages button");
		
		}

		private void chooseContactInMessageTab() {
			/*	
			driver.findElementById("com.magicjack.connect:id/action_search").click();
			System.out.println("Click on Search");
			Log.info("Click on Search");
								
			driver.findElementById("com.magicjack.connect:id/search_src_text").sendKeys("0681108612"); 
			System.out.println("Type 0681108612");
			Log.info("Type 0681108612");
			*/
			
			driver.findElementByXPath("//android.widget.LinearLayout[@index='1']").click(); 
			System.out.println("Click on the first contact");
			Log.info("Click on the first contact");
		}
		
		
		private void logIn() {
			/*				
			//to review	
			driver.findElement(By.id("com.magicjack.connect:id/welcome_skip")).click(); //Optional
			System.out.println("Click on Skip button");
			Log.info("Click on Skip button");
			*/
					
			driver.findElementById("com.magicjack.connect:id/registration_phone_number_value").sendKeys("685902238");
			System.out.println("Type 685902238 phone number");
			Log.info("Type 685902238 phone number");
			
			driver.findElementById("com.magicjack.connect:id/login_id_text").click();
			System.out.println("Click on Login In button");
			Log.info("Click on Login In button");
			
			driver.findElementById("com.magicjack.connect:id/registration_dialog_go").click(); 
			System.out.println("Click on Continue button");
			Log.info("Click on Continue button");
						
			driver.findElementById("com.magicjack.connect:id/trial_exit").click(); //Optional
			System.out.println("Click on Trial Exit button");
			Log.info("Click on Trial Exit button");
		}
			
			@AfterTest
			 public void End() {
			  driver.quit();
			 }
			
			}
 

