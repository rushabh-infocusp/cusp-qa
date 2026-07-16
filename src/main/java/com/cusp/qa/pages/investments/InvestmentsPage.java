package com.cusp.qa.pages.investments;

import com.cusp.qa.pages.BasePage;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class InvestmentsPage extends BasePage {

    private static final By INVESTMENTS_TITLE = AppiumBy.androidUIAutomator(
        "new UiSelector().textContains(\"Investments\")"
    );
    private static final By MUTUAL_FUNDS_TAB = AppiumBy.androidUIAutomator(
        "new UiSelector().text(\"Mutual Funds\")"
    );
    private static final By STOCKS_ETF_TAB = AppiumBy.androidUIAutomator(
        "new UiSelector().text(\"Stocks & ETFs\")"
    );
    private static final By NPS_TAB = AppiumBy.androidUIAutomator(
        "new UiSelector().text(\"NPS\")"
    );
    private static final By CONNECT_INVESTMENT_BTN = AppiumBy.androidUIAutomator(
        "new UiSelector().text(\"Connect Investment\")"
    );

    @Override
    public boolean isLoaded() {
        return isElementDisplayed(INVESTMENTS_TITLE);
    }

    public MutualFundsPage goToMutualFunds() {
        tap(MUTUAL_FUNDS_TAB);
        return new MutualFundsPage();
    }

    public StocksEtfPage goToStocksEtf() {
        tap(STOCKS_ETF_TAB);
        return new StocksEtfPage();
    }

    public NpsPage goToNps() {
        tap(NPS_TAB);
        return new NpsPage();
    }

    public void tapConnectInvestment() {
        tap(CONNECT_INVESTMENT_BTN);
    }
}
