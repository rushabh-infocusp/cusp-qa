package com.cusp.qa.launch;

import com.cusp.qa.base.BaseTest;
import com.cusp.qa.driver.DriverManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AppLaunchTest extends BaseTest {

    @Test(description = "Verify Cusp app launches and driver session is created")
    public void testAppLaunch() {
        log.info("App launched. Current activity: {}", DriverManager.getDriver().currentActivity());
        String activity = DriverManager.getDriver().currentActivity();
        Assert.assertNotNull(activity, "App should have a current activity after launch");
        log.info("App is running. Activity: {}", activity);
    }
}
