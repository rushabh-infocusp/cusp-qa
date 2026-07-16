package com.cusp.qa.pages.family;

import com.cusp.qa.pages.BasePage;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FamilyMemberPage extends BasePage {

    private static final By PAGE_TITLE = AppiumBy.androidUIAutomator(
        "new UiSelector().textContains(\"Family\")"
    );
    private static final By ADD_MEMBER_BTN = AppiumBy.androidUIAutomator(
        "new UiSelector().text(\"Add Member\")"
    );
    private static final By MEMBER_ITEM = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"com.infocusp.cuspmoney:id/item_family_member\")"
    );
    private static final By MEMBER_NAME_INPUT = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"com.infocusp.cuspmoney:id/et_member_name\")"
    );
    private static final By MEMBER_MOBILE_INPUT = AppiumBy.androidUIAutomator(
        "new UiSelector().resourceId(\"com.infocusp.cuspmoney:id/et_member_mobile\")"
    );
    private static final By SAVE_BTN = AppiumBy.androidUIAutomator(
        "new UiSelector().text(\"Save\")"
    );

    @Override
    public boolean isLoaded() {
        return isElementDisplayed(PAGE_TITLE);
    }

    public FamilyMemberPage tapAddMember() {
        tap(ADD_MEMBER_BTN);
        return this;
    }

    public FamilyMemberPage enterMemberDetails(String name, String mobile) {
        typeText(MEMBER_NAME_INPUT, name);
        typeText(MEMBER_MOBILE_INPUT, mobile);
        hideKeyboard();
        return this;
    }

    public FamilyMemberPage tapSave() {
        tap(SAVE_BTN);
        return this;
    }

    public List<WebElement> getMemberItems() {
        return findElements(MEMBER_ITEM);
    }

    public int getMemberCount() {
        return findElements(MEMBER_ITEM).size();
    }
}
