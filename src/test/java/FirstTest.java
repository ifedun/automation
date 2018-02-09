import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class FirstTest {

    private static AppiumDriver<MobileElement> driver;

    public static void main(String[] args) {

        File file = new File(System.getProperty("user.dir"));
        File appPath = new File(file + "/app/android/Contacts.apk");

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "8.0");
        desiredCapabilities.setCapability("deviceName", "Samsung");
        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, "false");
        desiredCapabilities.setCapability("appPackage", "com.jayway.contacts");
        desiredCapabilities.setCapability("appActivity", "com.jayway.contacts.MainActivity");
//        desiredCapabilities.setCapability("app", appPath);

        try {
            driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), desiredCapabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        MobileElement searchField = driver.findElement(By.id("main_search"));
        searchField.sendKeys("Cedillo");

        List<MobileElement> namesList = driver.findElements(By.id("name"));
        assert namesList.size() == 1;

        namesList.get(0).click();
        MobileElement userEmail = driver.findElement(By.id("email"));
        assert userEmail.getText().equals("hxduane23@gmail.com");


    }


//    @Test
//    public void testPositiveCases(){
//
//    }
}
