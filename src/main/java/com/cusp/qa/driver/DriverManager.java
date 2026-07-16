package com.cusp.qa.driver;

import com.cusp.qa.config.AppConfig;
import com.cusp.qa.config.ConfigLoader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.time.Duration;

public class DriverManager {

    private static final Logger log = LogManager.getLogger(DriverManager.class);
    private static final ThreadLocal<AndroidDriver> driverThread = new ThreadLocal<>();

    private DriverManager() {}

    public static AndroidDriver getDriver() {
        return driverThread.get();
    }

    public static void initDriver() {
        if (driverThread.get() != null) return;

        AppConfig cfg = ConfigLoader.get();
        AppConfig.AndroidConfig android = cfg.getAndroid();

        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName(android.getPlatformName());
        options.setDeviceName(android.getDeviceName());
        options.setAppPackage(android.getAppPackage());
        options.setAppActivity(android.getAppActivity());
        options.setNoReset(android.isNoReset());
        options.setFullReset(android.isFullReset());
        options.setAutoGrantPermissions(android.isAutoGrantPermissions());
        options.setNewCommandTimeout(Duration.ofSeconds(android.getNewCommandTimeout()));

        if (android.getUdid() != null && !android.getUdid().isBlank()) {
            options.setUdid(android.getUdid());
        }
        if (android.getAppPath() != null && !android.getAppPath().isBlank()) {
            options.setApp(android.getAppPath());
        }

        options.setCapability("appium:skipDeviceInitialization", android.isSkipDeviceInitialization());
        options.setCapability("appium:adbExecTimeout", android.getAdbExecTimeout());
        options.setCapability("appium:uiautomator2ServerLaunchTimeout", android.getUiautomator2ServerLaunchTimeout());
        options.setCapability("appium:uiautomator2ServerInstallTimeout", android.getUiautomator2ServerInstallTimeout());

        unlockDevice(android.getUdid());

        try {
            URL serverUrl = new URL(cfg.getAppium().getServerUrl());
            log.info("Starting AndroidDriver at {}", serverUrl);
            AndroidDriver driver = new AndroidDriver(serverUrl, options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(cfg.getTimeouts().getImplicit()));
            driverThread.set(driver);
            log.info("Driver initialised successfully. Session ID: {}", driver.getSessionId());
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialise AndroidDriver", e);
        }
    }

    private static void unlockDevice(String udid) {
        try {
            String serial = (udid != null && !udid.isBlank()) ? "-s " + udid + " " : "";
            String adb = "adb " + serial;
            Runtime rt = Runtime.getRuntime();
            rt.exec((adb + "shell input keyevent KEYCODE_WAKEUP").split(" ")).waitFor();
            rt.exec((adb + "shell wm dismiss-keyguard").split(" ")).waitFor();
            log.info("Device screen unlocked");
        } catch (Exception e) {
            log.warn("Could not unlock device screen (non-fatal): {}", e.getMessage());
        }
    }

    public static void quitDriver() {
        AndroidDriver driver = driverThread.get();
        if (driver != null) {
            driver.quit();
            driverThread.remove();
            log.info("Driver closed");
        }
    }
}
