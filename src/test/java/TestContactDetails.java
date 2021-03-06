import appiumsetup.AppiumServerService;
import appiumsetup.SetupAppiumDriver;
import utilities.AdbCommands;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.Assert;
import org.testng.annotations.*;
import pageobject.ContactDetails;
import pageobject.ContactsList;
import utils.ExtentReport;

public class TestContactDetails extends ExtentReport {

    private static AppiumDriver<MobileElement> driver;

    @BeforeClass
    public void invokeAppiumServerAndAppiumDriver() {
        AppiumServerService service = new AppiumServerService();
        service.startServer();

        SetupAppiumDriver setupAppiumDriver = new SetupAppiumDriver(driver);
        driver = setupAppiumDriver.invokeDriver();
    }

    @BeforeMethod
    public void launchApp() {
        driver.launchApp();

        ContactsList contactsList = new ContactsList(driver);
        contactsList.isSearchPerformed("Cedillo");
        contactsList.clickOnContact("Duance Cedillo");

    }

    @Test
    public void testTitle() {
        ContactDetails contactDetails = new ContactDetails(driver);
        Assert.assertEquals(contactDetails.getTitle(), "Contacts");
    }

    @Test
    public void testIcon() {
        ContactDetails contactDetails = new ContactDetails(driver);
        Assert.assertTrue(contactDetails.iconIsDisplayed());
    }

    @Test
    public void phoneNumberTitle() {
        ContactDetails contactDetails = new ContactDetails(driver);
        Assert.assertEquals(contactDetails.getPhoneNumberTitle(), "PHONE NUMBER");
    }

    @Test
    public void userPhoneNumber() {
        ContactDetails contactDetails = new ContactDetails(driver);
        Assert.assertEquals(contactDetails.getUserPhoneNumber(), "+1(141)-1779583");
    }

    @Test
    public void emailAddressTitle() {
        ContactDetails contactDetails = new ContactDetails(driver);
        Assert.assertEquals(contactDetails.getEmailAddressTitle(), "EMAIL ADDRESS");
    }

    @Test
    public void userEmailAddress() {
        ContactDetails contactDetails = new ContactDetails(driver);
        Assert.assertEquals(contactDetails.getUserEmailAddress(), "hxduane23@gmail.com");
    }

    @Test
    public void streetAddressTitle() {
        ContactDetails contactDetails = new ContactDetails(driver);
        Assert.assertEquals(contactDetails.getStreetAddressTitle(), "STREET ADDRESS");
    }

    @Test
    public void UserStreetAddress() {
        ContactDetails contactDetails = new ContactDetails(driver);
        Assert.assertEquals(contactDetails.getUserStreeAddress(), "438 Ethels Lane 24456 Houston");
    }

    @AfterMethod
    public void quitApp() {

        driver.closeApp();
        AdbCommands adbCommands = new AdbCommands("com.jayway.contacts");
        adbCommands.forceStopApp();
        adbCommands.clearAppData();
    }

    @AfterClass
    public void quitDriverAndServer() {

        driver.quit();

        AppiumServerService service = new AppiumServerService();
        service.stopServer();
    }

}
