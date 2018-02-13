package pageobject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ContactsList {

    private AppiumDriver<MobileElement> driver;

    public ContactsList(AppiumDriver<MobileElement> driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
    }

    @iOSFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Contacts\"]")
    @AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.view.View/android.widget.TextView")
    private MobileElement titleFieldElement;

//  Fields - elements of a page where value is an element's locator

    private String titleField = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.View/android.widget.FrameLayout[1]/android.view.View/android.widget.TextView";
    private String searchField = "main_search"; // id
    private String contactsList = "name"; // id
    private String floatingButton = "fab"; // id
    private String errorField = "main_text"; // id


//  Method - action

    public String getTitle() {
        return titleFieldElement.getText();
    }

    public String getSearchHint() {
        MobileElement searchHint = driver.findElement(By.id(searchField));
        return searchHint.getText();
    }

    public boolean isSearchPerformed(String name) {
        MobileElement search = driver.findElement(By.id(searchField));
        search.sendKeys(name);

        List<MobileElement> namesList = driver.findElements(By.id(contactsList));
        boolean result = false;

        for (MobileElement element : namesList) {
            String elementFullName = element.getText();
            if (elementFullName != null && name != null && name.length() <= elementFullName.length() && elementFullName.contains(name)) {
                result = true;
            }
        }
        return result;
    }

    public boolean errorIsDisplayed() {
        MobileElement error = driver.findElement(By.id(errorField));
        return error.isDisplayed();
    }

    public String getErrorText() {
        MobileElement error = driver.findElement(By.id(errorField));
        return error.getText();
    }

    public void clickOnContact(String contactFullName) {
        List<MobileElement> contactNamesList = driver.findElements(By.id(contactsList));
        if (contactNamesList.size() == 1) {
            contactNamesList.get(0).click();
        } else {
            for (MobileElement element : contactNamesList) {
                String value = element.getText();
                if (value.equals(contactFullName)) {
                    element.click();
                    break;
                }
            }

        }
    }


    public void clickOnFloatingButton() {
        MobileElement button = driver.findElement(By.id(floatingButton));
        button.click();
    }


}
