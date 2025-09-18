package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import ru.iteco.fmhandroid.R;
import ru.Page.AuthPage;
import ru.utils.waitDisplayed;
import ru.Page.TopMenuPage;
@LargeTest
@RunWith(AndroidJUnit4.class)
public class AuthTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Test
    public void authUnLoginTest() {
        new AuthPage().Auth("login1", "password2");

        new AuthPage().AuthUnSucess();
    }

    @Test
    public void authUnPasswordTest() {
        new AuthPage().Auth("login2", "password1");

        new AuthPage().AuthUnSucess();
    }

    @Test
    public void authNullLoginTest() {
        new AuthPage().Auth("", "password2");

        new AuthPage().AuthUnSucess();
    }

    @Test
    public void authNullPasswordTest() {
        new AuthPage().Auth("login2", "");

        new AuthPage().AuthUnSucess();
    }

    @Test
    public void authSpaceLoginTest() {
        new AuthPage().Auth("   ", "password2");

        new AuthPage().AuthUnSucess();
    }

    @Test
    public void authSpacePasswordTest() {
        new AuthPage().Auth("login2", "   ");

        new AuthPage().AuthUnSucess();
    }

    @Test
    public void authTest() {

        new AuthPage().Auth("login2", "password2");

        onView(isRoot()).perform(new waitDisplayed(R.id.authorization_image_button, 5000));

        new AuthPage().AuthSucess();

        new TopMenuPage().Exit();
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
