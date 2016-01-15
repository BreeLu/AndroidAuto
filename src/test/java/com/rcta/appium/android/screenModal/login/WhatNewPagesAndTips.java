package com.rcta.appium.android.screenModal.login;

import com.rcta.appium.android.utils.Helper;
import io.appium.java_client.MobileElement;

public class WhatNewPagesAndTips {
    public static MobileElement getAlertTitle() {
        return Helper.getElementById("alertTitle");
    }

    public static MobileElement getDeclineBtn() {
        return Helper.getElementById("button2");
    }

    public static MobileElement getAcceptBtn() {
        return Helper.getElementById("button1");
    }

    public static MobileElement getSkipBtn() {
        return Helper.getElementById("whats_new_button_left");
    }

    public static MobileElement getSetPhoneNumber() {
        return Helper.getElementById("username_edit");
    }

    public static MobileElement getPhoneNumberSaveBtn() {
        return Helper.getElementById("saveBtn");
    }

    public static void skipWhatNewPages() {
        MobileElement SummaryOfLegalTermsPage = getAcceptBtn();
        if (SummaryOfLegalTermsPage != null) {
            SummaryOfLegalTermsPage.click();
            MobileElement emergencyPage = getAcceptBtn();
            if (emergencyPage != null) {
                emergencyPage.click();
            }
            getSkipBtn().click();
            MobileElement setPhoneNumber = getSetPhoneNumber();
            setPhoneNumber.tap(1, 1);
            setPhoneNumber.sendKeys("01216546576");
            getPhoneNumberSaveBtn().click();
        }
    }

    public static void removeTips() {
        MobileElement btnClose = Helper.getElementById("btnClose");
        if (btnClose != null) {
            btnClose.click();
        }
        MobileElement title_bar_photo = Helper.getElementById("title_bar_photo");
        if (title_bar_photo != null) {
            title_bar_photo.click();
        }
        MobileElement btnClose2 = Helper.getElementById("btnClose");
        if (btnClose2 != null) {
            btnClose2.click();
        }
        Helper.back();
        MobileElement title = Helper.getElementById("title");
        if (title != null) {
            title.click();
        }
        Helper.back();
        MobileElement moreBtn = Helper.getElementById("layout_plus");
        if (moreBtn != null) {
            moreBtn.tap(1,1);
            moreBtn.tap(1,1);
        }


    }

}
