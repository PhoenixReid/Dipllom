package ru.Page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
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

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.utils.waitDisplayed;

public class AddEditNewsPage {

    public void category(String Category){
        Allure.step("Проверяем наличие поля" + R.id.news_item_category_text_auto_complete_text_view);
        onView(isRoot()).perform(new waitDisplayed(R.id.news_item_category_text_auto_complete_text_view, 5000));
        Allure.step("Нажимаем на поле" + R.id.news_item_category_text_auto_complete_text_view);
        onView(withId(R.id.news_item_category_text_auto_complete_text_view))
                .perform(click());
        Allure.step("Ждём подзагрузку категорий");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Allure.step("Выбираем категорию " + Category);
        onView(withText(Category))
                .inRoot(isPlatformPopup())
                .check(matches(isDisplayed()))
                .perform(click());



    }

    public void title(String title){
        Allure.step("Вводим в поле " + R.id.news_item_title_text_input_edit_text + " заголовок " + title);
        onView(withId(R.id.news_item_title_text_input_edit_text))
                .perform(replaceText(title));
    }


    public void date(String Data){
        Allure.step("Нажимаем на поле " + R.id.news_item_publish_date_text_input_edit_text);
        onView(withId(R.id.news_item_publish_date_text_input_edit_text))
                .perform(click());
        Allure.step("Ожидаем загрузку кнопки" + android.R.id.button1);
        onView(isRoot()).perform(new waitDisplayed(android.R.id.button1, 5000));
        Allure.step("Нажимаем на дату " + Data);
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        device.findObject(By.text(Data)).click();
        Allure.step("Нажимаем на кнопку ОК");
        onView(withText("ОК"))
                .inRoot(isDialog())
                .perform(click());
    }

    public void time(){
        Allure.step("Выбираем время");
        onView(withId(R.id.news_item_publish_time_text_input_edit_text))
                .perform(click());
        Allure.step("Нажимаем на кнопку Ок");
        onView(withText("ОК"))
                .inRoot(isDialog())
                .perform(click());
    }

    public void description(String description){
        Allure.step("Вводим в поле описания текст " + description);
        onView(withId(R.id.news_item_description_text_input_edit_text))
                .perform(replaceText(description), closeSoftKeyboard());

    }

    public void clickSaveButton(){
        Allure.step("Првоеряем наличие кнопки" + R.id.save_button) ;
        onView(isRoot()).perform(new waitDisplayed(R.id.save_button, 5000));
        Allure.step("Нажимаем на кнопку" + R.id.save_button);
        onView(withId(R.id.save_button))
                .perform(click());
    }

    public void activionButton(){
        Allure.step("ППереключаем кнопку " + R.id.switcher);
        onView(withId(R.id.switcher))
                .perform(click());
    }

    public void addNews(String category, String title,String description,String data){
        new AddEditNewsPage().category(category);

        new AddEditNewsPage().title(title);

        new AddEditNewsPage().date(data);

        new AddEditNewsPage().time();

        new AddEditNewsPage().description(description);

        new AddEditNewsPage().clickSaveButton();

        new NewsPage().textExists(title);
    }

    public void cancelClick(){
        onView(withId(R.id.cancel_button))
                .perform(click());

        onView(withText("OK"))
                .inRoot(isDialog())
                .perform(click());

        onView(isRoot()).perform(new waitDisplayed(R.id.delete_news_item_image_view, 5000));
    }
}
