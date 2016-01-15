package com.rcta.appium.android.screenModal.MakeCallPages;

import com.rcta.appium.android.utils.Helper;
import io.appium.java_client.MobileElement;

public class CallScreenPages {
    public static MobileElement getEndCallButton() {
        return Helper.getElementById("btn_call_endcall");
    }

    public static MobileElement getSecureInidcateIcon() {
        return Helper.getElementById("secure_indicator_view");
    }

    public static MobileElement getVoipCallInfoCallCuration() {
        return Helper.getElementById("voip_call_info_call_duration");
    }
    /***************************
     * Callee Screen
     *************************/
    public static MobileElement getCallerNumOrName() {
        return Helper.getElementById("voip_call_info_caller_name_or_phone_number");
    }
    public static MobileElement getAnswerCallButton() {
        return Helper.getElementById("btn_call_answer");
    }
    public static MobileElement getSendToVoiceMailButton() {
        return Helper.getElementById("btn_call_send_to_voicemail");
    }

}
