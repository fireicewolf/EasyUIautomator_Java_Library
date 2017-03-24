package com.dukeg.easyuiautomator;

import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;

public class CheckEvent {
    private static UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

    //Check Screen on or off
    public static boolean checkScreenOn() throws RemoteException {
        return mDevice.isScreenOn();
    }
}
