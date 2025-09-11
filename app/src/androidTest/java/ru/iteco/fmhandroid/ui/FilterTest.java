package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.Until;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import ru.iteco.fmhandroid.R;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class FilterTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void login() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.main_menu_image_button))
                .perform(click());
        onView(withText("Новости")).perform(click());

    }

    @Test
    public void filterNoDateTest() {
        onView(withId(R.id.filter_news_material_button))
                .perform(click());

        onView(withId(R.id.news_item_category_text_auto_complete_text_view))
                .perform(click());

        onView(withText("Объявление"))
                .inRoot(isPlatformPopup())
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withId(R.id.filter_button))
                .perform(click());

        onView(withText("cat")).check(matches(isDisplayed()));
        onView(withText("title")).check(matches(isDisplayed()));
        onView(withText("mangosteens")).check(matches(isDisplayed()));
        onView(withText("Lobster")).check(matches(isDisplayed()));
        onView(withText("Whiting Wild Rice")).check(matches(isDisplayed()));
    }

    @Test
    public void filterTest() {

        onView(withId(R.id.filter_news_material_button)).perform(click());

        // Выбрать категорию
        onView(withId(R.id.news_item_category_text_auto_complete_text_view)).perform(click());
        DataInteraction categoryItem = onData(org.hamcrest.Matchers.anything())
                .inAdapterView(
                        org.hamcrest.Matchers.instanceOf(android.widget.ListView.class)
                ).atPosition(0);
        categoryItem.perform(click());

        // Установить начальную дату
        onView(withId(R.id.news_item_publish_date_start_text_input_edit_text)).perform(click());
        onView(withContentDescription("Прошлый месяц")).perform(click());
        try {
            onView(withText("ОК"))
                    .inRoot(isDialog())
                    .check(matches(isDisplayed()))
                    .perform(click());
        } catch (NoMatchingViewException e) {
            // Диалог мог уже закрыться
        }

        // Установить конечную дату
        onView(withId(R.id.news_item_publish_date_end_text_input_edit_text)).perform(click());
        try {
            onView(withText("ОК"))
                    .inRoot(isDialog())
                    .check(matches(isDisplayed()))
                    .perform(click());
        } catch (NoMatchingViewException e) {
            // Диалог мог уже закрыться
        }

        // Применить фильтр
        onView(withId(R.id.filter_button)).perform(click());
    }
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
