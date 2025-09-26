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

import androidx.test.core.app.ActivityScenario;
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
import static org.hamcrest.Matchers.not;

import ru.iteco.fmhandroid.R;
import ru.Page.AuthPage;
import ru.utils.waitDisplayed;
import ru.Page.TopMenuPage;
import ru.Data.AuthData;
@LargeTest
@RunWith(AndroidJUnit4.class)
public class AuthTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);



    @Before
    public void setUp() {

        if (authPage.checkAuth()){
            topMenuPage.exit();
        }
    }


    AuthPage authPage = new AuthPage();
    TopMenuPage topMenuPage = new TopMenuPage();

    @Test
    public void authUnLoginTest() {
        authPage.auth(AuthData.unLogin, AuthData.password);

        authPage.authUnSucess();
    }

    @Test
    public void authUnPasswordTest() {
        authPage.auth(AuthData.login, AuthData.unPassword);

        authPage.authUnSucess();
    }

    @Test
    public void authNullLoginTest() {
        authPage.auth(AuthData.nullLogin, AuthData.password);

        authPage.authUnSucess();
    }

    @Test
    public void authNullPasswordTest() {
        authPage.auth(AuthData.login, AuthData.nullPassword);

        authPage.authUnSucess();
    }

    @Test
    public void authSpaceLoginTest() {
        authPage.auth(AuthData.spaseLogin, AuthData.password);

        authPage.authUnSucess();
    }

    @Test
    public void authSpacePasswordTest() {
        authPage.auth(AuthData.login, AuthData.spasePassword);

        authPage.authUnSucess();
    }

    @Test
    public void authTest() {

        authPage.auth(AuthData.login, AuthData.password);

        authPage.authSucess();

        topMenuPage.exit();
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
