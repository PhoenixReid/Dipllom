package ru.Page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.utils.waitDisplayed;

public class EditNewsPage {
 public void addNewsClick(){
     Allure.step("Проверяем наличие кнопки" + R.id.add_news_image_view);
     onView(isRoot()).perform(new waitDisplayed(R.id.add_news_image_view, 5000));
     Allure.step("Нажимаем на кнопку " + R.id.add_news_image_view);
     onView(withId(R.id.add_news_image_view))
             .perform(click());
 }

 public void deleteNews(String title){
     Allure.step("Нажимаем на кнопку " + R.id.delete_news_item_image_view + " c заголовком " + title);
     onView(allOf(withId(R.id.delete_news_item_image_view),
             withParent(hasDescendant(withText(title)))))
             .perform(click());
     Allure.step("Нажимаем кнопку ОК");
     onView(withText("OK"))
             .inRoot(isDialog())
             .check(matches(isDisplayed()))
             .perform(click());
 }

 public void editNewsClick(String title){
     Allure.step("Нажимаем на кнопку" + R.id.edit_news_item_image_view);
     onView(allOf(withId(R.id.edit_news_item_image_view),
             withParent(hasDescendant(withText(title)))))
             .perform(click());
 }

}
