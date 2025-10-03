package ru.Page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.dto.News;

public class FilterPage {
    public void filterCategory(String category){
        Allure.step("Нажимаем на кнопку " + R.id.news_item_category_text_auto_complete_text_view);
        onView(withId(R.id.news_item_category_text_auto_complete_text_view))
                .perform(click());
        Allure.step("Выбираем категорию " + category);
        onView(withText(category))
                .inRoot(isPlatformPopup())
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void clickFilter(){
        Allure.step("Нажимаем на кнопку " + R.id.filter_button);
        onView(withId(R.id.filter_button))
                .perform(click());
    }

    public void startDate(String startDay){
        Allure.step("Нажимаем на поле " + R.id.news_item_publish_date_start_text_input_layout);
        onView(withId(R.id.news_item_publish_date_start_text_input_layout))
                .perform(click());
        Allure.step("выюираем дату " + startDay);
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        device.findObject(By.text(startDay)).click();
        Allure.step("Нажимаем кнопку ОК");
        onView(withText("ОК"))
                .inRoot(isDialog())
                .perform(click());
    }

    public void endDate(String endDay){
        Allure.step("Нажимаем на поле" + R.id.news_item_publish_date_end_text_input_layout);
        onView(withId(R.id.news_item_publish_date_end_text_input_layout))
                .perform(click());
        Allure.step("Выбираем дату " + endDay);
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        device.findObject(By.text(endDay)).click();
        Allure.step("Нажимаем на кнопку ОК");
        onView(withText("ОК"))
                .inRoot(isDialog())
                .perform(click());
    }
}
