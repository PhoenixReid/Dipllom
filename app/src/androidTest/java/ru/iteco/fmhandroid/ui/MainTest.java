package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.Is.is;

import android.icu.text.SimpleDateFormat;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.uiautomator.UiDevice;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.util.Date;
import java.util.Locale;

import ru.Page.AddEditNewsPage;
import ru.Page.AuthPage;
import ru.Page.EditNewsPage;
import ru.Page.MainMenuPage;
import ru.Page.NewsPage;
import ru.Page.TopMenuPage;
import ru.iteco.fmhandroid.R;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void login() {
        new AuthPage().Auth("login2","password2");

        new TopMenuPage().MainMenuButton("Новости");

        new NewsPage().NewsEditClick();

        new EditNewsPage().AddNewsClick();

        String today = new SimpleDateFormat("dd", Locale.getDefault()).format(new Date());
        new AddEditNewsPage().AddNews("Зарплата", "Зарплата Аовы", "больше чем у вас", today);

        new TopMenuPage().MainMenuButton("Главная");
    }

    @After
    public void exit() {
        new TopMenuPage().MainMenuButton("Новости");

        new NewsPage().NewsEditClick();

        new EditNewsPage().DeleteNews("Зарплата Аовы");

        new TopMenuPage().Exit();
    }

    @Test
    public void expandTest() {

        new NewsPage().TextExists("Зарплата Аовы");

        new MainMenuPage().ExpandButtonClick();


        new NewsPage().TextNOExists("Зарплата Аовы");
    }

    @Test
    public void newsListTest() {

        new NewsPage().ClickNews("Зарплата Аовы");

        new NewsPage().TextExists("больше чем у вас");
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
