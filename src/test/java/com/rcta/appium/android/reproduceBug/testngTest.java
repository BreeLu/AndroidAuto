package com.rcta.appium.android.reproduceBug;


import org.testng.Assert;
import org.testng.annotations.Test;

public class testngTest {
    @Test
    public void testA() {
        System.out.println("****Use testNG frameWork*****");
        Assert.assertTrue(true,"this is the first testNG class");
    }
}
