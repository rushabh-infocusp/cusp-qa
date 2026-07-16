package com.cusp.qa.pages.cashflow;

import com.cusp.qa.pages.BasePage;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class CashflowPage extends BasePage {

    private static final By PAGE_TITLE = AppiumBy.androidUIAutomator(
        "new UiSelector().textContains(\"Cash Flow\")"
    );
    private static final By INCOME_LABEL = AppiumBy.androidUIAutomator(
        "new UiSelector().textContains(\"Income\")"
    );
    private static final By EXPENSE_LABEL = AppiumBy.androidUIAutomator(
        "new UiSelector().textContains(\"Expense\")"
    );
    private static final By RECURRING_TAB = AppiumBy.androidUIAutomator(
        "new UiSelector().text(\"Recurring\")"
    );

    @Override
    public boolean isLoaded() {
        return isElementDisplayed(PAGE_TITLE);
    }

    public boolean isIncomeVisible() {
        return isElementDisplayed(INCOME_LABEL);
    }

    public boolean isExpenseVisible() {
        return isElementDisplayed(EXPENSE_LABEL);
    }

    public CashflowPage goToRecurring() {
        tap(RECURRING_TAB);
        return this;
    }
}
