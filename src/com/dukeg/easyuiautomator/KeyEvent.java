package com.dukeg.easyuiautomator;

import android.os.RemoteException;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;
import android.view.InputDevice;
import android.view.KeyCharacterMap;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class KeyEvent {
    // Initialize UiDevice instance
    private UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

    //Long press key by keycode
    private boolean longPressKeyCode(int keyCode, long PressTime) {
        try {
            Field mUiAutomationBridge = Class.forName("android.support.test.uiautomator.UiDevice").getDeclaredField("mUiAutomationBridge");
            mUiAutomationBridge.setAccessible(true);

            Object bridgeObj = mUiAutomationBridge.get(mDevice);
            Method injectInputEvent = Class.forName("android.support.test.uiautomator.UiAutomatorBridge")
                    .getDeclaredMethod("injectInputEvent", android.view.InputEvent.class, boolean.class);

            final long eventTime = SystemClock.uptimeMillis();
            android.view.KeyEvent downEvent = new android.view.KeyEvent(eventTime, eventTime, android.view.KeyEvent.ACTION_DOWN,
                    keyCode, 0, 0, KeyCharacterMap.VIRTUAL_KEYBOARD, 0, 0,
                    InputDevice.SOURCE_KEYBOARD);

            if ((Boolean) injectInputEvent.invoke(bridgeObj, downEvent, true)) {
                SystemClock.sleep(PressTime);
                android.view.KeyEvent upEvent = new android.view.KeyEvent(eventTime, eventTime,
                        android.view.KeyEvent.ACTION_UP, keyCode, 0, 0,
                        KeyCharacterMap.VIRTUAL_KEYBOARD, 0, 0,
                        InputDevice.SOURCE_KEYBOARD);
                if ((Boolean) injectInputEvent.invoke(bridgeObj, upEvent, true)) {
                    return true;
                }
            }
        } catch (NoSuchMethodException | ClassNotFoundException | IllegalAccessException | NoSuchFieldException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return false;
    }

    //Press Home key
    public boolean pressHome() {
        return mDevice.pressHome();
    }

    //Press Menu key
    public boolean pressMenu() {
        return mDevice.pressMenu();
    }

    //Press Back key
    public boolean pressBack() {
        return mDevice.pressBack();
    }

    //Press RecentApps key
    public boolean pressRececntApps() throws RemoteException {
        return mDevice.pressRecentApps();
    }

    //Press Volume Up key
    public boolean pressVolumeUp() {
        return mDevice.pressKeyCode(android.view.KeyEvent.KEYCODE_VOLUME_UP);
    }

    //Press Volume Down key
    public boolean pressVolumeDown() {
        return mDevice.pressKeyCode(android.view.KeyEvent.KEYCODE_VOLUME_DOWN);
    }

    //Press Volume Mute key
    public boolean pressVolumeMute() {
        return mDevice.pressKeyCode(android.view.KeyEvent.KEYCODE_VOLUME_MUTE);
    }

    //Press Power Key
    public boolean pressPower() {
        return mDevice.pressKeyCode(android.view.KeyEvent.KEYCODE_VOLUME_MUTE);
    }

    //Long press power key
    public boolean longpressPower(long pressTime) {
        return longPressKeyCode(android.view.KeyEvent.KEYCODE_POWER,pressTime);
    }

    //Long press home key
    public boolean longpressHome(long pressTime) {
        return longPressKeyCode(android.view.KeyEvent.KEYCODE_HOME,pressTime);
    }

    //Screnn On(Doing nothint if screnn is already on)
    public void screenOn() throws RemoteException {
        mDevice.wakeUp();
    }
    //Screen Off(Doing nothint if screnn is already on)
    public void screenOff() throws RemoteException {
        mDevice.sleep();
    }
}
