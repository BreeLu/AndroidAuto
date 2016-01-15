package com.rcta.appium.android.utils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Helper {
    private static AndroidDriver driver;
    private static AndroidDriver driver1;
    private static AndroidDriver driver2;
    private static WebDriverWait driverWait;

    public static void init(AndroidDriver androidDriver) {
        driver = androidDriver;
        int timeoutInSeconds = 60;
        driverWait = new WebDriverWait(driver, timeoutInSeconds);
    }

    public static void init(AndroidDriver androidDriver1, AndroidDriver androidDriver2) {
        driver1 = androidDriver1;
        driver2 = androidDriver2;
    }

    public static void switchToDriver1() {
        driver = driver1;
    }

    public static void switchToDriver2() {
        driver = driver2;
    }

    public static boolean isInstallApp(String packageName) {
        return driver.isAppInstalled(packageName);
    }

    public static MobileElement findById(String accessbilityId) {
        WebElement element = driver.findElement(By.id(accessbilityId));
        return (MobileElement) element;
    }

    public static MobileElement findByName(String name) {
        return (MobileElement) driver.findElement(By.name(name));
    }

    public static MobileElement getElementByName(String name) {
        MobileElement element = null;
        try {
            element = findByName(name);
        } catch (Exception e) {
            System.out.println(String.format("[WARN]Cannot find %s in the screen", name));
        }
        return element;
    }

    public static MobileElement getElementById(String Id) {
        MobileElement element = null;
        try {
            element = findById(Id);
        } catch (Exception e) {
            System.out.println(String.format("[WARN]Cannot find %s in the screen", Id));
        }
        return element;
    }

    public static MobileElement getElementByXPath(String path) {
        MobileElement element = null;
        try {
            element = findByXPath(path);
        } catch (Exception e) {
            System.out.println(String.format("[WARN]Cannot find %s in the screen", path));
        }
        return element;
    }


    public static List<MobileElement> findElemsByName(String name) {
        List<MobileElement> elemsList = driver.findElementsByName(name);
        return elemsList;
    }

    public static MobileElement findByXPath(String xpath) {
        return (MobileElement) driver.findElement(By.xpath(xpath) );
    }

    public static List<MobileElement> findElementsByClassName(String className) {
        return driver.findElementsByClassName(className);
    }

    public static void waitForElementAppear(long millionSec) {
        try {
            new Thread().sleep(millionSec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void back() {
        driver.navigate().back();
    }
}
