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

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.dto.News;

public class FilterPage {
    public void FilterCategory(String category){
        onView(withId(R.id.news_item_category_text_auto_complete_text_view))
                .perform(click());

        onView(withText(category))
                .inRoot(isPlatformPopup())
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void ClickFilter(){
        onView(withId(R.id.filter_button))
                .perform(click());
    }

    public void StartDate(String startDay){
        onView(withId(R.id.news_item_publish_date_start_text_input_layout))
                .perform(click());

        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        device.findObject(By.text(startDay)).click();

        onView(withText("ОК"))
                .inRoot(isDialog())
                .perform(click());
    }

    public void EndDate(String endDay){
        onView(withId(R.id.news_item_publish_date_end_text_input_layout))
                .perform(click());

        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        device.findObject(By.text(endDay)).click();

        onView(withText("ОК"))
                .inRoot(isDialog())
                .perform(click());
    }
}
