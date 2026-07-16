package com.cusp.qa.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppConfig {
    private AppiumConfig appium;
    private AndroidConfig android;
    private TimeoutConfig timeouts;
    private TestDataConfig testData;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AppiumConfig {
        private String serverUrl;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AndroidConfig {
        private String platformName;
        private String automationName;
        private String appPackage;
        private String appActivity;
        private String deviceName;
        private String udid;
        private String appPath;
        private boolean noReset;
        private boolean fullReset;
        private boolean autoGrantPermissions;
        private int newCommandTimeout;
        private int adbExecTimeout;
        private int uiautomator2ServerLaunchTimeout;
        private int uiautomator2ServerInstallTimeout;
        private boolean skipDeviceInitialization;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TimeoutConfig {
        private int implicit;
        private int explicit;
        private int pageLoad;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TestDataConfig {
        private String mobileNumber;
        private String otp;
    }
}
