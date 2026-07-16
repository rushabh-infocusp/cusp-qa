package com.cusp.qa.utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private final AndroidDriver driver;
    private final int defaultTimeoutSec;

    public WaitUtils(AndroidDriver driver, int defaultTimeoutSec) {
        this.driver = driver;
        this.defaultTimeoutSec = defaultTimeoutSec;
    }

    private WebDriverWait wait(int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    public WebElement forVisibility(By locator) {
        return wait(defaultTimeoutSec).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement forVisibility(By locator, int timeoutSec) {
        return wait(timeoutSec).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement forClickable(By locator) {
        return wait(defaultTimeoutSec).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement forPresence(By locator) {
        return wait(defaultTimeoutSec).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public boolean forInvisibility(By locator) {
        return wait(defaultTimeoutSec).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public boolean forInvisibility(By locator, int timeoutSec) {
        return wait(timeoutSec).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public boolean forTextInElement(By locator, String text) {
        return wait(defaultTimeoutSec).until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }
}
