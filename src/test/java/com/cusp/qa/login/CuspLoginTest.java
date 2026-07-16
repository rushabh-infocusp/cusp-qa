package com.cusp.qa.login;

import com.cusp.qa.base.BaseTest;
import com.cusp.qa.config.ConfigLoader;
import com.cusp.qa.driver.DriverManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@Feature("Login")
public class CuspLoginTest extends BaseTest {

    private static final By CONTINUE_BTN  = AppiumBy.androidUIAutomator("new UiSelector().text(\"Continue\")");
    private static final By GET_STARTED   = AppiumBy.androidUIAutomator("new UiSelector().text(\"Get started\")");
    private static final By GOOGLE_CANCEL = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.google.android.gms:id/cancel\")");
    private static final By MOBILE_INPUT  = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\")");
    private static final By OTP_SCREEN    = AppiumBy.androidUIAutomator("new UiSelector().text(\"Enter verification code\")");
    private static final By NOTIF_ALLOW   = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"com.android.permissioncontroller:id/permission_allow_button\")");
    private static final By HOME_TAB      = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"com.infocusp.cuspmoney:id/nav_home\")");
    private static final By NET_WORTH_LABEL = AppiumBy.androidUIAutomator(
        "new UiSelector().textContains(\"Net Worth\")");

    // ── TC01 ──────────────────────────────────────────────────────────────────

    @Test(groups = {"smoke"}, description = "TC01 - App launches and opening screen is displayed")
    @Story("App Launch")
    public void testAppLaunchDisplaysScreen() throws Exception {
        AndroidDriver driver = DriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        By splashText = AppiumBy.androidUIAutomator(
            "new UiSelector().textContains(\"simple\").instance(0)"
        );
        WebElement screen = wait.until(ExpectedConditions.presenceOfElementLocated(splashText));
        Assert.assertNotNull(screen, "Opening screen should appear after launch");
        log.info("TC01 PASS — App launched. Screen text: {}", screen.getText());
    }

    // ── TC02 ──────────────────────────────────────────────────────────────────

    @Test(groups = {"smoke"}, description = "TC02 - Login with mobile 6351738122 and OTP 123456",
          dependsOnMethods = "testAppLaunchDisplaysScreen")
    @Story("Login Flow")
    public void testLoginWithMobileAndOtp() throws Exception {
        AndroidDriver driver = DriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        String mobile = ConfigLoader.get().getTestData().getMobileNumber();
        String otp    = ConfigLoader.get().getTestData().getOtp();
        String udid   = ConfigLoader.get().getAndroid().getUdid();
        String serial = (udid != null && !udid.isBlank()) ? "-s " + udid + " " : "";

        // Step 1: Wait for carousel to reach slide 4 (auto-advances), then tap Continue
        log.info("Step 1: Waiting for onboarding carousel to reach Get started");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        for (int i = 0; i < 15; i++) {
            if (!driver.findElements(GET_STARTED).isEmpty()) {
                log.info("Get started visible after {} loop iterations", i);
                break;
            }
            List<WebElement> cont = driver.findElements(CONTINUE_BTN);
            if (!cont.isEmpty()) {
                Rectangle r = cont.get(0).getRect();
                driver.executeScript("mobile: clickGesture",
                    Map.of("x", r.getX() + r.getWidth() / 2, "y", r.getY() + r.getHeight() / 2));
                log.info("Tapped Continue at y={}", r.getY() + r.getHeight() / 2);
            }
            Thread.sleep(1200);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Step 2: Tap Get started
        log.info("Step 2: Tapping Get started");
        wait.until(ExpectedConditions.presenceOfElementLocated(GET_STARTED)).click();
        Thread.sleep(2000);

        // Step 3: Dismiss Google sign-in hint picker
        log.info("Step 3: Dismissing Google sign-in picker if present");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        List<WebElement> signInCancel = driver.findElements(GOOGLE_CANCEL);
        if (!signInCancel.isEmpty()) {
            signInCancel.get(0).click();
            log.info("Dismissed sign-in Google picker");
            Thread.sleep(1000);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Step 4: Tap mobile EditText — also triggers phone-number hint picker
        log.info("Step 4: Entering mobile number {}", mobile);
        wait.until(ExpectedConditions.elementToBeClickable(MOBILE_INPUT)).click();
        Thread.sleep(1500);

        // Dismiss phone-number hint picker without re-clicking (would re-trigger picker)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        List<WebElement> hintCancel = driver.findElements(GOOGLE_CANCEL);
        if (!hintCancel.isEmpty()) {
            hintCancel.get(0).click();
            log.info("Dismissed phone-number hint picker");
            Thread.sleep(800);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Type mobile number while field retains focus
        Runtime.getRuntime()
            .exec(("adb " + serial + "shell input text " + mobile).split(" "))
            .waitFor();
        log.info("Mobile number entered via ADB — app will auto-submit on 10 digits");
        Thread.sleep(500);

        // Step 5: Wait for OTP screen (app auto-submits after 10 digits)
        log.info("Step 5: Waiting for OTP screen");
        wait.until(ExpectedConditions.presenceOfElementLocated(OTP_SCREEN));
        log.info("OTP screen loaded");
        Thread.sleep(1000);

        // Step 6: Tap OTP input area to show keyboard, then enter each digit via keyevent
        // Compose OTP boxes require individual keyevents — 'input text' doesn't fill them
        log.info("Step 6: Entering OTP {} digit by digit via keyevents", otp);
        Dimension size = driver.manage().window().getSize();
        // OTP box row is at ~23% of screen height (empirically: y≈551 on 2424px screen)
        driver.executeScript("mobile: clickGesture",
            Map.of("x", size.getWidth() / 2, "y", (int)(size.getHeight() * 0.23)));
        Thread.sleep(700);

        for (char c : otp.toCharArray()) {
            Runtime.getRuntime()
                .exec(("adb " + serial + "shell input keyevent KEYCODE_" + c).split(" "))
                .waitFor();
            Thread.sleep(300);
        }
        log.info("OTP digits sent — app auto-navigates when all boxes turn green");
        Thread.sleep(3000);

        // Step 7: Allow notification permission dialog if shown
        log.info("Step 7: Handling notification permission dialog if present");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        List<WebElement> allowBtn = driver.findElements(NOTIF_ALLOW);
        if (!allowBtn.isEmpty()) {
            allowBtn.get(0).click();
            log.info("Notification permission allowed");
            Thread.sleep(2000);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Step 8: Verify home screen reached
        log.info("Step 8: Verifying home screen loaded");
        wait.until(ExpectedConditions.or(
            ExpectedConditions.presenceOfElementLocated(HOME_TAB),
            ExpectedConditions.presenceOfElementLocated(NET_WORTH_LABEL)));
        log.info("TC02 PASS — Login successful, home screen reached with mobile {}", mobile);
    }
}
