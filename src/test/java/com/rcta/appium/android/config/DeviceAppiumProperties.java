package com.rcta.appium.android.config;

public class DeviceAppiumProperties {
    private String platformVersion;
    private String deviceName;
    private String appiumVersion;
    private String UDID;
    private String app;
    private String deviceIP;

    public DeviceAppiumProperties() {
    }
    public String getPlatformVersion() {
        return platformVersion;
    }

    public void setPlatformVersion(String platformVersion) {
        this.platformVersion = platformVersion;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getAppiumVersion() {
        return appiumVersion;
    }

    public void setAppiumVersion(String appiumVersion) {
        this.appiumVersion = appiumVersion;
    }

    public String getUDID() {
        return UDID;
    }

    public void setUDID(String UDID) {
        this.UDID = UDID;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getDeviceIP() {
        return deviceIP;
    }

    public void setDeviceIP(String deviceIP) {
        this.deviceIP = deviceIP;
    }
}
