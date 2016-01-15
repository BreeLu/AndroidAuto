package com.rcta.appium.android.screenModal.login;

import com.rcta.appium.android.utils.Helper;
import io.appium.java_client.MobileElement;

public class LoginPage {
    public static MobileElement getPhoneNumberField() {
        return Helper.getElementById("phone");
    }

    public static MobileElement getExtensionField() {
        return Helper.getElementById("phone");
    }
    public static MobileElement getPasswordField() {
        return Helper.getElementById("password");
    }

    public static MobileElement getLoginButton() {
        return Helper.getElementById("btnSignIn");
    }

    public static MobileElement getForgetPasswordButton() {
        return Helper.getElementById("passwordRecoveryLink");
    }

    public static void doFirstLogin(String phone, String extension, String password) {
        doLogin(phone, extension, password);
        WhatNewPagesAndTips.skipWhatNewPages();
        WhatNewPagesAndTips.removeTips();
    }
    public static void doLogin(String phone, String extension, String password) {
        MobileElement phoneNumberField = getPhoneNumberField();
        phoneNumberField.tap(1, 1);
        phoneNumberField.sendKeys(phone);
        if (extension != null) {
            MobileElement extensionField = getExtensionField();
            extensionField.tap(1, 1);
            extensionField.sendKeys(extension);
        }
        MobileElement passwordField = getPasswordField();
        passwordField.tap(1, 1);
        passwordField.sendKeys(password);
        MobileElement loginButton = getLoginButton();
        loginButton.click();
        Helper.waitForElementAppear(5000);
    }
}
