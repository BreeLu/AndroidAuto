package com.rcta.appium.android.config;

import com.google.gson.Gson;

import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

public class AndroidConfig {
    private static DeviceAppiumProperties deviceAppiumProperties;
    private static DeviceAppiumProperties deviceAppiumProperties2;
    public static final String DEFAULT_WD_HUB_URL = "http://127.0.0.1:4723/wd/hub";
    public static final String DEFAULT_WD_HUB_URL_2 = "http://127.0.0.1:4725/wd/hub";
    private static boolean isInitialized = false;
    private static boolean isInitializedTwoDevice = false;

    public static DeviceAppiumProperties getDeviceAppiumProperties() {
        return deviceAppiumProperties;
    }

    public static DeviceAppiumProperties getDeviceAppiumProperties2() {
        return deviceAppiumProperties2;
    }

    public static void init() {

        if (!isInitialized) {
            deviceAppiumProperties = new DeviceAppiumProperties();
            try {
                selectFirstAvailableDeviceConfig();
                selectAppPathForDeviceConfig();
            } catch (Exception e) {
                e.printStackTrace();
            }
            isInitialized = true;
        }
    }

    public static void initTwoDevices() {
        if (!isInitializedTwoDevice) {
            deviceAppiumProperties = new DeviceAppiumProperties();
            deviceAppiumProperties2 = new DeviceAppiumProperties();
            try {
                selectTwoAvailableDeviceConfig();
                selectAppPathForDeviceConfig();
                System.out.println(new Gson().toJson(deviceAppiumProperties));
                System.out.println(new Gson().toJson(deviceAppiumProperties2));
            } catch (Exception e) {
                e.printStackTrace();
            }
            isInitializedTwoDevice = true;
        }
    }

    public static String getCurrentTestBrand() {
//        String currentBrand = "RCMobile";
//        String currentBrand = "TELUS";
//        String currentBrand = "ATT";
        String currentBrand = "BT";
        if (System.getProperty("CurrentBrand") != null) {
            currentBrand = System.getProperty("CurrentBrand");
            return currentBrand;
        }
        return currentBrand;
    }

    private static void selectFirstAvailableDeviceConfig() throws Exception {
        if (findLocalDevices().size() == 0)
            throw new Exception("******** ERROR: No available devices ********");

        List<DeviceInfoConfig.DEVICE_CONFIG> existingDeviceList = new ArrayList<>();
        existingDeviceList.add(DeviceInfoConfig.DEVICE_CONFIG.DEVICE1);

        for (DeviceInfoConfig.DEVICE_CONFIG cfg : existingDeviceList) {
            String deviceID = findLocalDevices().get(0);
            System.out.println("deviceID = " + deviceID);
            if (deviceID.equals(cfg.udid)) {
                deviceAppiumProperties.setUDID(cfg.udid);
                deviceAppiumProperties.setPlatformVersion(cfg.platformVersion);
                deviceAppiumProperties.setDeviceName(cfg.deviceName);
                deviceAppiumProperties.setAppiumVersion(cfg.appiumVersion);
            }
            System.out.println(new Gson().toJson(deviceAppiumProperties));
        }
    }

    private static void selectTwoAvailableDeviceConfig() throws Exception {
        if (findLocalDevices().size() == 0)
            throw new Exception("******** ERROR: No available devices ********");

        List<DeviceInfoConfig.DEVICE_CONFIG> existingDeviceList = new ArrayList<>();
        existingDeviceList.add(DeviceInfoConfig.DEVICE_CONFIG.DEVICE1);
        existingDeviceList.add(DeviceInfoConfig.DEVICE_CONFIG.DEVICE2);
        existingDeviceList.add(DeviceInfoConfig.DEVICE_CONFIG.DEVICE3);

        for (int i = 0; i < 2; i++) {
            String deviceID = findLocalDevices().get(i);
            System.out.println("deviceID = " + deviceID);
            for (DeviceInfoConfig.DEVICE_CONFIG cfg : existingDeviceList) {
                if (deviceID.equals(cfg.udid)) {
                    if (i == 0) {
                        deviceAppiumProperties.setUDID(cfg.udid);
                        deviceAppiumProperties.setPlatformVersion(cfg.platformVersion);
                        deviceAppiumProperties.setDeviceName(cfg.deviceName);
                        deviceAppiumProperties.setAppiumVersion(cfg.appiumVersion);
                    } else {
                        deviceAppiumProperties2.setUDID(cfg.udid);
                        deviceAppiumProperties2.setPlatformVersion(cfg.platformVersion);
                        deviceAppiumProperties2.setDeviceName(cfg.deviceName);
                        deviceAppiumProperties2.setAppiumVersion(cfg.appiumVersion);
                    }
                }
            }
        }
    }

    public static void selectAppPathForDeviceConfig() {
        String userDir = System.getProperty("user.dir");
        File classpathRoot = new File(userDir);
        File app = new File(classpathRoot, "BT_8.0.0.5.50_XIA_UP_Automation.apk");
        deviceAppiumProperties.setApp(app.getAbsolutePath());
        deviceAppiumProperties2.setApp(app.getAbsolutePath());
    }

    private static String searchForLocalAppName(String folderName) {
        String FILE_TEXT_EXT = ".apk";

        GenericExtFilter filter = new GenericExtFilter(FILE_TEXT_EXT);
        File dir = new File(folderName);

        if (!dir.isDirectory()) {
            System.out.println("Directory does not exists: " + folderName);
            return null;
        }

        String[] list = dir.list(filter);
        if (list.length == 0) {
            System.out.println("no files end with : " + FILE_TEXT_EXT);
            return null;
        }

        for (String filename : list) {
            if (filename.contains(getCurrentTestBrand())) {
                System.out.println("file name = " + filename);
                return filename;
            }
        }
        return null;
    }

    private static class GenericExtFilter implements FilenameFilter {

        private String ext;

        public GenericExtFilter(String ext) {
            this.ext = ext;
        }

        public boolean accept(File dir, String name) {
            return (name.endsWith(ext));
        }

    }

    private static List<String> findLocalDevices() throws Exception {
        return runShell("adb devices");
    }

    public static List runShell(String shStr) throws Exception {
        List<String> strList = new ArrayList();

        Process process;
        process = Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", shStr}, null, null);
        InputStreamReader ir = new InputStreamReader(process
                .getInputStream());
        LineNumberReader input = new LineNumberReader(ir);
        String line;
        process.waitFor();
        while ((line = input.readLine()) != null) {
            if (!line.contains("List of devices attached")) {
                strList.add(line.replace("\tdevice", ""));
            }
        }
        return strList;
    }

}
