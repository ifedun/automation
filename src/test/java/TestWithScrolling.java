import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class TestWithScrolling {
    private static AppiumDriver<MobileElement> driver;

    @BeforeClass
    public void invokeAppiumDriver() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "5.1.1");
        desiredCapabilities.setCapability("deviceName", "Nexus");
        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, "false");
        desiredCapabilities.setCapability("appPackage", "com.jayway.contacts");
        desiredCapabilities.setCapability("appActivity", "com.jayway.contacts.MainActivity");

        try {
            driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), desiredCapabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUsersData() {

        MobileElement contactsList = driver.findElement(By.id("result_list"));
        contactsList.swipe(SwipeElementDirection.UP, 10, 10, 1000);

        MobileElement contact = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.View/android.widget.FrameLayout[2]/android.view.View/android.widget.LinearLayout/android.widget.ListView/android.widget.RelativeLayout[9]/android.widget.TextView"));
        String contactNameFromList = contact.getText();

        contactsList.swipe(SwipeElementDirection.UP, 10, 10, 1000);
        MobileElement contact2 = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.View/android.widget.FrameLayout[2]/android.view.View/android.widget.LinearLayout/android.widget.ListView/android.widget.RelativeLayout[9]/android.widget.TextView"));
        String contactNameFromList2 = contact2.getText();

        Assert.assertEquals(contactNameFromList, contactNameFromList2);

        contact.click();

        MobileElement contactDetailsScreen = driver.findElement(By.id("detail_name"));
        String contactNameFromDetailsScreen = contactDetailsScreen.getText();
        Assert.assertEquals(contactNameFromList, contactNameFromDetailsScreen);

    }

    @AfterMethod
    public void closeApp() {

        driver.closeApp();
    }

    @AfterClass
    public void quitDriver() {

        driver.quit();
    }
}
