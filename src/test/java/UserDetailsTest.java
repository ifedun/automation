import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UserDetailsTest {

    private static AppiumDriver<MobileElement> driver;
    private List<User> users = new ArrayList<User>(3);

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

    @BeforeMethod
    public void createMetaData() {
        users.add(new User("Duane", "Cedillo", "+1(141)-1779583", "hxduane23@gmail.com", "438 Ethels Lane"));
        users.add(new User("Joy", "Stclair", "+1(555)-9779202", "alstclair11@yopmail.com", "52 Woodside Way"));
        users.add(new User("Madison", "Gentile", "+1(343)-4779854", "dmmadison12@yopmail.com", "311 V Street"));
    }

    @Test
    public void testUsersData() {

        for (User user : users) {

            MobileElement searchField = driver.findElement(By.id("main_search"));
            searchField.sendKeys(user.getLastName());

            List<MobileElement> namesList = driver.findElements(By.id("name"));
            Assert.assertEquals(namesList.size(), 1);

            namesList.get(0).click();

            MobileElement userNameSurname = driver.findElement(By.id("detail_name"));
            Assert.assertEquals(userNameSurname.getText(), user.getFirstName() + " " + user.getLastName());

            MobileElement phoneNumber = driver.findElement(By.id("phonenumber"));
            Assert.assertEquals(phoneNumber.getText(), user.getPhoneNumber());

            MobileElement userEmail = driver.findElement(By.id("email"));
            Assert.assertEquals(userEmail.getText(), user.getEmailAddress());

            MobileElement userStreetAddress = driver.findElement(By.id("street1"));
            Assert.assertEquals(userStreetAddress.getText(), user.getStreetAddress());

            driver.navigate().back();

            searchField.clear();

        }


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

