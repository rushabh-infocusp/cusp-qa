package com.cusp.qa.utils;

import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtils {

    private static final Logger log = LogManager.getLogger(ScreenshotUtils.class);
    private static final String SCREENSHOT_DIR = "test-output/screenshots/";
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    private ScreenshotUtils() {}

    public static String capture(AndroidDriver driver, String name) {
        try {
            String timestamp = LocalDateTime.now().format(FMT);
            String path = SCREENSHOT_DIR + name + "_" + timestamp + ".png";
            File src = driver.getScreenshotAs(OutputType.FILE);
            File dest = new File(path);
            dest.getParentFile().mkdirs();
            FileUtils.copyFile(src, dest);
            log.info("Screenshot saved: {}", path);
            return dest.getAbsolutePath();
        } catch (Exception e) {
            log.error("Failed to capture screenshot: {}", e.getMessage());
            return null;
        }
    }

    public static byte[] captureAsBytes(AndroidDriver driver) {
        try {
            return driver.getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            log.error("Failed to capture screenshot bytes: {}", e.getMessage());
            return new byte[0];
        }
    }

    public static String captureFailure(AndroidDriver driver, String testName) {
        return capture(driver, "FAIL_" + testName);
    }
}
