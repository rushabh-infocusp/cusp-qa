package com.cusp.qa.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;

public class ConfigLoader {

    private static final Logger log = LogManager.getLogger(ConfigLoader.class);
    private static AppConfig instance;

    private ConfigLoader() {}

    public static AppConfig get() {
        if (instance == null) {
            synchronized (ConfigLoader.class) {
                if (instance == null) {
                    instance = load();
                }
            }
        }
        return instance;
    }

    private static AppConfig load() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try (InputStream is = ConfigLoader.class.getClassLoader().getResourceAsStream("config.yaml")) {
            if (is == null) throw new RuntimeException("config.yaml not found on classpath");
            AppConfig cfg = mapper.readValue(is, AppConfig.class);
            applyEnvOverrides(cfg);
            log.info("Configuration loaded successfully");
            return cfg;
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.yaml", e);
        }
    }

    private static void applyEnvOverrides(AppConfig cfg) {
        String deviceName = System.getenv("DEVICE_NAME");
        String udid = System.getenv("DEVICE_UDID");
        String appPath = System.getenv("APP_PATH");
        String serverUrl = System.getenv("APPIUM_SERVER_URL");
        String noReset = System.getenv("NO_RESET");

        if (deviceName != null) cfg.getAndroid().setDeviceName(deviceName);
        if (udid != null) cfg.getAndroid().setUdid(udid);
        if (appPath != null) cfg.getAndroid().setAppPath(appPath);
        if (serverUrl != null) cfg.getAppium().setServerUrl(serverUrl);
        if (noReset != null) cfg.getAndroid().setNoReset(Boolean.parseBoolean(noReset));
    }
}
