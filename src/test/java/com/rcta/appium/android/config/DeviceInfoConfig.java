package com.rcta.appium.android.config;

public class DeviceInfoConfig {
    public enum DEVICE_CONFIG {
        //     |   DEVICE_IP    |                    UDID                   |  PLATFORM VERSION |  DEVICE NAME  | APPIUM VERSION
        DEVICE1("10.32.58.58", "04cbfaa51986510a",        "5.0.1",       "Nexus 4",    "1.4.8"),
        DEVICE2("10.36.0.223", "4d003bbb4f223127",        "4.4.2",       "Galaxy S5",    "1.4.8"),
        DEVICE3("10.36.0.219", "HC3C1WG01239",        "4.4.2",       "HTC 8088",    "1.4.8");

        public String deviceIp;
        public String udid;
        public String platformVersion;
        public String deviceName;
        public String appiumVersion;

        DEVICE_CONFIG(String deviceIp, String udid, String platformVersion, String deviceName, String appiumVersion) {
            this.deviceIp = deviceIp;
            this.udid = udid;
            this.platformVersion = platformVersion;
            this.deviceName = deviceName;
            this.appiumVersion = appiumVersion;
        }
    }
}
