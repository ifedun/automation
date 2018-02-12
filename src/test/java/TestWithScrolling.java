import appiumsetup.SetupAppiumDriver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobject.ContactDetails;

public class TestWithScrolling {
    private static AppiumDriver<MobileElement> driver;

    @BeforeClass
    public void invokeApiumDriver() {
        SetupAppiumDriver setupAppiumDriver = new SetupAppiumDriver(driver);
        driver = setupAppiumDriver.invokeDriver();
    }

    @Test
    public void testUsersData() {

        MobileElement contact;
        MobileElement contact2;
        MobileElement firstListElement;
        String contactNameFromList;
        String contactNameFromList2;
        TouchAction touchAction = new TouchAction(driver);

        do {

            firstListElement = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.View/android.widget.FrameLayout[2]/android.view.View/android.widget.LinearLayout/android.widget.ListView/android.widget.RelativeLayout[1]"));
            String name = firstListElement.getText();

            contact = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.View/android.widget.FrameLayout[2]/android.view.View/android.widget.LinearLayout/android.widget.ListView/android.widget.RelativeLayout[9]/android.widget.TextView"));
            contactNameFromList = contact.getText();

            touchAction.press(contact).moveTo(firstListElement).release().perform();

            contact2 = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.View/android.widget.FrameLayout[2]/android.view.View/android.widget.LinearLayout/android.widget.ListView/android.widget.RelativeLayout[9]/android.widget.TextView"));
            contactNameFromList2 = contact2.getText();

        } while (!contactNameFromList.equals(contactNameFromList2));

        contact.click();

        ContactDetails contactDetails = new ContactDetails(driver);
        String contactNameFromDetailsScreen = contactDetails.getUserFullName();
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
