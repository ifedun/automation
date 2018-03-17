package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.lang.reflect.Method;

public class ExtentReport {

    public static ExtentReports reports;
    ExtentTest testInfo;
    ExtentHtmlReporter htmlReporter;

    @BeforeTest
    public void setup() {
        htmlReporter = new ExtentHtmlReporter(new File(System.getProperty("user.dir") + "/AutomationReport.html"));
        htmlReporter.loadXMLConfig(new File(System.getProperty("user.dir") + "/extent-config.xml"));
        reports = new ExtentReports();
        reports.attachReporter(htmlReporter);
    }

    @BeforeMethod
    public void registerEachTestInExtentReports(Method method) {
        String testName = method.getName();
        testInfo = reports.createTest(testName);
    }

    @AfterMethod
    public void captureStatus(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            testInfo.log(Status.PASS, "The test method named as: " + result.getName() + " is passed");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            testInfo.log(Status.FAIL, "The test method named as: " + result.getName() + " is failed");
            testInfo.log(Status.INFO, "Test failure reason: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            testInfo.log(Status.SKIP, "The test method named as: " + result.getName() + " is skipped");
        }
    }

    @AfterTest
    public void CleanUp() {
        reports.flush();
    }
}

