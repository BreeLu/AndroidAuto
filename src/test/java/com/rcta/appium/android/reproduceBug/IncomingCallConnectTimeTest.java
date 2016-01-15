package com.rcta.appium.android.reproduceBug;

import com.rcta.appium.android.screenModal.MakeCallPages.CallScreenPages;
import com.rcta.appium.android.screenModal.MakeCallPages.DialPadPage;
import com.rcta.appium.android.screenModal.login.LoginPage;
import com.rcta.appium.android.screenModal.navigationBar.BottomNavigationBar;
import com.rcta.appium.android.utils.Helper;
import com.rcta.appium.android.utils.TwoDevicesTestBase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IncomingCallConnectTimeTest extends TwoDevicesTestBase {
    private static final Logger LOG = LoggerFactory.getLogger(IncomingCallConnectTimeTest.class);

    @Test
    public void answerCallTest() {
        LoginPage.doLogin("01213560002", null, "Test!123");
        Helper.switchToDriver2();
        LoginPage.doLogin("01410800001", null, "Test!123");

        int loopTime = 200;
        while (loopTime > 0) {
            Helper.switchToDriver1();
            BottomNavigationBar.getDialPadButton().tap(1, 1);
            DialPadPage.pressKeyCode("01410800001");
            DialPadPage.getCallButton().tap(1, 1);
            LOG.info("Star to make call");

            Helper.switchToDriver2();
            int count = 10;
            while (CallScreenPages.getAnswerCallButton() == null && count > 0) {
                System.out.println("now is switch to device2,count  = "+count);
                Helper.waitForElementAppear(2000);
                count--;
            }
            if (CallScreenPages.getAnswerCallButton() != null) {
                CallScreenPages.getAnswerCallButton().click();
                LOG.info("Star to answer the call");
                Helper.waitForElementAppear(5000);
                if (CallScreenPages.getEndCallButton() != null) {
                    CallScreenPages.getEndCallButton().click();
                    LOG.info("End the call");
                }
            }
            loopTime--;
        }

    }
}
