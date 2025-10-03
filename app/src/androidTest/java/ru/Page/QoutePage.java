package ru.Page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;

import io.qameta.allure.kotlin.Allure;

public class QoutePage {
    public void clickQoute(String Name){
        Allure.step("Нажимаем наличие цитаты с названием " + Name );
        onView(withText(containsString(Name)))
                .check(matches(isDisplayed()));
        Allure.step("нажимает на цитату с названием" + Name);
        onView(withText(containsString(Name)))
                .perform(click());
    }

    public void  quoteDesc(String description){
        Allure.step("ПРоверяем описание на совпадение с " + description);
        onView(withText(containsString(description)))
                .check(matches(isDisplayed()));
    }

    public void qoutePoint(){
        Allure.step("Проверяем переход на страницу цитат");
        onView(withText("Главное - жить любя")).check(matches(isDisplayed()));
    }
}
