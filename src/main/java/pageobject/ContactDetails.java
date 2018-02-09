package pageobject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class ContactDetails {

    private AppiumDriver<MobileElement> driver;

    public ContactDetails(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    private String titleField = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.View/android.widget.FrameLayout[1]/android.view.View/android.widget.TextView";
    private String iconFiled = "detail_icon";
    private String userFullName = "detail_name";
    private String phoneNumberTitle = "phonenumber_tag";
    private String userPhoneNumber = "phonenumber";
    private String emailAddressTitle = "email_tag";
    private String userEmailAddress = "email";
    private String streetAddressTitle = "street_tag";
    private String userStreeAddress1 = "street1";
    private String userStreeAddress2 = "street2";


    public String getTitle() {
        MobileElement title = driver.findElement(By.xpath(titleField));
        return title.getText();
    }

    public boolean iconIsDisplayed() {
        MobileElement icon = driver.findElement(By.id(iconFiled));
        return icon.isDisplayed();
    }

    public String getUserFullName() {
        MobileElement fullName = driver.findElement(By.id(userFullName));
        return fullName.getText();
    }

    public String getPhoneNumberTitle() {
        MobileElement numberTitle = driver.findElement(By.id(phoneNumberTitle));
        return numberTitle.getText();
    }

    public String getUserPhoneNumber() {
        MobileElement phoneNumber = driver.findElement(By.id(userPhoneNumber));
        return phoneNumber.getText();
    }

    public String getEmailAddressTitle() {
        MobileElement addressTitle = driver.findElement(By.id(emailAddressTitle));
        return addressTitle.getText();
    }

    public String getUserEmailAddress() {
        MobileElement emailAddress = driver.findElement(By.id(userEmailAddress));
        return emailAddress.getText();
    }

    public String getStreetAddressTitle() {
        MobileElement addressTitle = driver.findElement(By.id(streetAddressTitle));
        return addressTitle.getText();
    }

    public String getUserStreeAddress() {
        MobileElement streetAddress1 = driver.findElement(By.id(userStreeAddress1));
        MobileElement streetAddress2 = driver.findElement(By.id(userStreeAddress2));
        return streetAddress1.getText() + " " + streetAddress2.getText();
    }


}
