package com.cusp.qa.pages.bank_accounts;

import com.cusp.qa.pages.BasePage;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BankAccountsPage extends BasePage {

    private static final By PAGE_TITLE = AppiumBy.androidUIAutomator(
        "new UiSelector().textContains(\"Bank Accounts\")"
    );
    private static final By ADD_BANK_BTN = AppiumBy.androidUIAutomator(
        "new UiSelector().text(\"Add Bank Account\")"
    );
    private static final By BANK_ACCOUNT_ITEM = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"com.infocusp.cuspmoney:id/item_bank_account\")"
    );
    private static final By FINVU_OPTION = AppiumBy.androidUIAutomator(
        "new UiSelector().text(\"Finvu\")"
    );
    private static final By NADL_OPTION = AppiumBy.androidUIAutomator(
        "new UiSelector().text(\"NADL\")"
    );
    private static final By ONE_MONEY_OPTION = AppiumBy.androidUIAutomator(
        "new UiSelector().text(\"One Money\")"
    );

    @Override
    public boolean isLoaded() {
        return isElementDisplayed(PAGE_TITLE);
    }

    public BankAccountsPage tapAddBank() {
        tap(ADD_BANK_BTN);
        return this;
    }

    public BankAccountsPage selectFinvu() {
        tap(FINVU_OPTION);
        return this;
    }

    public BankAccountsPage selectNadl() {
        tap(NADL_OPTION);
        return this;
    }

    public BankAccountsPage selectOneMoney() {
        tap(ONE_MONEY_OPTION);
        return this;
    }

    public List<WebElement> getBankAccounts() {
        return findElements(BANK_ACCOUNT_ITEM);
    }

    public int getBankAccountCount() {
        return findElements(BANK_ACCOUNT_ITEM).size();
    }
}
