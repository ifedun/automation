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

public class TestContactsList extends ExtentReport{

    @DataProvider(name = "dp")
    public Object[][] createData() {
        return new Object[][]{
                {"ce", "Duane Cedillo"},
                {"ce", "Garance Epperson"},
                {"ma", "Irma Bolden"},
                {"ma", "Madison Gentile"},
                {"ma", "Matthew Heath"},
                {"ma", "Byron Workman"},
        };
    }

    private static AppiumDriver<MobileElement> driver;

    @BeforeClass(alwaysRun = true)
    public void invokeAppiumServerAndAppiumDriver() {
        AppiumServerService service = new AppiumServerService();
        service.startServer();

        SetupAppiumDriver setupAppiumDriver = new SetupAppiumDriver(driver);
        driver = setupAppiumDriver.invokeDriver();
    }


    @BeforeMethod(alwaysRun = true)
    public void launchApp() {
        driver.launchApp();
    }

    @Test
    public void testTitle() {
        ContactsList contactsList = new ContactsList(driver);
        Assert.assertEquals(contactsList.getTitle(), "Contacts");
    }

    @Test(groups = {"smoke"})
    public void testSearchHint() {
        ContactsList contactsList = new ContactsList(driver);
        Assert.assertEquals(contactsList.getSearchHint(), "Search for contact name");
    }

    @Test
    public void testFloatingButton() {
        ContactsList contactsList = new ContactsList(driver);
        contactsList.clickOnFloatingButton();
//        Assert.assertEquals("new view is opened"); how to test toast message?
    }

    @Test(groups = {"smoke"})
    public void testSearch() {
        ContactsList contactsList = new ContactsList(driver);
        Assert.assertTrue(contactsList.isSearchPerformed("ma"));
    }

    @Test(dataProvider = "dp")
    public void testClickOnContact(String searchValue, String contactFullName) {
        ContactsList contactsList = new ContactsList(driver);
        contactsList.isSearchPerformed(searchValue);
        contactsList.clickOnContact(contactFullName);

        ContactDetails contactDetails = new ContactDetails(driver);

        Assert.assertEquals(contactDetails.getUserFullName(), contactFullName);
    }

    @Test
    public void testSearchError() {
        ContactsList contactsList = new ContactsList(driver);
        String searchValue = "jj";
        contactsList.isSearchPerformed(searchValue);
        Assert.assertTrue(contactsList.errorIsDisplayed());
        Assert.assertEquals(contactsList.getErrorText(), "No contacts found with " + "\"" + searchValue + "\"" + " in the name");
    }

    @AfterMethod(alwaysRun = true)
    public void quitApp() {
        driver.closeApp();

        AdbCommands adbCommands = new AdbCommands("com.jayway.contacts");
        adbCommands.forceStopApp();
        adbCommands.clearAppData();


    }

    @AfterClass(alwaysRun = true)
    public void quitDriverAndServer() {

        driver.quit();

        AppiumServerService service = new AppiumServerService();
        service.stopServer();
    }
}


