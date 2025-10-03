package ru.Page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.containsString;

import java.security.PublicKey;

import io.qameta.allure.kotlin.Allure;
import ru.Data.TextButtonData;
import ru.iteco.fmhandroid.R;
import ru.utils.waitDisplayed;

public class TopMenuPage {
    public void exit(){
        Allure.step("Проверяем загрузку кнопку " + R.id.authorization_image_button);
        onView(isRoot()).perform(new waitDisplayed(R.id.authorization_image_button, 5000));
        Allure.step("Нажимаем на кнопку" + R.id.authorization_image_button);
        onView(withId(R.id.authorization_image_button))
                .perform(click());
        Allure.step("Нажимаем на кнопку с текстов " + TextButtonData.exitButton);
        onView(withText(containsString(TextButtonData.exitButton)))
                .perform(click());
    }

    public void qoute(){
        Allure.step("Проверяем загрузку кнопки " + R.id.our_mission_image_button);
        onView(isRoot()).perform(new waitDisplayed(R.id.our_mission_image_button, 5000));
        Allure.step("Нажимаем на кнопку " + R.id.our_mission_image_button);
        onView(withId(R.id.our_mission_image_button))
                .perform(click());
    }

    public void mainMenuButton(String Name){
        Allure.step("Првоеряем загрузку кнопки " + R.id.main_menu_image_button);
        onView(isRoot()).perform(new waitDisplayed(R.id.main_menu_image_button, 5000));
        Allure.step("Нажимаем на кнопку " + R.id.main_menu_image_button);
        onView(withId(R.id.main_menu_image_button))
                .perform(click());
        onView(withText(Name)).perform(click());
    }
}
