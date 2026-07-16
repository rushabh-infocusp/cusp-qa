package com.cusp.qa.pages;

import com.cusp.qa.config.ConfigLoader;
import com.cusp.qa.driver.DriverManager;
import com.cusp.qa.utils.WaitUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

public abstract class BasePage {

    protected final Logger log = LogManager.getLogger(getClass());
    protected final AndroidDriver driver;
    protected final WaitUtils wait;

    protected BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WaitUtils(driver, ConfigLoader.get().getTimeouts().getExplicit());
    }

    // ── Element interactions ──────────────────────────────────────────────────

    protected WebElement findElement(By locator) {
        return wait.forVisibility(locator);
    }

    protected List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    protected void tap(By locator) {
        log.debug("Tapping: {}", locator);
        wait.forClickable(locator).click();
    }

    protected void typeText(By locator, String text) {
        log.debug("Typing '{}' into: {}", text, locator);
        WebElement el = wait.forClickable(locator);
        el.clear();
        el.sendKeys(text);
    }

    protected String getText(By locator) {
        return findElement(locator).getText();
    }

    protected boolean isElementDisplayed(By locator) {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(ConfigLoader.get().getTimeouts().getImplicit())
            );
        }
    }

    // ── Swipe helpers (W3C PointerInput — Appium Java client 9.x) ─────────────

    private void performSwipe(int startX, int startY, int endX, int endY, long durationMs) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(durationMs), PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(swipe));
    }

    protected void scrollDown() {
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();
        performSwipe(width / 2, (int)(height * 0.8), width / 2, (int)(height * 0.2), 800);
    }

    protected void scrollUp() {
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();
        performSwipe(width / 2, (int)(height * 0.2), width / 2, (int)(height * 0.8), 800);
    }

    protected void scrollToText(String text) {
        driver.findElement(AppiumBy.androidUIAutomator(
            "new UiScrollable(new UiSelector().scrollable(true))" +
            ".scrollIntoView(new UiSelector().text(\"" + text + "\"))"
        ));
    }

    protected void scrollToTextContaining(String text) {
        driver.findElement(AppiumBy.androidUIAutomator(
            "new UiScrollable(new UiSelector().scrollable(true))" +
            ".scrollIntoView(new UiSelector().textContains(\"" + text + "\"))"
        ));
    }

    protected void swipeLeft() {
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();
        performSwipe((int)(width * 0.8), height / 2, (int)(width * 0.2), height / 2, 600);
    }

    protected void swipeRight() {
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();
        performSwipe((int)(width * 0.2), height / 2, (int)(width * 0.8), height / 2, 600);
    }

    // ── Navigation ─────────────────────────────────────────────────────────────

    protected void navigateBack() {
        driver.navigate().back();
    }

    protected void hideKeyboard() {
        try {
            driver.hideKeyboard();
        } catch (Exception ignored) {}
    }

    // ── Subclasses declare their page readiness ───────────────────────────────

    public abstract boolean isLoaded();
}
