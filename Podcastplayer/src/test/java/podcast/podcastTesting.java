package podcast;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
//import org.openqa.selenium.By.ByXPath;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Podcast_cap.cap_podcast;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class podcastTesting extends cap_podcast {

    public AndroidDriver<AndroidElement> driver;

    @BeforeTest
    public void bt() throws MalformedURLException {
        // Initialize the driver using the desired capabilities from cap_podcast class
        driver = cap();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);  // Set implicit wait for element detection
    }

    @Test (priority =1)
    public void openPodcastApp() throws InterruptedException {
        // Step 1: Validate that the app has started
        System.out.println("Podcast App has been launched.");
        
        // Step 2: Click on the profile using XPath
        System.out.println("Navigating to profile.");
        driver.findElement(By.xpath("(//android.view.ViewGroup[@resource-id='au.com.shiftyjelly.pocketcasts:id/navigation_bar_item_labels_group'])[4]")).click();
        
        // Introduce a short wait for the profile page to load
        Thread.sleep(4000); 
        
        // Step 3: Click on the "Settings" button using Accessibility ID
        System.out.println("Navigating to settings.");
        driver.findElementByAccessibilityId("Settings").click();
        
        // Introduce a short wait for the settings page to load
        Thread.sleep(3000);
        
        // Step 4: Click on the "Notifications" button in the settings menu (assuming it's the third item in a ScrollView)
        System.out.println("Navigating to Notifications.");
        driver.findElementByXPath("//android.widget.ScrollView/android.view.View[3]").click();
        
        // Wait for the Notifications settings page to load
        Thread.sleep(3000);
        
        // Step 5: Toggle the "Notify Me" option by clicking on the switch element
        System.out.println("Toggling 'Notify Me' option.");
        driver.findElementByXPath("(//android.widget.Switch[@resource-id='android:id/switch_widget'])[1]").click();
        
        // Wait for the change to take effect
        Thread.sleep(8000); // Adjust wait times as needed, consider WebDriverWait for better handling
        
        // Optional: Implement further actions, such as navigating back or verifying settings
        System.out.println("Action complete. Optional: Navigate back or verify further settings.");
    }

    
    
    
    @Test(priority = 2)
    public void toggleBatterySaver() throws InterruptedException {
        // Step 1: Go back to the home screen
        System.out.println("Returning to the home screen...");
        driver.pressKey(new KeyEvent(AndroidKey.HOME)); // Press the home button to return to the home screen
        Thread.sleep(3000); // Shorter wait time to ensure responsiveness

        // Step 2: Open the notification panel
        System.out.println("Opening the notification panel...");
        driver.openNotifications();
        Thread.sleep(3000); // Shorter wait time to allow notifications to load

        // Step 3: Click on Battery Saver
        System.out.println("Clicking on Battery Saver...");
        // Locate the Battery Saver toggle by its XPath and click it
        //enable
        driver.findElements(MobileBy.className("android.widget.ImageView")).get(4).click();
        Thread.sleep(3000); 
        //disable
        driver.findElements(MobileBy.className("android.widget.ImageView")).get(4).click();
        Thread.sleep(3000); // Shorter wait time to allow notifications to load


        // Step 4: Go back to the home screen
        System.out.println("Returning to the home screen...");
        driver.pressKey(new KeyEvent(AndroidKey.HOME)); // Press the home button to return to the home screen
    } 
    
    
   
	

	@Test(priority = 3)
    public void switchApp() throws InterruptedException {
        // Step 1: Switch to General Store app
        System.out.println("Switching to General Store app...");
        driver.activateApp("com.androidsample.generalstore");
        
        // Step 2: Wait for the app to load
        Thread.sleep(15000); // Adjust as necessary for the app to fully load

        // Step 3: Get the toolbar title text
        String msg = driver.findElement(MobileBy.xpath("//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/toolbar_title']")).getText();
        System.out.println("Copied text from General Store app: " + msg); // Output copied text
        
        // Step 4: Switch back to the Podcast Player app
        System.out.println("Switching back to Podcast Player app...");
        driver.activateApp("au.com.shiftyjelly.pocketcasts"); // Activate the Podcast Player app
        
        // Step 5: Wait for the Podcast Player app to load
        Thread.sleep(5000); // Adjust as necessary for the app to fully load

        // Step 6: Go back to the home screen
        System.out.println("Returning to the home screen...");
        driver.pressKey(new KeyEvent(AndroidKey.HOME)); // Press the home button to return to the home screen
        
        // Step 7: Wait after pressing the home button
        Thread.sleep(3000); // Adjust wait time as necessary
    }


}
