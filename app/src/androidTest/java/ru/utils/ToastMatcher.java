// File: src/androidTest/java/ru/utils/ToastMatcher.java

package ru.utils;

import android.os.IBinder;
import android.view.WindowManager;

import androidx.test.espresso.Root;


import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class ToastMatcher extends TypeSafeMatcher<Root> {

    private final int maximumRetries;
    private int currentFailures = 0;

    public ToastMatcher(int maximumRetries) {
        this.maximumRetries = maximumRetries;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("is toast");
    }

    @Override
    protected boolean matchesSafely(Root root) {
        int type = root.getWindowLayoutParams().get().type;

        if (type == WindowManager.LayoutParams.TYPE_TOAST ||
                type == WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY) {

            IBinder windowToken = root.getDecorView().getWindowToken();
            IBinder appToken = root.getDecorView().getApplicationWindowToken();

            if (windowToken != null && appToken != null && windowToken.equals(appToken)) {
                return true;
            }
        }

        return ++currentFailures >= maximumRetries;
    }
}