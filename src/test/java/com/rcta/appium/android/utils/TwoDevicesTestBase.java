package com.rcta.appium.android.utils;

import com.rcta.appium.android.config.AndroidConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static com.rcta.appium.android.config.AndroidConfig.getCurrentTestBrand;

public class TwoDevicesTestBase {
    private AndroidDriver androidDriver1;
    private AndroidDriver androidDriver2;

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
        AndroidConfig.initTwoDevices();
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

        DesiredCapabilities desiredCapabilities2 = new DesiredCapabilities();
        desiredCapabilities2.setCapability(MobileCapabilityType.PLATFORM_VERSION, AndroidConfig.getDeviceAppiumProperties2().getPlatformVersion());
        desiredCapabilities2.setCapability(MobileCapabilityType.DEVICE_NAME, AndroidConfig.getDeviceAppiumProperties2().getDeviceName());
        desiredCapabilities2.setCapability(MobileCapabilityType.APPIUM_VERSION, AndroidConfig.getDeviceAppiumProperties2().getAppiumVersion());
        desiredCapabilities2.setCapability(MobileCapabilityType.UDID, AndroidConfig.getDeviceAppiumProperties2().getUDID());
        desiredCapabilities2.setCapability(MobileCapabilityType.APP, AndroidConfig.getDeviceAppiumProperties2().getApp());
        desiredCapabilities2.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "999999");
        desiredCapabilities2.setCapability("name", name.getMethodName());
        desiredCapabilities2.setCapability("appPackage", packageName);
        desiredCapabilities2.setCapability("autoAcceptAlerts", false);
        try {
            androidDriver1 = new AndroidDriver(new URL(AndroidConfig.DEFAULT_WD_HUB_URL), desiredCapabilities);
            androidDriver2 = new AndroidDriver(new URL(AndroidConfig.DEFAULT_WD_HUB_URL_2), desiredCapabilities2);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Helper.init(androidDriver1,androidDriver2);
        androidDriver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        androidDriver2.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Helper.switchToDriver1();
    }

    @After
    public void tearDown() throws IOException, InterruptedException {
        System.out.println("[AppiumTestBase Tear down method] quit driver");
        Helper.switchToDriver1();
        androidDriver1.quit();
        Helper.switchToDriver2();
        androidDriver2.quit();
    }
}
