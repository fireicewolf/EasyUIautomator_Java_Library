package com.dukeg.easyuiautomator;

import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.*;

import java.lang.reflect.InvocationTargetException;

public class TouchEvent {
    private UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

    //Click once action in pixels
    public boolean click(int x, int y) {
       if (mDevice.getDisplayWidth() / mDevice.getDisplayHeight() == 9/16) {
           return mDevice.click(x * mDevice.getDisplayWidth() / 1080, y * mDevice.getDisplayHeight() / 1920);
       }
       if (mDevice.getDisplayWidth() / mDevice.getDisplayHeight() == 16/9) {
           return mDevice.click(x * mDevice.getDisplayWidth() / 1920, y * mDevice.getDisplayHeight() / 1080);
       }
       else {
           return mDevice.click(x ,y );
       }
    }

    //Long Click action in pixels
    public boolean longClick(int x, int y, int pressTime) {
        int steps = pressTime / 5;
        if (mDevice.getDisplayWidth() / mDevice.getDisplayHeight() == 9/16) {
            return mDevice.swipe(x * mDevice.getDisplayWidth() / 1080, y * mDevice.getDisplayHeight() / 1920,
                    x * mDevice.getDisplayWidth() / 1080, y * mDevice.getDisplayHeight() / 1920, steps);
        }
        if (mDevice.getDisplayWidth() / mDevice.getDisplayHeight() == 16/9) {
            return mDevice.swipe(x * mDevice.getDisplayWidth() / 1920, y * mDevice.getDisplayHeight() / 1080,
                    x * mDevice.getDisplayWidth() / 1920, y * mDevice.getDisplayHeight() / 1080, steps);
        }
        else {
            return mDevice.swipe(x, y ,x ,y , steps);
        }
    }

    //Multi Click action in pixels
    public void multiClick(int x, int y, long interval, int times) {
        long timeout = Configurator.getInstance().getActionAcknowledgmentTimeout();
        Configurator.getInstance().setActionAcknowledgmentTimeout(interval);
        for (int i = 0; i < times; i++) {
            if (mDevice.getDisplayWidth() / mDevice.getDisplayHeight() == 9/16) {
                mDevice.click(x * mDevice.getDisplayWidth() / 1080, y * mDevice.getDisplayHeight() / 1920);
            }
            if (mDevice.getDisplayWidth() / mDevice.getDisplayHeight() == 16/9) {
                mDevice.click(x * mDevice.getDisplayWidth() / 1920, y * mDevice.getDisplayHeight() / 1080);
            }
            else {
                mDevice.click(x, y);
            }
        }
        Configurator.getInstance().setActionAcknowledgmentTimeout(timeout);
    }

    //Click once element by Object Resource ID
    public void clickByObjectResourceID(String resourceID, long timeout) {
        mDevice.wait(Until.findObject(By.res(resourceID)),timeout).click();
    }

    //Click once element by Object Text
    public void clickByObjectText(String text, long timeout) {
        mDevice.wait(Until.findObject(By.text(text)),timeout).click();
    }

    //Click once element by Object Text
    public void clickByObjectDesc(String desc, long timeout) {
        mDevice.wait(Until.findObject(By.desc(desc)),timeout).click();
    }

    //Swipe actions in pixels(5ms per step)
    public boolean swipe(int startX, int startY, int endX, int endY, int steps) {
        return mDevice.swipe(startX, startY, endX, endY, steps);
    }

    //Drag actions in pixels
    public boolean drag(int startX, int startY, int endX, int endY, int steps) {
        return mDevice.drag(startX, startY, endX, endY, steps);
    }

    //Scroll up screen
    public boolean scrollUp() {
        return mDevice.swipe(540 * mDevice.getDisplayWidth() / 1080, 1440 * mDevice.getDisplayHeight() / 1920, 540 * mDevice.getDisplayWidth() / 1080, 480 * mDevice.getDisplayHeight() / 1920, 100);
    }

    //Scroll down screen
    public boolean scrollDown() {
        return mDevice.swipe(540 * mDevice.getDisplayWidth() / 1080, 480 * mDevice.getDisplayHeight() / 1920, 540 * mDevice.getDisplayWidth() / 1080, 1440 * mDevice.getDisplayHeight() / 1920, 100);
    }

    //Scroll left screen
    public boolean scrollLeft() {
        return mDevice.swipe(810 * mDevice.getDisplayWidth() / 1080, 960 * mDevice.getDisplayHeight() / 1920, 270 * mDevice.getDisplayWidth() / 1080, 960 * mDevice.getDisplayHeight() / 1920, 100);
    }

    //Scroll right screen
    public boolean scrollRight() {
        return mDevice.swipe(270 * mDevice.getDisplayWidth() / 1080, 960 * mDevice.getDisplayHeight() / 1920, 810 * mDevice.getDisplayWidth() / 1080, 960 * mDevice.getDisplayHeight() / 1920, 100);
    }

}
