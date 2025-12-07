package ru.Page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

import android.view.View;
import android.widget.TextView;

import androidx.test.espresso.contrib.RecyclerViewActions;

import org.hamcrest.Matchers;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.utils.waitDisplayed;

public class NewsPage {
    public void pointNews(){
        Allure.step("Проверяем наличие кнопки" + R.id.news_list_swipe_refresh);
        onView(withId(R.id.news_list_swipe_refresh))
                .check(matches(isDisplayed()));
    }

    public void clickNews(String Name){
        Allure.step("Раскрываем новость с заголовком " + Name);
        onView(withText(Name))
                .perform(scrollTo())
                .perform(click());
    }

    public void newsEditClick(){
        Allure.step("Ожидаем загрузки кнопки " + R.id.edit_news_item_image_view);
        onView(isRoot()).perform(new waitDisplayed(R.id.edit_news_material_button, 5000));
        Allure.step("Нажимаем на кнопку" + R.id.edit_news_item_image_view);
        onView(withId(R.id.edit_news_material_button))
                .perform(click());
    }


    public void textExists(String text){
        Allure.step("Проверяем наличие текста " + text);
        onView(withText(text))
                .perform(scrollTo())          // прокручиваем до элемента
                .check(matches(isDisplayed())); // и проверяем, что он виден
    }

    public void textRecycler(String text){
        Allure.step("Проверяем наличие текста (с прокруткой в RecyclerView): " + text);

        // Сначала прокручиваем RecyclerView до элемента с нужным текстом
        onView(withId(R.id.news_list_recycler_view))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText(text))));

        // Затем проверяем, что он отображается
        onView(withText(text)).check(matches(isDisplayed()));
    }

    public void textRecyclerClick(String text){
        Allure.step("Проверяем наличие текста (с прокруткой в RecyclerView): " + text);

        // Сначала прокручиваем RecyclerView до элемента с нужным текстом
        onView(withId(R.id.news_list_recycler_view))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText(text))));

        // Затем проверяем, что он отображается
        onView(withText(text)).perform(click());
    }


    public void textNOExists(String text){
        Allure.step("Проверяем отсутсствие текста " + text);
        onView(withText(text)).check(matches(not(isDisplayed())));;
    }

    public void noText(String text) {
        Allure.step("Проверяем отсутсствие текста " + text);
        onView(withText(text)).check(doesNotExist());
    }

    public void filterClick(){
        Allure.step("Ожидаем загрузки кнопки" + R.id.filter_news_material_button);
        onView(isRoot()).perform(new waitDisplayed(R.id.filter_news_material_button, 5000));
        Allure.step("Нажимаем на кнопку " + R.id.filter_news_material_button);
        onView(withId(R.id.filter_news_material_button))
                .perform(click());
    }

    public void errorText(String textError, View decorView){
        Allure.step("Проверяем наличие текста : " + textError);
        onView(withText(textError))
                .inRoot(withDecorView(Matchers.not(decorView)))// Here you use decorView
                .check(matches(isDisplayed()));
    }


}
