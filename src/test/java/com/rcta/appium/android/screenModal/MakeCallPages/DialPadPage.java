package com.rcta.appium.android.screenModal.MakeCallPages;

import com.rcta.appium.android.utils.Helper;
import io.appium.java_client.MobileElement;

public class DialPadPage {
    public static void pressKeyCode(String phoneNum) {
        char[] keys = phoneNum.toCharArray();
        for (char c : keys) {
            if (c == '+') {
                getNumberButton(getKeyCodeId("0")).tap(1, 1);
            } else {
                getNumberButton(getKeyCodeId(String.valueOf(c - 48))).tap(1, 1);
            }
        }
    }

    public static MobileElement getCallButton() {
        return Helper.getElementById("btnCall");
    }

    private static String getKeyCodeId(String number) {
        String keyCodeId = null;
        switch (number) {
            case "1":
                keyCodeId = "one";
                break;
            case "2":
                keyCodeId = "two";
                break;
            case "3":
                keyCodeId = "three";
                break;
            case "4":
                keyCodeId = "four";
                break;
            case "5":
                keyCodeId = "five";
                break;
            case "6":
                keyCodeId = "six";
                break;
            case "7":
                keyCodeId = "seven";
                break;
            case "8":
                keyCodeId = "eight";
                break;
            case "9":
                keyCodeId = "nine";
                break;
            case "0":
                keyCodeId = "zero";
                break;
        }
        return keyCodeId;
    }

    private static MobileElement getNumberButton(String id) {
        return Helper.getElementById(id);
    }


}
