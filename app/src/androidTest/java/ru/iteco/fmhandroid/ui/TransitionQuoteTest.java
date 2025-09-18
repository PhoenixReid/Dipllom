package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;

import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import ru.Page.AboutAppPage;
import ru.Page.AuthPage;
import ru.Page.MainMenuPage;
import ru.Page.NewsPage;
import ru.Page.TopMenuPage;
import ru.iteco.fmhandroid.R;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TransitionQuoteTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void login() {
        new AuthPage().Auth("login2","password2");

        new TopMenuPage().Qoute();
    }

    @After
    public void exit() {
        new TopMenuPage().Exit();
    }

    @Test
    public void newsTest() {
        new TopMenuPage().MainMenuButton("Новости");

        new NewsPage().PointNews();
    }

    @Test
    public void aboutAppTest() {
        new TopMenuPage().MainMenuButton("О приложении");

        new AboutAppPage().AboutAppPoint();
    }

    @Test
    public void maimTest() {
        new TopMenuPage().MainMenuButton("Главная");

        new MainMenuPage().PointMain();
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
