package com.cusp.qa.base;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.cusp.qa.driver.DriverManager;
import com.cusp.qa.utils.ExtentReportManager;
import com.cusp.qa.utils.ScreenshotUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class BaseTest {

    protected final Logger log = LogManager.getLogger(getClass());

    @BeforeSuite(alwaysRun = true)
    public void suiteSetup() {
        ExtentReportManager.getInstance();
        log.info("=== Test Suite Started ===");
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method) {
        log.info("Starting test: {}", method.getName());
        DriverManager.initDriver();

        ExtentTest test = ExtentReportManager.getInstance()
            .createTest(method.getName());
        ExtentReportManager.setTest(test);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        ExtentTest test = ExtentReportManager.getTest();

        if (result.getStatus() == ITestResult.FAILURE) {
            log.error("Test FAILED: {}", result.getName());
            if (test != null && DriverManager.getDriver() != null) {
                byte[] screenshot = ScreenshotUtils.captureAsBytes(DriverManager.getDriver());
                test.fail(result.getThrowable())
                    .addScreenCaptureFromBase64String(
                        java.util.Base64.getEncoder().encodeToString(screenshot),
                        "Failure screenshot"
                    );
            }
        } else if (result.getStatus() == ITestResult.SKIP) {
            log.warn("Test SKIPPED: {}", result.getName());
            if (test != null) test.log(Status.SKIP, "Test skipped: " + result.getThrowable());
        } else {
            log.info("Test PASSED: {}", result.getName());
            if (test != null) test.log(Status.PASS, "Test passed");
        }

        DriverManager.quitDriver();
    }

    @AfterSuite(alwaysRun = true)
    public void suiteTearDown() {
        ExtentReportManager.flush();
        log.info("=== Test Suite Completed ===");
    }
}
