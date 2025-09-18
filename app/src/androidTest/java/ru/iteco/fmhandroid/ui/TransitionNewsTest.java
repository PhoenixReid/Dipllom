package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.uiautomator.UiDevice;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.uiautomator.UiDevice;


import ru.Page.AboutAppPage;
import ru.Page.AuthPage;
import ru.Page.MainMenuPage;
import ru.Page.QoutePage;
import ru.Page.TopMenuPage;
import ru.iteco.fmhandroid.R;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TransitionNewsTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void login() {
        new AuthPage().Auth("login2","password2");

        new TopMenuPage().MainMenuButton("Новости");
    }

    @After
    public void exit() {
        new TopMenuPage().Exit();
    }


    @Test
    public void quoteTest() {
        new TopMenuPage().Qoute();

        new QoutePage().QoutePoint();
    }

    @Test
    public void maimTest() {
        new TopMenuPage().MainMenuButton("Главная");

        new MainMenuPage().PointMain();
    }

    @Test // данный тест должен упасть так как кнопка О приложении недоступна
    public void aboutAppTest() {
        new TopMenuPage().MainMenuButton("О приложении");

        new AboutAppPage().AboutAppPoint();

        new AboutAppPage().AboutAppExit();
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                return false;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }


        };
    }
}
