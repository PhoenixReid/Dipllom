package ru.Page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import ru.iteco.fmhandroid.R;
import ru.utils.waitDisplayed;
import ru.Data.TextButtonData;

public class AuthPage {
    TopMenuPage topMenuPage = new TopMenuPage();

    public void auth(String login, String password){
        onView(isRoot()).perform(new waitDisplayed(R.id.login_text_input_layout, 5000));

        onView(allOf(withHint("Логин"), isDescendantOfA(withId(R.id.login_text_input_layout))))
                .perform(replaceText(login));

        onView(allOf(withHint("Пароль"), isDescendantOfA(withId(R.id.password_text_input_layout))))
                .perform(replaceText(password));

        onView(withId(R.id.enter_button))
                .perform(click());


    }

    public  void  authSucess(){
        onView(isRoot()).perform(new waitDisplayed(R.id.authorization_image_button, 5000));

        onView(withText(TextButtonData.allNewsButton)).check(matches(isDisplayed()));
    }

    public  boolean  checkAuth(){
        try {
            onView(isRoot()).perform(new waitDisplayed(R.id.authorization_image_button, 5000));

            onView(withText(TextButtonData.allNewsButton)).check(matches(isDisplayed()));

          return true;

        } catch (Exception e) {
            return false;
        }

    }

    public  void  authUnSucess(){
        onView(withText("Авторизация")).check(matches(isDisplayed()));
    }
}
