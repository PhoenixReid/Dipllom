package ru.Page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import org.w3c.dom.Text;

import io.qameta.allure.kotlin.Allure;
import ru.Data.TextButtonData;
import ru.iteco.fmhandroid.R;
import ru.utils.waitDisplayed;

public class MainMenuPage {
    public void pointMain(){
        Allure.step("Проверяем наличие текста " + TextButtonData.newsButton);
        onView(withText(TextButtonData.newsButton)).check(matches(isDisplayed()));
    }

    public void allNews(){
        Allure.step("Проверяем загрузку кнопки " + R.id.main_menu_image_button);
        onView(isRoot()).perform(new waitDisplayed(R.id.main_menu_image_button, 5000));
        Allure.step("Нажимаем на кнопку с текстом " + TextButtonData.allNewsButton);
        onView(withText(TextButtonData.allNewsButton)).perform(click());
    }

    public void expandButtonClick(){
        Allure.step("Нажимаем на кнопку " + R.id.expand_material_button);
        onView(withId(R.id.expand_material_button))
                .perform(click());
    }
}
