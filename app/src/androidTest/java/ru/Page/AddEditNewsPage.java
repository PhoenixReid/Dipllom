package ru.Page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


import androidx.test.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;

import java.util.Date;

import ru.iteco.fmhandroid.R;
import ru.utils.waitDisplayed;

public class AddEditNewsPage {

    public void Category(String Category){
        onView(withId(R.id.news_item_category_text_auto_complete_text_view))
                .perform(click());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withText(Category))
                .inRoot(isPlatformPopup())
                .check(matches(isDisplayed()))
                .perform(click());

    }

    public void Title(String title){
        onView(withId(R.id.news_item_title_text_input_edit_text))
                .perform(replaceText(title));
    }


    public void Date(String Data){
        onView(withId(R.id.news_item_publish_date_text_input_edit_text))
                .perform(click());

        onView(isRoot()).perform(new waitDisplayed(android.R.id.button1, 5000));

        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        device.findObject(By.text(Data)).click();

        onView(withText("ОК"))
                .inRoot(isDialog())
                .perform(click());
    }

    public void Time(){
        onView(withId(R.id.news_item_publish_time_text_input_edit_text))
                .perform(click());

        onView(withText("ОК"))
                .inRoot(isDialog())
                .perform(click());
    }

    public void Description(String description){
        onView(withId(R.id.news_item_description_text_input_edit_text))
                .perform(replaceText(description), closeSoftKeyboard());

    }

    public void ClickSaveButton(){
        onView(withId(R.id.save_button))
                .perform(click());
    }

    public void ActivionButton(){
        onView(withId(R.id.switcher))
                .perform(click());
    }

    public void AddNews(String category, String title,String description,String data){
        new AddEditNewsPage().Category(category);

        new AddEditNewsPage().Title(title);

        new AddEditNewsPage().Date(data);

        new AddEditNewsPage().Time();

        new AddEditNewsPage().Description(description);

        new AddEditNewsPage().ClickSaveButton();

        new NewsPage().TextExists(title);
    }

    public void CancelClick(){
        onView(withId(R.id.cancel_button))
                .perform(click());

        onView(withText("OK"))
                .inRoot(isDialog())
                .perform(click());

        onView(isRoot()).perform(new waitDisplayed(R.id.delete_news_item_image_view, 5000));
    }
}
