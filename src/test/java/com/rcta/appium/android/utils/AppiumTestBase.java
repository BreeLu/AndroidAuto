package com.rcta.appium.android.utils;


import com.rcta.appium.android.config.AndroidConfig;
import com.rcta.appium.android.screenModal.login.LoginPage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static com.rcta.appium.android.config.AndroidConfig.getCurrentTestBrand;

public class AppiumTestBase {
    private AndroidDriver androidDriver;

    public
    @Rule
    TestName name = new TestName();

    @Before
    public void setup() throws InterruptedException {
        System.out.println("[AppiumTestBase Setup method] setup Appium driver");
        String packageName;
        switch (getCurrentTestBrand()) {
            case  "RCMobile" :
                packageName = "com.ringcentral.android";
                break;
            case "BT":
                packageName = "com.bt.cloudphone";
                break;
            case "ATT":
                packageName = "com.attofficeathand.android";
                break;
            case "Telus":
                packageName = "com.telusvoip.android";
                break;
            default:
                packageName = "com.ringcentral.android";
                break;
        }
        AndroidConfig.init();
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, AndroidConfig.getDeviceAppiumProperties().getPlatformVersion());
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, AndroidConfig.getDeviceAppiumProperties().getDeviceName());
        desiredCapabilities.setCapability(MobileCapabilityType.APPIUM_VERSION, AndroidConfig.getDeviceAppiumProperties().getAppiumVersion());
        desiredCapabilities.setCapability(MobileCapabilityType.UDID, AndroidConfig.getDeviceAppiumProperties().getUDID());
        desiredCapabilities.setCapability(MobileCapabilityType.APP, AndroidConfig.getDeviceAppiumProperties().getApp());
        desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "999999");
        desiredCapabilities.setCapability("name", name.getMethodName());
        desiredCapabilities.setCapability("appPackage", packageName);
        desiredCapabilities.setCapability("autoAcceptAlerts", false);
        try {
            androidDriver = new AndroidDriver(new URL(AndroidConfig.DEFAULT_WD_HUB_URL), desiredCapabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Helper.init(androidDriver);
        androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() throws IOException, InterruptedException {
        System.out.println("[AppiumTestBase Tear down method] quit driver");
        androidDriver.quit();
    }

}
