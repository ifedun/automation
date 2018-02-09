package appiumsetup;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;


public class SetupAppiumDriver {

    private static AppiumDriver<MobileElement> driver;

    public SetupAppiumDriver(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public AppiumDriver<MobileElement> invokeDriver() {
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

        return driver;
    }

}
