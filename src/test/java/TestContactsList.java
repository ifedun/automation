import appiumsetup.SetupAppiumDriver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.Assert;
import org.testng.annotations.*;
import pageobject.ContactDetails;
import pageobject.ContactsList;

public class TestContactsList {

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

    @BeforeClass
    public void invokeApiumDriver() {
        SetupAppiumDriver setupAppiumDriver = new SetupAppiumDriver(driver);
        driver = setupAppiumDriver.invokeDriver();
    }


    @BeforeMethod
    public void launchApp() {
        driver.launchApp();
    }

    @Test
    public void testTitle() {
        ContactsList contactsList = new ContactsList(driver);
        Assert.assertEquals(contactsList.getTitle(), "Contacts");
    }

    @Test
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

    @Test
    public void testSearch() {
        ContactsList contactsList = new ContactsList(driver);
        Assert.assertTrue(contactsList.searchIsPerformed("ma"));
    }

    @Test(dataProvider = "dp")
    public void testClickOnContact(String searchValue, String contactFullName) {
        ContactsList contactsList = new ContactsList(driver);
        contactsList.searchIsPerformed(searchValue);
        contactsList.clickOnContact(contactFullName);

        ContactDetails contactDetails = new ContactDetails(driver);

        Assert.assertEquals(contactDetails.getUserFullName(), contactFullName);
    }

    @Test
    public void testSearchError() {
        ContactsList contactsList = new ContactsList(driver);
        String searchValue = "jj";
        contactsList.searchIsPerformed(searchValue);
        Assert.assertTrue(contactsList.errorIsDisplayed());
        Assert.assertEquals(contactsList.getErrorText(), "No contacts found with " + "\"" + searchValue + "\"" + " in the name");
    }

    @AfterMethod
    public void quitApp() {
        driver.closeApp();


    }

    @AfterClass
    public void quitDriver() {
        driver.quit();
    }
}

