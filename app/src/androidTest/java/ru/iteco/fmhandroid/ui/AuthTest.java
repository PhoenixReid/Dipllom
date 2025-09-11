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

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AuthTest {

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
    }

    @Test
    public void authUnLoginTest() {
        onView(
                allOf(
                        withHint("Логин"),
                        isDescendantOfA(withId(R.id.login_text_input_layout))
                )
        ).perform(replaceText("login1"));

        onView(
                allOf(
                        withHint("Пароль"),
                        isDescendantOfA(withId(R.id.password_text_input_layout))
                )
        ).perform(replaceText("password2"));

        onView(withId(R.id.enter_button))
                .perform(click());


        onView(withText("Авторизация")).check(matches(isDisplayed()));
    }

    @Test
    public void authUnPasswordTest() {

        onView(
                allOf(
                        withHint("Логин"),
                        isDescendantOfA(withId(R.id.login_text_input_layout))
                )
        ).perform(replaceText("login2"));

        onView(
                allOf(
                        withHint("Пароль"),
                        isDescendantOfA(withId(R.id.password_text_input_layout))
                )
        ).perform(replaceText("password1"));

        // Нажимаем "Войти"
        onView(withId(R.id.enter_button))
                .perform(click());


        onView(withText("Авторизация")).check(matches(isDisplayed()));

    }

    @Test
    public void authNullLoginTest() {

        onView(
                allOf(
                        withHint("Пароль"),
                        isDescendantOfA(withId(R.id.password_text_input_layout))
                )
        ).perform(replaceText("password2"));

        // Нажимаем "Войти"
        onView(withId(R.id.enter_button))
                .perform(click());

        onView(withText("Авторизация")).check(matches(isDisplayed()));

    }

    @Test
    public void authNullPasswordTest() {

        onView(
                allOf(
                        withHint("Логин"),
                        isDescendantOfA(withId(R.id.login_text_input_layout))
                )
        ).perform(replaceText("login2"));

        // Нажимаем "Войти"
        onView(withId(R.id.enter_button))
                .perform(click());

        onView(withText("Авторизация")).check(matches(isDisplayed()));
    }

    @Test
    public void authSpaceLoginTest() {

        onView(
                allOf(
                        withHint("Логин"),
                        isDescendantOfA(withId(R.id.login_text_input_layout))
                )
        ).perform(replaceText("     "));

        onView(
                allOf(
                        withHint("Пароль"),
                        isDescendantOfA(withId(R.id.password_text_input_layout))
                )
        ).perform(replaceText("password2"));

        // Нажимаем "Войти"
        onView(withId(R.id.enter_button))
                .perform(click());

        onView(withText("Авторизация")).check(matches(isDisplayed()));

    }

    @Test
    public void authSpacePasswordTest() {

        onView(
                allOf(
                        withHint("Логин"),
                        isDescendantOfA(withId(R.id.login_text_input_layout))
                )
        ).perform(replaceText("login2"));

        onView(
                allOf(
                        withHint("Пароль"),
                        isDescendantOfA(withId(R.id.password_text_input_layout))
                )
        ).perform(replaceText("   "));

        // Нажимаем "Войти"
        onView(withId(R.id.enter_button))
                .perform(click());

        onView(withText("Авторизация")).check(matches(isDisplayed()));

    }

    @Test
    public void authTest() {

        onView(
                allOf(
                        withHint("Логин"),
                        isDescendantOfA(withId(R.id.login_text_input_layout))
                )
        ).perform(replaceText("login2"));

        onView(
                allOf(
                        withHint("Пароль"),
                        isDescendantOfA(withId(R.id.password_text_input_layout))
                )
        ).perform(replaceText("password2"));

        // Нажимаем "Войти"
        onView(withId(R.id.enter_button))
                .perform(click());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withText("Новости")).check(matches(isDisplayed()));
        ViewInteraction textView = onView(
                allOf(withText("Новости"),
                        withParent(withParent(withId(R.id.container_list_news_include_on_fragment_main))),
                        isDisplayed()));
        textView.check(matches(withText("Новости")));

        onView(withId(R.id.authorization_image_button))
                .perform(click());
        onView(withText("Выйти")).perform(click());

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
