package com.rcta.appium.android.screenModal.navigationBar;

import com.rcta.appium.android.utils.Helper;
import io.appium.java_client.MobileElement;

public class BottomNavigationBar {
    public static MobileElement getMessageButton() {
        return Helper.getElementByName("Messages");
    }
    public static MobileElement getCallLogButton() {
        return Helper.getElementByName("Call Log");
    }
    public static MobileElement getMoreMenuButton() {
        return Helper.getElementByName("layout_plus");
    }

    public static MobileElement getContactsButton() {
        return Helper.getElementByName("Contacts");
    }
    public static MobileElement getDialPadButton() {
        return Helper.getElementByName("Dial Pad");
    }
}
