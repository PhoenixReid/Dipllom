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

public class AuthPage {
    public void Auth(String login, String password){
        onView(isRoot()).perform(new waitDisplayed(R.id.login_text_input_layout, 5000));

        onView(allOf(withHint("Логин"), isDescendantOfA(withId(R.id.login_text_input_layout))))
                .perform(replaceText(login));

        onView(allOf(withHint("Пароль"), isDescendantOfA(withId(R.id.password_text_input_layout))))
                .perform(replaceText(password));

        onView(withId(R.id.enter_button))
                .perform(click());


    }

    public  void  AuthSucess(){
        onView(withText("ВСЕ НОВОСТИ")).check(matches(isDisplayed()));
    }

    public  void  AuthUnSucess(){
        onView(withText("Авторизация")).check(matches(isDisplayed()));
    }
}
