package com.cusp.qa.pages.transactions;

import com.cusp.qa.pages.BasePage;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TransactionsPage extends BasePage {

    private static final By TRANSACTIONS_TITLE = AppiumBy.androidUIAutomator(
        "new UiSelector().textContains(\"Transactions\")"
    );
    private static final By SEARCH_ICON = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"com.infocusp.cuspmoney:id/iv_search\")"
    );
    private static final By SEARCH_INPUT = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"com.infocusp.cuspmoney:id/et_search\")"
    );
    private static final By FILTER_ICON = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"com.infocusp.cuspmoney:id/iv_filter\")"
    );
    private static final By TRANSACTION_ITEM = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"com.infocusp.cuspmoney:id/item_transaction\")"
    );
    private static final By BOOKMARK_ICON = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"com.infocusp.cuspmoney:id/iv_bookmark\")"
    );
    private static final By BULK_EDIT_BTN = AppiumBy.androidUIAutomator(
        "new UiSelector().text(\"Bulk Edit\")"
    );
    private static final By EXCLUDE_TOGGLE = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"com.infocusp.cuspmoney:id/toggle_exclude\")"
    );
    private static final By ADD_NOTE_BTN = AppiumBy.androidUIAutomator(
        "new UiSelector().text(\"Add Note\")"
    );
    private static final By NOTE_INPUT = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"com.infocusp.cuspmoney:id/et_note\")"
    );

    @Override
    public boolean isLoaded() {
        return isElementDisplayed(TRANSACTIONS_TITLE);
    }

    public TransactionsPage tapSearch() {
        tap(SEARCH_ICON);
        return this;
    }

    public TransactionsPage searchTransaction(String query) {
        tap(SEARCH_ICON);
        typeText(SEARCH_INPUT, query);
        hideKeyboard();
        return this;
    }

    public TransactionsPage tapFilter() {
        tap(FILTER_ICON);
        return this;
    }

    public List<WebElement> getTransactionItems() {
        return findElements(TRANSACTION_ITEM);
    }

    public int getTransactionCount() {
        return findElements(TRANSACTION_ITEM).size();
    }

    public TransactionsPage tapFirstTransaction() {
        List<WebElement> items = getTransactionItems();
        if (!items.isEmpty()) items.get(0).click();
        return this;
    }

    public TransactionsPage bookmarkTransaction(int index) {
        List<WebElement> items = findElements(BOOKMARK_ICON);
        if (index < items.size()) items.get(index).click();
        return this;
    }

    public TransactionsPage tapBulkEdit() {
        tap(BULK_EDIT_BTN);
        return this;
    }

    public TransactionsPage toggleExclude() {
        tap(EXCLUDE_TOGGLE);
        return this;
    }

    public TransactionsPage addNote(String note) {
        tap(ADD_NOTE_BTN);
        typeText(NOTE_INPUT, note);
        hideKeyboard();
        return this;
    }
}
