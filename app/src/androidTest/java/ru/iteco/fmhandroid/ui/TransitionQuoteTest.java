package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import org.junit.Before;
import org.junit.Test;

import ru.iteco.fmhandroid.R;

public class TransitionQuoteTest {
    @Before
    public void login() {
        onView(withId(R.id.our_mission_image_button))
                .perform(click());
        onView(withText("Главное - жить любя")).check(matches(isDisplayed()));
    }

    @Test
    public void newsTest() {
        onView(withId(R.id.main_menu_image_button))
                .perform(click());
        onView(withText("Новости")).perform(click());

        onView(withText("Праздник")).check(matches(isDisplayed()));
    }

    @Test
    public void aboutAppTest() {
        onView(withId(R.id.main_menu_image_button))
                .perform(click());
        onView(withText("О приложении")).perform(click());

        onView(withText("Версия:")).check(matches(isDisplayed()));
    }

    @Test
    public void maimTest() {
        onView(withId(R.id.main_menu_image_button))
                .perform(click());
        onView(withText("Главная")).perform(click());

        onView(withText("Праздник")).check(matches(isDisplayed()));
    }

}
