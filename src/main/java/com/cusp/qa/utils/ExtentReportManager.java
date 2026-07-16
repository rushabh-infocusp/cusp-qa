package com.cusp.qa.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    private ExtentReportManager() {}

    public static ExtentReports getInstance() {
        if (extent == null) {
            synchronized (ExtentReportManager.class) {
                if (extent == null) {
                    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
                    String reportPath = "test-output/CuspReport_" + timestamp + ".html";

                    ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
                    spark.config().setTheme(Theme.DARK);
                    spark.config().setDocumentTitle("Cusp Money - Test Report");
                    spark.config().setReportName("Cusp Android Automation Results");
                    spark.config().setTimeStampFormat("yyyy-MM-dd HH:mm:ss");

                    extent = new ExtentReports();
                    extent.attachReporter(spark);
                    extent.setSystemInfo("App", "Cusp Money (com.infocusp.cuspmoney)");
                    extent.setSystemInfo("Platform", "Android");
                    extent.setSystemInfo("Automation", "Appium + UIAutomator2");
                }
            }
        }
        return extent;
    }

    public static ExtentTest getTest() {
        return testThread.get();
    }

    public static void setTest(ExtentTest test) {
        testThread.set(test);
    }

    public static void flush() {
        if (extent != null) extent.flush();
    }
}
