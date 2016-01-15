package com.rcta.appium.android.reproduceBug;

import com.rcta.appium.android.screenModal.MakeCallPages.CallScreenPages;
import com.rcta.appium.android.screenModal.MakeCallPages.DialPadPage;
import com.rcta.appium.android.screenModal.login.LoginPage;
import com.rcta.appium.android.screenModal.navigationBar.BottomNavigationBar;
import com.rcta.appium.android.utils.AppiumTestBase;
import com.rcta.appium.android.utils.Helper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FrequentlyMakeCallTest extends AppiumTestBase {
    private static final Logger LOG = LoggerFactory.getLogger(FrequentlyMakeCallTest.class);

    @Before
    public void setupData() {
      LoginPage.doFirstLogin("01213560002", null, "Test!123");
    }

    @After
    public void cleanUpData() {

    }

    @Test
    public void makeCallTest() {
        BottomNavigationBar.getDialPadButton().tap(1, 1);
        DialPadPage.pressKeyCode("102");
        DialPadPage.getCallButton().tap(1, 1);
        LOG.info("Star to make call");
        Helper.waitForElementAppear(15000);
        CallScreenPages.getEndCallButton().tap(1,1);
    }
}
