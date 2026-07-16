package com.cusp.qa.pages.home;

import com.cusp.qa.pages.BasePage;
import com.cusp.qa.pages.bank_accounts.BankAccountsPage;
import com.cusp.qa.pages.investments.InvestmentsPage;
import com.cusp.qa.pages.settings.SettingsPage;
import com.cusp.qa.pages.transactions.TransactionsPage;
import com.cusp.qa.pages.wealth.WealthPage;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    // Bottom nav tabs
    private static final By HOME_TAB = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"com.infocusp.cuspmoney:id/nav_home\")"
    );
    private static final By WEALTH_TAB = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"com.infocusp.cuspmoney:id/nav_wealth\")"
    );
    private static final By TRANSACTIONS_TAB = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"com.infocusp.cuspmoney:id/nav_transactions\")"
    );
    private static final By INVESTMENTS_TAB = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"com.infocusp.cuspmoney:id/nav_investments\")"
    );
    private static final By SETTINGS_TAB = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"com.infocusp.cuspmoney:id/nav_settings\")"
    );

    // Home screen elements
    private static final By NET_WORTH_LABEL = AppiumBy.androidUIAutomator(
        "new UiSelector().textContains(\"Net Worth\")"
    );
    private static final By CONNECT_BANK_BTN = AppiumBy.androidUIAutomator(
        "new UiSelector().text(\"Connect Bank\")"
    );
    private static final By GREETING_TEXT = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"com.infocusp.cuspmoney:id/tv_greeting\")"
    );

    @Override
    public boolean isLoaded() {
        return isElementDisplayed(HOME_TAB) || isElementDisplayed(NET_WORTH_LABEL);
    }

    public WealthPage goToWealth() {
        tap(WEALTH_TAB);
        return new WealthPage();
    }

    public TransactionsPage goToTransactions() {
        tap(TRANSACTIONS_TAB);
        return new TransactionsPage();
    }

    public InvestmentsPage goToInvestments() {
        tap(INVESTMENTS_TAB);
        return new InvestmentsPage();
    }

    public SettingsPage goToSettings() {
        tap(SETTINGS_TAB);
        return new SettingsPage();
    }

    public BankAccountsPage tapConnectBank() {
        tap(CONNECT_BANK_BTN);
        return new BankAccountsPage();
    }

    public boolean isNetWorthVisible() {
        return isElementDisplayed(NET_WORTH_LABEL);
    }

    public String getGreeting() {
        return getText(GREETING_TEXT);
    }
}
